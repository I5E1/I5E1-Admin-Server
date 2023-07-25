package com.fc5.adminback.domain.model;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 60, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 12)
    private String tel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'STAFF'")
    private Position position;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int annualCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
