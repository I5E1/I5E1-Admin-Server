package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.model.Member;
import com.fc5.adminback.domain.model.Position;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private String name;
    private String email;
    private Position position;
    private int annualCount;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .position(member.getPosition())
                .annualCount(member.getAnnualCount())
                .build();
    }
}
