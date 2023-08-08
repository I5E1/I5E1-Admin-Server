package com.fc5.adminback.domain.annual.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UpdateAnnualRequestDto {

    @NotNull
    private Status status;
}
