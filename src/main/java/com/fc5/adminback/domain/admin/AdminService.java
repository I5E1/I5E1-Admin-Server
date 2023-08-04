package com.fc5.adminback.domain.admin;

import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import com.fc5.adminback.domain.annual.exception.UnauthorizedAdminException;
import com.fc5.adminback.domain.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin login(AdminLoginRequestDto loginRequestDto) {
        Admin admin = adminRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED.getMessage(), AnnualErrorCode.UNAUTHORIZED));

        if (!admin.getPassword().equals(loginRequestDto.getPassword())) {
            throw new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED.getMessage(), AnnualErrorCode.UNAUTHORIZED);
        }

        return admin;
    }

    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED.getMessage(), AnnualErrorCode.UNAUTHORIZED));
    }
}
