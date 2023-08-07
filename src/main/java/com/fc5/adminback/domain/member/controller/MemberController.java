package com.fc5.adminback.domain.member.controller;

import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.admin.dto.MemberWithCompletedDutyCountPagingResponseDto;
import com.fc5.adminback.domain.admin.dto.UpdateUserPositionDto;
import com.fc5.adminback.domain.annual.dto.PageIndex;
import com.fc5.adminback.domain.member.repository.MemberRepository;
import com.fc5.adminback.domain.member.service.MemberService;
import com.fc5.adminback.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("api/user")
    public ResponseEntity<?> getPagingMemberList(
            @ModelAttribute @Valid PageIndex pageIndex
    ) {
        int pageSize = 3;
        int totalPages = memberService.getTotalPages(pageIndex.getPage(), pageSize);

        MemberWithCompletedDutyCountPagingResponseDto result = memberService.getMamberPagingDto(totalPages, pageIndex.getPage(), pageSize);

        return APIDataResponse.of(HttpStatus.OK, "모든 유저 조회에 성공하였습니다.", result);
    }

    @GetMapping("/api/annual/{userId}")
    public ResponseEntity<?> updateAnnual(@PathVariable("userId") Long userId, @RequestParam @Min(0) int size) {
        memberService.modifiyAnnual(userId, size);
        return APIDataResponse.empty(HttpStatus.OK, "회원의 남은 연차 일 수를 수정했습니다.");
    }

    @PatchMapping("/api/position/{userId}")
    public ResponseEntity<?> updatePosition(@PathVariable Long userId, UpdateUserPositionDto updateUserPositionDto) {

        memberService.modifyPosition(userId, updateUserPositionDto);

        return APIDataResponse.empty(HttpStatus.OK, "회원의 직급을 수정하였습니다.");
    }
}
