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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public int getTotalCount(int currentPage, int pageSize) {
        List<Member> members = memberRepository.findAll();
        int totalPages = members.size() % pageSize == 0 ? members.size() / pageSize : members.size() / pageSize + 1;

        if (currentPage > totalPages) {
            throw new InvalidPageException(MemberErrorCode.INVALID_PAGE.getMessage(), MemberErrorCode.INVALID_PAGE);
        }

        return members.size();
    }

    public MemberWithCompletedDutyCountPagingResponseDto getMamberPagingDto(int totalCount, int currentPage, int pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        List<MemberWithCompletedDutyCount> members = memberRepository.getAllMemberWithExecutedDutyCount(pageRequest);
        return MemberWithCompletedDutyCountPagingResponseDto.of(members, totalCount, currentPage);
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

    public MemberWithCompletedDutyCountPagingResponseDto searchByName(int page, String query) {
        Page<MemberWithCompletedDutyCount> pages = memberRepository.findByNameContainingWithExecutedDutyCount(PageRequest.of(page - 1, 10), query);
        List<MemberWithCompletedDutyCount> members = pages.stream().collect(Collectors.toList());

        if (page > pages.getTotalPages()) {
            throw new InvalidPageException(MemberErrorCode.INVALID_PAGE.getMessage(), MemberErrorCode.INVALID_PAGE);
        }
        return MemberWithCompletedDutyCountPagingResponseDto.of(members, Long.valueOf(pages.getTotalElements()).intValue(), page);
    }
}
