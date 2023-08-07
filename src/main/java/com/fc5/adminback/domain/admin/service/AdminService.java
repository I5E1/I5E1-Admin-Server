package com.fc5.adminback.domain.admin.service;

import com.fc5.adminback.domain.admin.dto.AdminLoginRequestDto;
import com.fc5.adminback.domain.admin.repository.AdminRepository;
import com.fc5.adminback.domain.annual.exception.errorcode.AnnualErrorCode;
import com.fc5.adminback.domain.annual.exception.UnauthorizedAdminException;
import com.fc5.adminback.domain.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin login(AdminLoginRequestDto loginRequestDto) {
        Admin admin = adminRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED.getMessage(), AnnualErrorCode.UNAUTHORIZED));

        validatePassword(loginRequestDto, admin);

        return admin;
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public void setSession(HttpServletRequest request, Admin admin) {
        request.getSession().invalidate();
        HttpSession session = request.getSession(true);

        session.setAttribute("adminId", admin.getId());
        session.setMaxInactiveInterval(3600);
    }

    private void validatePassword(AdminLoginRequestDto loginRequestDto, Admin admin) {
        if (!admin.getPassword().equals(loginRequestDto.getPassword())) {
            throw new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED.getMessage(), AnnualErrorCode.UNAUTHORIZED);
        }
    }
}
