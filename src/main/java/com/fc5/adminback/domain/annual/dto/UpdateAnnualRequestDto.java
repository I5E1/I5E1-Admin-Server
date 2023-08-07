package com.fc5.adminback.domain.annual.dto;

import com.fc5.adminback.common.validator.annotation.DateConfirm;
import com.fc5.adminback.domain.model.Status;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString
@DateConfirm
public class UpdateAnnualRequestDto {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @NotEmpty
    private String summary;

    @NotEmpty
    @NotNull
    @Length(min = 1, max = 20)
    private String reason;

    @NotNull
    private Status status;
}
