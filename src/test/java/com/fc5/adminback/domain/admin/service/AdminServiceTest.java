package com.fc5.adminback.domain.admin.service;

import com.fc5.adminback.domain.admin.dto.AdminLoginRequestDto;
import com.fc5.adminback.domain.admin.repository.AdminRepository;
import com.fc5.adminback.domain.model.Admin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @DisplayName("로그인 테스트")
    @Test
    void login() {
        AdminLoginRequestDto dto = AdminLoginRequestDto.builder()
                .email("qwer123@naver.com")
                .password("1234")
                .build();
        Admin mockAdmin = Admin.builder()
                .email("qwer123@naver.com")
                .password("1234")
                .build();

        HttpServletRequest request = new MockHttpServletRequest();
        given(adminRepository.findByEmail(any())).willReturn(Optional.ofNullable(mockAdmin));

        Admin admin = adminService.login(request, dto);

        assertThat(request.getSession()).isNotNull();
        assertThat(admin).isEqualTo(mockAdmin);

    }

    @DisplayName("로그아웃 테스트")
    @Test
    void logout() {
        // Given
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession(true);

        // When
        adminService.logout(request);

        // Then
        assertThat(request.getSession(false)).isNull();

    }
}