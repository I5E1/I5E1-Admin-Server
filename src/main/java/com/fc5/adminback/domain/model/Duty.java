package com.fc5.adminback.domain.model;

import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Getter
@Table(
        name = "duty",
        uniqueConstraints =
        @UniqueConstraint(
                name = "MEMBER_DUTYDATE_UNIQUE_IDX",
                columnNames = {"member_id", "duty_date"}
        )
)
public class Duty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(nullable = false, name = "duty_date")
    private LocalDate dutyDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'REQUESTED'")
    private Status status;

    @Column(nullable = false)
    @ColumnDefault("'의무 당직'")
    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void updateByRequest(UpdateDutyRequestDto updateDutyRequestDto) {
        this.status = updateDutyRequestDto.getStatus();
        this.updatedAt = LocalDateTime.now();
        this.reason = updateDutyRequestDto.getReason();
        this.dutyDate = updateDutyRequestDto.getDutyDate();
    }
}
