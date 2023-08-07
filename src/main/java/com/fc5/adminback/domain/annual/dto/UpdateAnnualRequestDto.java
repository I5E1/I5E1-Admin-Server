package com.fc5.adminback.domain.annual.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class UpdateAnnualRequestDto {

    @NotNull
    private Status status;
}
