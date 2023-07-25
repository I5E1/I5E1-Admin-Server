package com.fc5.adminback.domain.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Annual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'REQUESTED'")
    private Status status;

    @Column(nullable = false)
    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
