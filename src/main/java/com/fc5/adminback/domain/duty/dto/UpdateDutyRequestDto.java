package com.fc5.adminback.domain.duty.dto;

import com.fc5.adminback.common.validator.annotation.DateConfirm;
import com.fc5.adminback.domain.model.Status;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@DateConfirm
public class UpdateDutyRequestDto {

    @NotNull
    private LocalDate dutyDate;

    @NotEmpty
    @NotNull
    @Length(min = 1, max = 20)
    private String reason;

    @NotNull
    private Status status;
}
