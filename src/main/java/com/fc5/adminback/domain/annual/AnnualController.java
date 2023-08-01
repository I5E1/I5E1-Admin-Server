package com.fc5.adminback.domain.annual;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import com.fc5.adminback.domain.annual.exception.UnauthorizedAdminException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//            @SessionAttribute(name = "adminId") Long adminId,
            @ModelAttribute @Valid PageIndex pageIndex
    ) {

        // TODO Session 구현되면 주석 풀기
//        Admin admin = adminService.getAdminById(adminId);
//        if (admin == null) {
//            throw new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED);
//        }
        List<AnnualResponseDto> result = annualService.getAll(pageIndex.getPage()).stream()
                .map(AnnualResponseDto::of)
                .collect(Collectors.toList());
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }
}
