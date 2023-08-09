package com.fc5.adminback.domain.admin.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class AdminLoginRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("유효성 검증 - 성공")
    @Test
    void validationSuccessTest() {

        AdminLoginRequestDto dto = AdminLoginRequestDto.builder()
                .email("qwe123@naver.com")
                .password("1234")
                .build();

        Set<ConstraintViolation<AdminLoginRequestDto>> violations = validator.validate(dto);
        assertThat(violations.size()).isEqualTo(0);
    }

    @DisplayName("유효성 검증 - 실패 (올바르지 않은 이메일 형식)")
    @Test
    void validationFailWithInvalidEmailFormat() {
        AdminLoginRequestDto dto = AdminLoginRequestDto.builder()
                .email("qwe123naver.com")
                .password("1234")
                .build();

        Set<ConstraintViolation<AdminLoginRequestDto>> violations = validator.validateProperty(dto, "email");
        assertThat(violations.size()).isEqualTo(1);
    }

}