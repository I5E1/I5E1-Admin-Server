package com.fc5.adminback.domain.member.service;

import com.fc5.adminback.domain.admin.dto.MemberWithCompletedDutyCountPagingResponseDto;
import com.fc5.adminback.domain.admin.dto.UpdateUserPositionDto;
import com.fc5.adminback.domain.member.MemberWithCompletedDutyCount;
import com.fc5.adminback.common.exception.InvalidPageException;
import com.fc5.adminback.common.exception.NotFoundEntityException;
import com.fc5.adminback.domain.member.exception.errorcode.MemberErrorCode;
import com.fc5.adminback.domain.member.repository.MemberRepository;
import com.fc5.adminback.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public int getTotalPages(int currentPage, int pageSize) {
        int totalPages = memberRepository.findAll().size() / pageSize + 1;

        if (currentPage > totalPages) {
            throw new InvalidPageException(MemberErrorCode.INVALID_PAGE.getMessage(), MemberErrorCode.INVALID_PAGE);
        }

        return totalPages;
    }

    public MemberWithCompletedDutyCountPagingResponseDto getMamberPagingDto(int totalPages, int currentPage, int pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        List<MemberWithCompletedDutyCount> members = memberRepository.getAllMemberWithExecutedDutyCount(pageRequest);
        return MemberWithCompletedDutyCountPagingResponseDto.of(members, totalPages, currentPage);
    }

    @Transactional
    public void modifiyAnnual(Long userId, int size) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new NotFoundEntityException(MemberErrorCode.INVALID_INDEX.getMessage(), MemberErrorCode.INVALID_INDEX));
        member.modifiyAnnualCount(size);
    }

    @Transactional
    public void modifyPosition(Long userId, UpdateUserPositionDto updateUserPositionDto) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new NotFoundEntityException(MemberErrorCode.INVALID_INDEX.getMessage(), MemberErrorCode.INVALID_INDEX));
        member.modifyPosition(updateUserPositionDto.getPosition());
    }
}
