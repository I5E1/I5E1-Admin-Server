package com.fc5.adminback.domain.duty.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UpdateDutyRequestDto {

    @NotNull
    private Status status;
}
