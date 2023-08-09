package com.fc5.adminback.domain.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc5.adminback.domain.admin.dto.AdminLoginRequestDto;
import com.fc5.adminback.domain.admin.service.AdminService;
import com.fc5.adminback.domain.model.Admin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Autowired
    private ObjectMapper mapper;

    @DisplayName("[/api/login] 로그인 테스트")
    @Test
    void login() throws Exception {
         // Given
        AdminLoginRequestDto mockDto = AdminLoginRequestDto.builder()
                .email("qwer@naver.com")
                .password("1234")
                .build();
        Admin admin = new Admin();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();


        given(adminService.login(mockDto)).willReturn(admin);
        willDoNothing().given(adminService).setSession(mockHttpServletRequest, admin);

        // When
        ResultActions result = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mockDto))
        );

        // Then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").doesNotExist());

    }

    @DisplayName("[/api/logout] 로그아웃")
    @Test
    void logout() throws Exception {

        // Given
        willDoNothing().given(adminService).logout(any());

        // When
        ResultActions result = mockMvc.perform(
                get("/api/logout")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}