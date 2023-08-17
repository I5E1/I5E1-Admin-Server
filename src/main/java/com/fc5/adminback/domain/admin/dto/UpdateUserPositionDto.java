package com.fc5.adminback.domain.admin.dto;

import com.fc5.adminback.domain.model.Position;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateUserPositionDto {

    @NotNull
    private Position position;
}
