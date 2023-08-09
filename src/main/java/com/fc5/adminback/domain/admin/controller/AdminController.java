package com.fc5.adminback.domain.admin.controller;

import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.admin.dto.AdminLoginRequestDto;
import com.fc5.adminback.domain.admin.service.AdminService;
import com.fc5.adminback.domain.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("api/login")
    public ResponseEntity<?> login(@RequestBody @Valid AdminLoginRequestDto adminLoginRequestDto, HttpServletRequest request) {
        adminService.login(request, adminLoginRequestDto);

        return APIDataResponse.empty(HttpStatus.OK, "로그인에 성공하였습니다");
    }

    @GetMapping("api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        adminService.logout(request);

        return APIDataResponse.empty(HttpStatus.OK, "로그아웃에 성공하였습니다");
    }
}
