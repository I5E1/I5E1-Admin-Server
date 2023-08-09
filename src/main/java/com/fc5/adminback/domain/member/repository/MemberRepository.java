package com.fc5.adminback.domain.member.repository;

import com.fc5.adminback.domain.member.MemberWithCompletedDutyCount;
import com.fc5.adminback.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m.id as id, m.createdAt as createdAt, m.name as name, m.email as email, " +
            "m.position as position, m.annualCount as annualCount, m.tel as tel, " +
            "coalesce(SUM(CASE WHEN d.status = 'COMPLETED' AND d.dutyDate < CURDATE() THEN 1 ELSE 0 END), 0) as completedDutyCount " +
            "from Member m " +
            "left join Duty d " +
            "on m.id = d.member.id " +
            "group by m.id")
    List<MemberWithCompletedDutyCount> getAllMemberWithExecutedDutyCount(PageRequest pageRequest);

    @Query("SELECT m.id as id, m.createdAt as createdAt, m.name as name, m.email as email, " +
            "m.position as position, m.annualCount as annualCount, m.tel as tel, " +
            "coalesce(SUM(CASE WHEN d.status = 'COMPLETED' AND d.dutyDate < CURDATE() THEN 1 ELSE 0 END), 0) as completedDutyCount " +
            "from Member m " +
            "left join Duty d " +
            "on m.id = d.member.id " +
            "where m.name like %:query% " +
            "group by m.id")
    Page<MemberWithCompletedDutyCount> findByNameContainingWithExecutedDutyCount(Pageable pageable, @Param("query") String query);
}
