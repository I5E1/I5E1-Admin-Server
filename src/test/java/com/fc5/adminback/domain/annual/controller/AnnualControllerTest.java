package com.fc5.adminback.domain.annual.controller;

import com.fc5.adminback.domain.annual.dto.AnnualPagingResponseDto;
import com.fc5.adminback.domain.annual.dto.AnnualResponseDto;
import com.fc5.adminback.domain.annual.mock.MockPage;
import com.fc5.adminback.domain.annual.service.AnnualService;
import com.fc5.adminback.domain.model.Annual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("AnnualController 테스트")
@AutoConfigureMockMvc
@SpringBootTest
class AnnualControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnualService annualService;

    private final MockHttpSession httpSession = new MockHttpSession();

    @BeforeEach
    public void clear() {
        httpSession.clearAttributes();
    }


    @DisplayName("[/api/annual?page=int.class] 모든 연차 조회 컨트롤러 테스트 - 정상 응답")
    @Test
    public void getAllAnnualTest() throws Exception {

        // given
        Page<Annual> page = new MockPage(1);
        List<AnnualResponseDto> annualResponseDtos = new ArrayList<>();
        AnnualPagingResponseDto annualPagingResponseDtoMock = AnnualPagingResponseDto.of(annualResponseDtos, 1, 11);
        given(annualService.getCount()).willReturn(11);
        given(annualService.getAllAnnuals(1)).willReturn(annualPagingResponseDtoMock);
        given(annualService.getAll(1)).willReturn(page);
        httpSession.setAttribute("adminId", 1L);

        // when
        mockMvc.perform(
                        get("/api/annual")
                                .param("page", Integer.toString(1))
                                .session(httpSession)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data.annuals").isArray())
                .andExpect(jsonPath("$.message").isString());


        then(annualService).should(times(1)).getCount();

    }

    @DisplayName("[/api/annual?page=int.class] 모든 연차 조회 컨트롤러 테스트 - 에러 [쿼리스트링 범위 에러]")
    @Test
    public void getAllAnnualWithInvalidExceptionTest() throws Exception {

        // given
        httpSession.setAttribute("adminId", 1L);

        // when
        mockMvc.perform(
                        get("/api/annual")
                                .param("page", Integer.toString(0))
                                .session(httpSession)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(BindException.class));

        // then
    }
}