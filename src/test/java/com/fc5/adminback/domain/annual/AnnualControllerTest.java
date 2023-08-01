package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.model.Annual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("AnnualController 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class AnnualControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnualService annualService;

    @DisplayName("[/api/annual?page=int.class] 모든 연차 조회 컨트롤러 테스트 - 정상 응답")
    @Test
    public void getAllAnnualTest() throws Exception {
        // given
        Page<Annual> page = new MockPage(1);
        given(annualService.getAll(1)).willReturn(page);

        // when
        mockMvc.perform(
                        get("/api/annual")
                                .param("page", Integer.toString(1))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message").isString());
//
        // then
        then(annualService).should().getAll(1);
    }

    @DisplayName("[/api/annual?page=int.class] 모든 연차 조회 컨트롤러 테스트 - 에러 [쿼리스트링 범위 에러]")
    @Test
    public void getAllAnnualWithInvalidExceptionTest() throws Exception {
        // given

        // when
        mockMvc.perform(
                        get("/api/annual")
                                .param("page", Integer.toString(0))
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(BindException.class));
//
        // then
    }
}