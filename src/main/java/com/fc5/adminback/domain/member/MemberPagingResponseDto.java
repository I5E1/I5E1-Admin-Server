package com.fc5.adminback.domain.member;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberPagingResponseDto {

    private int totalPages;
    private int currentPage;
    private List<MemberWithCompletedDutyCount> members;
}
