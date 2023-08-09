package com.fc5.adminback.domain.annual.controller;

import com.fc5.adminback.common.exception.InvalidPageException;
import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.annual.dto.AnnualPagingResponseDto;
import com.fc5.adminback.domain.annual.dto.PageIndex;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualResponseDto;
import com.fc5.adminback.domain.annual.exception.errorcode.AnnualErrorCode;
import com.fc5.adminback.domain.annual.service.AnnualService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/annual")
public class AnnualController {

    private final AnnualService annualService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @ModelAttribute @Valid PageIndex pageIndex
    ) {

        int pageSize = 10;
        int currentPage = pageIndex.getPage();
        int totalCount = annualService.getCount();
        int totalPages = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        if (currentPage > totalPages) {
            throw new InvalidPageException(AnnualErrorCode.NOT_ENOUGH_ANNUAL_PAGE.getMessage(), AnnualErrorCode.NOT_ENOUGH_ANNUAL_PAGE);
        }
        AnnualPagingResponseDto result = annualService.getAllAnnuals(currentPage);
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }

    @PutMapping("/{annualId}")
    public ResponseEntity<?> update(@PathVariable Long annualId, @RequestBody @Valid UpdateAnnualRequestDto updateAnnualRequestDto) {
        UpdateAnnualResponseDto result = annualService.update(annualId, updateAnnualRequestDto);

        return APIDataResponse.of(HttpStatus.OK, "연차 수정에 성공하였습니다", result);
    }

    @DeleteMapping("/{annualId}")
    public ResponseEntity<?> delete(@PathVariable Long annualId) {
        annualService.delete(annualId);

        return APIDataResponse.empty(HttpStatus.OK, "연차 삭제에 성공하였습니다");
    }
}
