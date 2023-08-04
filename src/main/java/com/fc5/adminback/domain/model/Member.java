package com.fc5.adminback.domain.model;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @ColumnDefault(value = "15")
    private int annualCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void modifiyAnnualCount(int size) {
        this.annualCount = size;
    }
}
