package com.fc5.adminback.domain.annual;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.model.Annual;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/annual")
public class AnnualController {

    private final AnnualService annualService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @ModelAttribute @Valid PageIndex pageIndex
    ) {

        Page<Annual> all = annualService.getAll(pageIndex.getPage());
        int totalPages = all.getTotalPages();
        List<AnnualResponseDto> annuals = all.stream()
                .map(AnnualResponseDto::of)
                .collect(Collectors.toList());
        AnnualPagingResponseDto result = AnnualPagingResponseDto.of(annuals, pageIndex.getPage(), totalPages);
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }

    @PutMapping("/{annualId}")
    public ResponseEntity<?> update(@PathVariable Long annualId, @RequestBody UpdateAnnualRequestDto updateAnnualRequestDto) {

        if (updateAnnualRequestDto.getStartDate().isAfter(updateAnnualRequestDto.getEndDate())) {
            throw new IllegalArgumentException("올바르지 않은 기간입니다");
        }

        // TODO 수정하려는 기간의 시작 혹은 끝 중 하나의 시점이라도 이미 신청해둔 연차의 기간에 포함하면 예외 처리

        Annual annual = annualService.get(annualId);

        annualService.update(annual, updateAnnualRequestDto);

        return APIDataResponse.empty(HttpStatus.OK, "연차 수정에 성공하였습니다");
    }

    @DeleteMapping("/{annualId}")
    public ResponseEntity<?> delete(@PathVariable Long annualId) {
        Annual annual = annualService.get(annualId);
        annualService.delete(annual);

        return APIDataResponse.empty(HttpStatus.OK, "연차 삭제에 성공하였습니다");
    }
}
