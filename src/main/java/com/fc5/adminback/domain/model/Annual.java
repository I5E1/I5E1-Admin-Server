package com.fc5.adminback.domain.model;

import com.fc5.adminback.common.exception.InvalidUpdateStatusException;
import com.fc5.adminback.common.exception.NotEnoughAnnualCountException;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.exception.errorcode.AnnualErrorCode;
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
@Table(
        name = "annual",
        uniqueConstraints =
        @UniqueConstraint(
                name = "MEMBER_STARTDATE_ENDDATE_UNIQUE_IDX",
                columnNames = {"member_id", "start_date", "end_date"}
        )
)
public class Annual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
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
        if (status.equals(Status.REQUESTED) && updateAnnualRequestDto.getStatus().equals(Status.APPROVED)) {
            updateStatus(updateAnnualRequestDto);
            calculateSpentDays();
            return;
        }

        if (status.equals(Status.REQUESTED) && updateAnnualRequestDto.getStatus().equals(Status.REJECTED)) {
            updateWithGiveBackAnnualCount(updateAnnualRequestDto);
            calculateSpentDays();
            return;
        }

        if (status.equals(Status.APPROVED) && updateAnnualRequestDto.getStatus().equals(Status.REJECTED)) {
            updateWithGiveBackAnnualCount(updateAnnualRequestDto);
            calculateSpentDays();
            return;
        }

        throw new InvalidUpdateStatusException(AnnualErrorCode.INVALID_UPDATE_STATUS.getMessage(), AnnualErrorCode.INVALID_UPDATE_STATUS);
    }

    public void updateWithGiveBackAnnualCount(UpdateAnnualRequestDto updateAnnualRequestDto) {
        updateStatus(updateAnnualRequestDto);
        calculateSpentDays();
        this.member.modifiyAnnualCount(this.member.getAnnualCount() + this.spentDays);
    }

    public void updateStatus(UpdateAnnualRequestDto updateAnnualRequestDto) {
        this.status = updateAnnualRequestDto.getStatus();
        this.updatedAt = LocalDateTime.now();
    }

    private void calculateSpentDays() {
        long size = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        long includedWeekendSize = IntStream.range(0, Long.valueOf(size).intValue())
                .mapToObj(day -> startDate.plusDays(day))
                .filter(localDate -> DayOfWeek.SATURDAY.equals(localDate.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(localDate.getDayOfWeek()))
                .count();

        int result = Long.valueOf(size - includedWeekendSize).intValue();

        this.spentDays = result;
    }
}
