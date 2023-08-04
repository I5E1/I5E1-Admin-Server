package com.fc5.adminback.domain.duty;

import com.fc5.adminback.domain.member.MemberListResponseDto;
import com.fc5.adminback.domain.model.Duty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DutyResponseDto {
    private Long id;
    private MemberListResponseDto member;
    private LocalDate dutyDate;
    private String status;
    private String reason;

    public static DutyResponseDto of(Duty duty) {
        return DutyResponseDto.builder()
                .id(duty.getId())
                .dutyDate(duty.getDutyDate())
                .status(duty.getStatus().getDescription())
                .reason(duty.getReason())
                .member(MemberListResponseDto.of(duty.getMember()))
                .build();
    }
}
