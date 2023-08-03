package com.fc5.adminback.domain.duty;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.admin.AdminService;
import com.fc5.adminback.domain.annual.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import com.fc5.adminback.domain.annual.exception.UnauthorizedAdminException;
import com.fc5.adminback.domain.model.Admin;
import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Duty;
import com.fc5.adminback.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/duty")
public class DutyController {

    private final DutyService dutyService;
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @SessionAttribute(name = "adminId") Long adminId,
            int page
    ) {

        Admin admin = adminService.getAdminById(adminId);
        if (admin == null) {
            throw new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED);
        }
        List<DutyResponseDto> result = dutyService.getAll(page).stream()
                .map(DutyResponseDto::of)
                .collect(Collectors.toList());

        if (result.size() == 0) {
            throw new IllegalArgumentException("더 이상 당직 정보가 존재하지 않습니다.");
        }

        return APIDataResponse.of(HttpStatus.OK, "모든 당직 조회에 성공하였습니다.", result);
    }

    @PutMapping("/{dutyId}")
    public ResponseEntity<?> update(@PathVariable Long dutyId, @RequestBody UpdateDutyRequestDto updateDutyRequestDto) {

        if (updateDutyRequestDto.getDutyDate().isAfter(LocalDate.now()) && updateDutyRequestDto.getStatus().equals(Status.COMPLETED)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }

        Duty duty = dutyService.get(dutyId);

        dutyService.update(duty, updateDutyRequestDto);

        return APIDataResponse.empty(HttpStatus.OK, "연차 수정에 성공하였습니다");
    }

    @DeleteMapping("/{dutyId}")
    public ResponseEntity<?> delete(@PathVariable Long dutyId) {
        Duty duty = dutyService.get(dutyId);
        dutyService.delete(duty);

        return APIDataResponse.empty(HttpStatus.OK, "당직 삭제에 성공하였습니다");
    }
}
