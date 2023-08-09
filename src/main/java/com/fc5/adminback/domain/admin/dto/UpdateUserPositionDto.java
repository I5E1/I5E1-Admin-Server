package com.fc5.adminback.domain.admin.dto;

import com.fc5.adminback.domain.model.Position;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UpdateUserPositionDto {

    @NotNull
    private Position position;
}
