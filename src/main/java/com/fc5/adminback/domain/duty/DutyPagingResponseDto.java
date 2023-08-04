package com.fc5.adminback.domain.duty;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DutyPagingResponseDto {

    private int totalPages;
    private int currentPage;
    private List<DutyResponseDto> duties;

    public static DutyPagingResponseDto of(List<DutyResponseDto> duties, int currentPage, int totalPages) {
        return DutyPagingResponseDto.builder()
                .currentPage(currentPage)
                .totalPages(totalPages)
                .duties(duties)
                .build();
    }
}
