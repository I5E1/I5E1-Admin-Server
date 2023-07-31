package com.fc5.adminback.domain.model;

import lombok.Getter;

@Getter
public enum Status {

    REQUESTED("신청 중"),
    APPROVED("승인 완료"),
    CANCEL_PENDING("취소 중"),
    CANCELED("취소 완료"),
    REJECTED("요청 반려"),
    CANCEL_REJECTED("취소 반려"),
    COMPLETED("완료");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}