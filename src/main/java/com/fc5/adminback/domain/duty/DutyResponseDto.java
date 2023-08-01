package com.fc5.adminback.domain.duty;

import com.fc5.adminback.domain.annual.MemberResponseDto;
import com.fc5.adminback.domain.model.Duty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DutyResponseDto {
    private MemberResponseDto member;
    private LocalDate dutyDate;
    private LocalDate endDate;
    private String status;
    private String summary;
    private String reason;
    private Integer spentDays;

    public static DutyResponseDto of(Duty duty) {
        return DutyResponseDto.builder()
                .dutyDate(duty.getDutyDate())
                .status(duty.getStatus().getDescription())
                .reason(duty.getReason())
                .member(MemberResponseDto.of(duty.getMember()))
                .build();
    }
}
