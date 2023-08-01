package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.model.Status;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class UpdateAnnualRequestDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private String summary;
    private String reason;
    private Status status;

}
