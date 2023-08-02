package com.fc5.adminback.domain.model;

import com.fc5.adminback.domain.annual.UpdateAnnualRequestDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

@Entity
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Annual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'REQUESTED'")
    private Status status;

    @Column(nullable = false, length = 20)
    private String summary;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Integer spentDays;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void updateByRequest(UpdateAnnualRequestDto updateAnnualRequestDto) {
        this.status = updateAnnualRequestDto.getStatus();
        this.updatedAt = LocalDateTime.now();
        this.reason = updateAnnualRequestDto.getReason();
        this.startDate = updateAnnualRequestDto.getStartDate();
        this.endDate = updateAnnualRequestDto.getEndDate();
        this.summary = updateAnnualRequestDto.getSummary();

        calculateSpentDays();
    }

    private void calculateSpentDays() {
        long size = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        long includedWeekendSize = IntStream.range(0, Long.valueOf(size).intValue())
                .mapToObj(day -> startDate.plusDays(day))
                .filter(localDate -> DayOfWeek.SATURDAY.equals(localDate.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(localDate.getDayOfWeek()))
                .count();

        // TODO : 계산한 spentDays가 member의 남은 연차 일 수 보다 크면 에러 처리

        this.spentDays = Long.valueOf(size - includedWeekendSize).intValue();

        
    }
}
