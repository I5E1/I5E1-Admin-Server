package com.fc5.adminback.domain.annual.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnualPagingResponseDto {

    private int totalCount;
    private int currentPage;
    private List<AnnualResponseDto> annuals;

    public static AnnualPagingResponseDto of(List<AnnualResponseDto> annuals, int currentPage, int totalCount) {
        return AnnualPagingResponseDto.builder()
                .currentPage(currentPage)
                .totalCount(totalCount)
                .annuals(annuals)
                .build();
    }
}
