package com.fc5.adminback.domain.admin.dto;

import com.fc5.adminback.domain.model.Position;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateUserPositionDto {

    @NotNull
    private Position position;
}
