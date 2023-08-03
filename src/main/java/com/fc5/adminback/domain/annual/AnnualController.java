package com.fc5.adminback.domain.annual;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.admin.AdminService;
import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import com.fc5.adminback.domain.annual.exception.UnauthorizedAdminException;
import com.fc5.adminback.domain.model.Admin;
import com.fc5.adminback.domain.model.Annual;
import lombok.RequiredArgsConstructor;
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
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @SessionAttribute(name = "adminId") Long adminId,
            @ModelAttribute @Valid PageIndex pageIndex
    ) {

        Admin admin = adminService.getAdminById(adminId);
        if (admin == null) {
            throw new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED);
        }
        List<AnnualResponseDto> result = annualService.getAll(pageIndex.getPage()).stream()
                .map(AnnualResponseDto::of)
                .collect(Collectors.toList());
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }

    @PutMapping("/{annualId}")
    public ResponseEntity<?> update(@PathVariable Long annualId, @RequestBody UpdateAnnualRequestDto updateAnnualRequestDto) {

        if (updateAnnualRequestDto.getStartDate().isAfter(updateAnnualRequestDto.getEndDate())) {
            throw new IllegalArgumentException("올바르지 않은 기간입니다");
        }

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
