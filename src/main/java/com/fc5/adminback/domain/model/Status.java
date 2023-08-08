package com.fc5.adminback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    REQUESTED("미승인"),
    APPROVED("승인"),
    CANCELED("취소"),
    REJECTED("반려"),
    COMPLETED("완료");

    private final String description;
}