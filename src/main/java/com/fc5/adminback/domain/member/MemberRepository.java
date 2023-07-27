package com.fc5.adminback.domain.member;

import com.fc5.adminback.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
