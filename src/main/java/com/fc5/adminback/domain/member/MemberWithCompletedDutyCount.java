package com.fc5.adminback.domain.member;

import com.fc5.adminback.domain.model.Position;

import java.time.LocalDateTime;

public interface MemberWithCompletedDutyCount {

    Long getId();

    String getName();

    String getEmail();

    String getTel();

    Position getPosition();

    Integer getAnnualCount();

    Integer getCompletedDutyCount();

    LocalDateTime getCreatedAt();
}
