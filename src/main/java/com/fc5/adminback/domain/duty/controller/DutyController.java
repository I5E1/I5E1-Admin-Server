package com.fc5.adminback.domain.duty.controller;

import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.duty.dto.DutyPagingResponseDto;
import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
import com.fc5.adminback.domain.duty.service.DutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> update(@PathVariable Long dutyId, @RequestBody @Valid UpdateDutyRequestDto updateDutyRequestDto) {

        dutyService.update(dutyId, updateDutyRequestDto);

        return APIDataResponse.empty(HttpStatus.OK, "당직 수정에 성공하였습니다");
    }

    @DeleteMapping("/{dutyId}")
    public ResponseEntity<?> delete(@PathVariable Long dutyId) {
        dutyService.delete(dutyId);

        return APIDataResponse.empty(HttpStatus.OK, "당직 삭제에 성공하였습니다");
    }
}
