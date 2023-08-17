package com.fc5.adminback.domain.annual.dto;

import com.fc5.adminback.domain.member.dto.MemberListResponseDto;
import com.fc5.adminback.domain.model.Annual;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnualResponseDto {

    private Long annualId;
    private MemberListResponseDto member;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String summary;
    private String reason;
    private Integer spentDays;

    public static AnnualResponseDto of(Annual annual) {
        return AnnualResponseDto.builder()
                .annualId(annual.getId())
                .startDate(annual.getStartDate())
                .endDate(annual.getEndDate())
                .status(annual.getStatus().getDescription())
                .reason(annual.getReason())
                .summary(annual.getSummary())
                .spentDays(annual.getSpentDays())
                .member(MemberListResponseDto.of(annual.getMember()))
                .build();
    }
}
