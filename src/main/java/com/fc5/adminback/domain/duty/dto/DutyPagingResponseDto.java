package com.fc5.adminback.domain.duty.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DutyPagingResponseDto {

    private int totalCount;
    private int currentPage;
    private List<DutyResponseDto> duties;

    public static DutyPagingResponseDto of(List<DutyResponseDto> duties, int currentPage, int totalCount) {
        return DutyPagingResponseDto.builder()
                .currentPage(currentPage)
                .totalCount(totalCount)
                .duties(duties)
                .build();
    }
}
