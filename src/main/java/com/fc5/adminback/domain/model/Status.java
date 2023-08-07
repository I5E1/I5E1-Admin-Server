package com.fc5.adminback.domain.model;

import lombok.Getter;

@Getter
public enum Status {

    REQUESTED("미승인"),
    APPROVED("승인"),
    CANCELED("취소"),
    REJECTED("반려"),
    COMPLETED("완료");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}