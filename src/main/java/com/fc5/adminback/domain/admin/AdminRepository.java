package com.fc5.adminback.domain.admin;

import com.fc5.adminback.domain.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}