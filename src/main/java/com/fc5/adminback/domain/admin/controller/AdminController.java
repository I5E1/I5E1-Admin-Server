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
    public ResponseEntity<?> getPagingMemberList(
            @ModelAttribute @Valid PageIndex pageIndex
    ) {
        int pageSize = 3;
        int totalPages = memberRepository.findAll().size() / pageSize + 1;
        if (pageIndex.getPage() > totalPages) {
            throw new IllegalArgumentException("더 이상 당직 정보가 존재하지 않습니다.");
        }

        PageRequest pageRequest = PageRequest.of(pageIndex.getPage() - 1, pageSize);
        List<MemberWithCompletedDutyCount> members = memberRepository.getAllMemberWithExecutedDutyCount(pageRequest);
        MemberWithCompletedDutyCountPagingResponseDto result =
                MemberWithCompletedDutyCountPagingResponseDto.of(members, totalPages, pageIndex.getPage());
        return APIDataResponse.of(HttpStatus.OK, "모든 유저 조회에 성공하였습니다.", result);
    }

    @GetMapping("/api/annual/{userId}")
    public ResponseEntity<?> updateAnnual(@PathVariable("userId") Long userId, @RequestParam int size) {
        if (size < 0) {
            throw new IllegalArgumentException("올바르지 않은 쿼리 입력입니다");
        }
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        member.modifiyAnnualCount(size);
        memberRepository.save(member);
        return APIDataResponse.empty(HttpStatus.OK, "회원의 남은 연차 일 수를 수정했습니다.");
    }

    @PatchMapping("/api/position/{userId}")
    public ResponseEntity<?> updatePosition(@PathVariable Long userId, UpdateUserPositionDto updateUserPositionDto) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        member.modifyPosition(updateUserPositionDto.getPosition());
        memberRepository.save(member);
        return APIDataResponse.empty(HttpStatus.OK, "회원의 직급을 수정하였습니다.");
    }
}
