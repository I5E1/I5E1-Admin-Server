package com.fc5.adminback.domain.admin.dto;

import com.fc5.adminback.domain.member.MemberWithCompletedDutyCount;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberWithCompletedDutyCountPagingResponseDto {

    private int totalCount;
    private int currentPage;
    private List<MemberWithCompletedDutyCount> members;

    public static MemberWithCompletedDutyCountPagingResponseDto of(List<MemberWithCompletedDutyCount> members, int totalCount, int currentPage) {
        return MemberWithCompletedDutyCountPagingResponseDto.builder()
                .members(members)
                .currentPage(currentPage)
                .totalCount(totalCount)
                .build();
    }
}
