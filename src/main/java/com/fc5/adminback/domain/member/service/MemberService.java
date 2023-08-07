package com.fc5.adminback.domain.member.service;

import com.fc5.adminback.domain.admin.dto.MemberWithCompletedDutyCountPagingResponseDto;
import com.fc5.adminback.domain.admin.dto.UpdateUserPositionDto;
import com.fc5.adminback.domain.member.MemberWithCompletedDutyCount;
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
            throw new IllegalArgumentException("더 이상 당직 정보가 존재하지 않습니다.");
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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        member.modifiyAnnualCount(size);
    }

    @Transactional
    public void modifyPosition(Long userId, UpdateUserPositionDto updateUserPositionDto) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        member.modifyPosition(updateUserPositionDto.getPosition());
    }
}
