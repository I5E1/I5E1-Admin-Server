package com.fc5.adminback.domain.annual;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnualPagingResponseDto {

    private int totalPages;
    private int currentPage;
    private List<AnnualResponseDto> annuals;

    public static AnnualPagingResponseDto of(List<AnnualResponseDto> annuals, int currentPage, int totalPages) {
        return AnnualPagingResponseDto.builder()
                .currentPage(currentPage)
                .totalPages(totalPages)
                .annuals(annuals)
                .build();
    }
}
