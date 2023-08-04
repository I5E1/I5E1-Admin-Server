package com.fc5.adminback.domain.duty;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.admin.AdminService;
import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import com.fc5.adminback.domain.annual.exception.UnauthorizedAdminException;
import com.fc5.adminback.domain.model.Admin;
import com.fc5.adminback.domain.model.Duty;
import com.fc5.adminback.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/duty")
public class DutyController {

    private final DutyService dutyService;

    @GetMapping
    public ResponseEntity<?> getAll(
            int page
    ) {
        DutyPagingResponseDto result = dutyService.getAll(page);

        return APIDataResponse.of(HttpStatus.OK, "모든 당직 조회에 성공하였습니다.", result);
    }

    @PutMapping("/{dutyId}")
    public ResponseEntity<?> update(@PathVariable Long dutyId, @RequestBody UpdateDutyRequestDto updateDutyRequestDto) {

        if (updateDutyRequestDto.getDutyDate().isAfter(LocalDate.now()) && updateDutyRequestDto.getStatus().equals(Status.COMPLETED)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }


        dutyService.update(dutyId, updateDutyRequestDto);

        return APIDataResponse.empty(HttpStatus.OK, "당직 수정에 성공하였습니다");
    }

    @DeleteMapping("/{dutyId}")
    public ResponseEntity<?> delete(@PathVariable Long dutyId) {
        dutyService.delete(dutyId);

        return APIDataResponse.empty(HttpStatus.OK, "당직 삭제에 성공하였습니다");
    }
}
