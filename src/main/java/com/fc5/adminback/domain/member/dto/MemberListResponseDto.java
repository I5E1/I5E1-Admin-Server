package com.fc5.adminback.domain.member.dto;

import com.fc5.adminback.domain.model.Member;
import com.fc5.adminback.domain.model.Position;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberListResponseDto {

    private Long memberId;
    private String name;
    private String email;
    private Position position;
    private int annualCount;

    public static MemberListResponseDto of(Member member) {
        return MemberListResponseDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .position(member.getPosition())
                .annualCount(member.getAnnualCount())
                .build();
    }
}
