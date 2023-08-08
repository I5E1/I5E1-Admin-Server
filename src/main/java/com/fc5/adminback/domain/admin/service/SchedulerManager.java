package com.fc5.adminback.domain.admin.service;

import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.repository.AnnualRepository;
import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
import com.fc5.adminback.domain.duty.repository.DutyRepository;
import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Duty;
import com.fc5.adminback.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class SchedulerManager {

    private final AnnualRepository annualRepository;
    private final DutyRepository dutyRepository;


    @Scheduled(cron = "0 * * * * *")
    public void changeStatus() {
        System.out.println("크론 실행!!!");
        changeAnnualStatus();
        changeDutyStatus();
    }

    private void changeDutyStatus() {
        LocalDate today = LocalDate.now();
        /**
         * duty는 상태만 변경하면 됨
         * APPROVED -> COMPLETED
         * REQUESTED -> REJECTED
         */
        List<Duty> approvedDuties = dutyRepository.getDutyByStatusAndDutyDate(Status.APPROVED, today.minusDays(1L));
        UpdateDutyRequestDto completed = UpdateDutyRequestDto.builder()
                .status(Status.COMPLETED)
                .build();
        approvedDuties.forEach(duty -> duty.updateByRequest(completed));

        System.out.println(approvedDuties);
        List<Duty> requestedDuties = dutyRepository.getDutyByStatusAndDutyDate(Status.REQUESTED, today.minusDays(1L));
        UpdateDutyRequestDto rejected = UpdateDutyRequestDto.builder()
                .status(Status.REJECTED)
                .build();
        System.out.println(requestedDuties);
        requestedDuties.forEach(duty -> duty.updateByRequest(rejected));
    }

    private void changeAnnualStatus() {
        /**
         * annual은 상태 변경하고 연차 돌려줘야 되면 돌려줘야함
         * APPROVED -> COMPLETED (연차 그대로)
         * REQUESTED -> REJECTED (연차 돌려주기)
         */
        LocalDate today = LocalDate.now();
        List<Annual> approvedAnnuals = annualRepository.getAnnualByStatusAndEndDate(Status.APPROVED, today.minusDays(1L));
        UpdateAnnualRequestDto completed = UpdateAnnualRequestDto.builder()
                .status(Status.COMPLETED)
                .build();

        System.out.println(approvedAnnuals);
        approvedAnnuals.forEach(annual -> annual.updateStatus(completed));

        List<Annual> requestedAnnuals = annualRepository.getAnnualByStatusAndStartDate(Status.REQUESTED, today);
        UpdateAnnualRequestDto rejected = UpdateAnnualRequestDto.builder()
                .status(Status.REJECTED)
                .build();

        System.out.println(requestedAnnuals);

        requestedAnnuals.forEach(annual -> annual.updateWithGiveBackAnnualCount(rejected));
    }

}
