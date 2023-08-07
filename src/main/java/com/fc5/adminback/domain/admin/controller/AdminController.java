package com.fc5.adminback.domain.admin.controller;

import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.admin.dto.AdminLoginRequestDto;
import com.fc5.adminback.domain.admin.service.AdminService;
import com.fc5.adminback.domain.admin.dto.MemberWithCompletedDutyCountPagingResponseDto;
import com.fc5.adminback.domain.admin.dto.UpdateUserPositionDto;
import com.fc5.adminback.domain.annual.dto.PageIndex;
import com.fc5.adminback.domain.member.repository.MemberRepository;
import com.fc5.adminback.domain.member.MemberWithCompletedDutyCount;
import com.fc5.adminback.domain.model.Admin;
import com.fc5.adminback.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MemberRepository memberRepository;

    @PostMapping("api/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequestDto adminLoginRequestDto, HttpServletRequest request) {
        Admin admin = adminService.login(adminLoginRequestDto);

        adminService.setSession(request, admin);


        return APIDataResponse.empty(HttpStatus.OK, "로그인에 성공하였습니다");
    }

    @GetMapping("api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        adminService.logout(request);

        return APIDataResponse.empty(HttpStatus.OK, "로그아웃에 성공하였습니다");
    }
}
