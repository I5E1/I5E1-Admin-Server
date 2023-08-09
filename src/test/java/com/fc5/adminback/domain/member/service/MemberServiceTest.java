package com.fc5.adminback.domain.member.service;

import com.fc5.adminback.domain.admin.dto.UpdateUserPositionDto;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.member.repository.MemberRepository;
import com.fc5.adminback.domain.model.Member;
import com.fc5.adminback.domain.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void modifiyAnnual() {
        // Given
        Member member = getMember();
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(member));

        // When
        memberService.modifiyAnnual(any(), 1999);

        // Then
        assertThat(member.getAnnualCount()).isEqualTo(1999);
    }

    @Test
    void modifyPosition() {
        // Given
        Member member = getMember();
        UpdateUserPositionDto dto = UpdateUserPositionDto.builder()
                .position(Position.BOSS)
                .build();

        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(member));

        // When
        memberService.modifyPosition(any(), dto);

        // Then
        assertThat(member.getPosition()).isEqualTo(Position.BOSS);
    }

    private Member getMember() {
        return Member.builder()
                .position(Position.STAFF)
                .name("김길동")
                .email("qwer123123@naver.com")
                .annualCount(15)
                .tel("01012345678")
                .password("1234")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}