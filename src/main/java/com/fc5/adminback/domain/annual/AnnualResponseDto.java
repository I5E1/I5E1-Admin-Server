package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.member.MemberListResponseDto;
import com.fc5.adminback.domain.model.Annual;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnualResponseDto {
    private Long id;
    private MemberListResponseDto member;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String summary;
    private String reason;
    private Integer spentDays;

    public static AnnualResponseDto of(Annual annual) {
        return AnnualResponseDto.builder()
                .id(annual.getId())
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
