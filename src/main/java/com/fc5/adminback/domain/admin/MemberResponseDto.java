package com.fc5.adminback.domain.admin;


import com.fc5.adminback.domain.model.Member;
import com.fc5.adminback.domain.model.Position;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberResponseDto {

    private Long id;

    private String name;

    private String email;

    private String tel;

    private Position position;

    private int annualCount;

    private LocalDateTime createdAt;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .annualCount(member.getAnnualCount())
                .createdAt(member.getCreatedAt())
                .email(member.getEmail())
                .tel(member.getTel())
                .name(member.getName())
                .position(member.getPosition())
                .build();
    }

}
