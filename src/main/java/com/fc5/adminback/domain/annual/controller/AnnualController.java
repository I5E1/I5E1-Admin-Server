package com.fc5.adminback.domain.annual.controller;

import com.fc5.adminback.common.response.APIDataResponse;
import com.fc5.adminback.domain.annual.dto.AnnualPagingResponseDto;
import com.fc5.adminback.domain.annual.dto.PageIndex;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.service.AnnualService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

        int currentPage = pageIndex.getPage();
        AnnualPagingResponseDto result = annualService.getAllAnnuals(currentPage);
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }

    @PutMapping("/{annualId}")
    public ResponseEntity<?> update(@PathVariable Long annualId, @RequestBody @Validated UpdateAnnualRequestDto updateAnnualRequestDto) {

        // TODO 수정하려는 기간의 시작 혹은 끝 중 하나의 시점이라도 이미 신청해둔 연차의 기간에 포함하면 예외 처리
        annualService.validatePeriod(annualId, updateAnnualRequestDto);


        annualService.update(annualId, updateAnnualRequestDto);

        return APIDataResponse.empty(HttpStatus.OK, "연차 수정에 성공하였습니다");
    }

    @DeleteMapping("/{annualId}")
    public ResponseEntity<?> delete(@PathVariable Long annualId) {
        annualService.delete(annualId);

        return APIDataResponse.empty(HttpStatus.OK, "연차 삭제에 성공하였습니다");
    }
}
