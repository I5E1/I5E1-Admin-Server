package com.fc5.adminback.domain.annual.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateAnnualRequestDto {

    @NotNull
    private Status status;
}
