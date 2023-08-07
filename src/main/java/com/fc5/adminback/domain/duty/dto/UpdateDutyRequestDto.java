package com.fc5.adminback.domain.duty.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateDutyRequestDto {

    @NotNull
    private Status status;
}
