package com.fc5.adminback.domain.duty.controller;

import com.fc5.adminback.common.exception.InvalidPageException;
import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.annual.dto.PageIndex;
import com.fc5.adminback.domain.duty.dto.DutyPagingResponseDto;
import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
import com.fc5.adminback.domain.duty.exception.errorcode.DutyErrorCode;
import com.fc5.adminback.domain.duty.service.DutyService;
import com.fc5.adminback.domain.member.exception.errorcode.MemberErrorCode;
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
            @ModelAttribute @Valid PageIndex pageIndex
    ) {
        int pageSize = 10;
        int currentPage = pageIndex.getPage();
        int totalCount = dutyService.getCount();
        int totalPages = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        if (currentPage > totalPages) {
            throw new InvalidPageException(DutyErrorCode.NOT_ENOUGH_DUTY_PAGE.getMessage(), DutyErrorCode.NOT_ENOUGH_DUTY_PAGE);
        }
        DutyPagingResponseDto result = dutyService.getAll(pageIndex.getPage());

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
