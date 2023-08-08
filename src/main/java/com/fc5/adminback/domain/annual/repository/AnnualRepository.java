package com.fc5.adminback.domain.annual.repository;

import com.fc5.adminback.domain.model.Annual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AnnualRepository extends JpaRepository<Annual, Long> {

    @Query("SELECT a FROM Annual a " +
            "WHERE " +
            "a.member.id = :memberId and " +
            "(a.startDate <= :startDate and :startDate <= a.endDate) " +
            "or " +
            "(a.startDate <= :endDate and :endDate <= a.endDate)")
    List<Annual> findAnnualByInvalidDate(
            @Param("memberId") Long memberId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT a FROM Annual a " +
            "ORDER BY " +
            "CASE " +
            "WHEN a.status = 'REQUESTED' THEN 0 " +
            "WHEN a.status = 'APPROVED' THEN 1 " +
            "WHEN a.status = 'CANCELED' THEN 2 " +
            "WHEN a.status = 'REJECTED' THEN 3 " +
            "ELSE 4 END")
    Page<Annual> findAllWithOrder(PageRequest pageRequest);
}
