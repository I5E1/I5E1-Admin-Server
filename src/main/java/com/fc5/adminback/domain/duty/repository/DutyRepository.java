package com.fc5.adminback.domain.duty.repository;

import com.fc5.adminback.domain.model.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty, Long> {
}
