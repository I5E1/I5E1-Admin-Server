package com.fc5.adminback.domain.model;

public enum Position {

    STAFF("사원"),
    BOSS("사장"),
    INTERN("인턴"),
    ASSISTANT_MANAGER("대리"),
    MANAGER("과장"),
    GENERAL_MANAGER("실장"),
    DEPUTY_GENERAL_MANAGER("차장"),
    DEPARTMENT_MANAGER("부장"),
    MANAGING_DIRECTOR("상무"),
    SENIOR_MANAGING_DIRECTOR("전무"),
    CHAIRMAN("회장"),
    ;

    private final String type;

    Position(String type) {
        this.type = type;
    }
}
