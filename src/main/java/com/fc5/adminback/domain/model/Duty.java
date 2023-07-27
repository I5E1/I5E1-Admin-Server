package com.fc5.adminback.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
public class Duty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(nullable = false)
    private Date dutyDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'REQUESTED'")
    private Status status;

    @Column(nullable = false)
    @ColumnDefault("'의무 당직'")
    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
