package com.fc5.adminback.domain.duty.dto;

import com.fc5.adminback.domain.model.Status;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateDutyRequestDto {

    private LocalDate dutyDate;
    private String reason;
    private Status status;
}
