package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Member;
import com.fc5.adminback.domain.model.Status;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnualResponseDto {
    private MemberResponseDto member;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String summary;
    private String reason;
    private Integer spentDays;

    public static AnnualResponseDto of(Annual annual) {
        return AnnualResponseDto.builder()
                .startDate(annual.getStartDate())
                .endDate(annual.getEndDate())
                .status(annual.getStatus().getDescription())
                .reason(annual.getReason())
                .summary(annual.getSummary())
                .spentDays(annual.getSpentDays())
                .member(MemberResponseDto.of(annual.getMember()))
                .build();
    }
}
