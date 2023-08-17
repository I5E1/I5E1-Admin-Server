package com.fc5.adminback.domain.duty.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateDutyRequestDto {

    @NotNull
    private Status status;
}
