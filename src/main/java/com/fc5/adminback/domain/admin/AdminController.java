package com.fc5.adminback.domain.admin;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.annual.AnnualResponseDto;
import com.fc5.adminback.domain.annual.PageIndex;
import com.fc5.adminback.domain.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("api/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequestDto adminLoginRequestDto, HttpServletRequest request) {
        Admin admin = adminService.login(adminLoginRequestDto);


        request.getSession().invalidate();
        HttpSession session = request.getSession(true);

        session.setAttribute("adminId", admin.getId());
        session.setMaxInactiveInterval(3600);

        return APIDataResponse.empty(HttpStatus.OK, "로그인에 성공하였습니다");
    }

    @GetMapping("api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return APIDataResponse.empty(HttpStatus.OK, "로그아웃에 성공하였습니다");
    }

    @GetMapping("api/user")
    public ResponseEntity<?> getAll(
            @ModelAttribute @Valid PageIndex pageIndex
    ) {

        List<MemberResponseDto> result = adminService.getAll(pageIndex.getPage()).stream()
                .map(MemberResponseDto::of)
                .collect(Collectors.toList());
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }
}
