package com.fc5.adminback.domain.duty.repository;

import com.fc5.adminback.domain.model.Duty;
import com.fc5.adminback.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DutyRepository extends JpaRepository<Duty, Long> {
    @Query("SELECT d FROM Duty d " +
            "ORDER BY " +
            "CASE " +
            "WHEN d.status = 'REQUESTED' THEN 0 " +
            "WHEN d.status = 'APPROVED' THEN 1 " +
            "WHEN d.status = 'CANCELED' THEN 2 " +
            "WHEN d.status = 'REJECTED' THEN 3 " +
            "ELSE 4 END")
    Page<Duty> findAllWithOrder(PageRequest pageRequest);

    List<Duty> getDutyByStatusAndDutyDate(Status status, LocalDate dutyDate);

}
