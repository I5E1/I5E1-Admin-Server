package com.fc5.adminback.domain.duty;

import com.fc5.adminback.common.APIDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/duty")
public class DutyController {

    private final DutyService dutyService;

    @GetMapping
    public ResponseEntity<?> getAll(
//            @SessionAttribute(name = "adminId") Long adminId,
            int page
    ) {

        // TODO Session 구현되면 주석 풀기
//        Admin admin = adminService.getAdminById(adminId);
//        if (admin == null) {
//            throw new UnauthorizedAdminException(AnnualErrorCode.UNAUTHORIZED);
//        }
        List<DutyResponseDto> result = dutyService.getAll(page).stream()
                .map(DutyResponseDto::of)
                .collect(Collectors.toList());

        if (result.size() == 0) {
            throw new IllegalArgumentException("더 이상 당직 정보가 존재하지 않습니다.");
        }

        return APIDataResponse.of(HttpStatus.OK, "모든 당직 조회에 성공하였습니다.", result);
    }
}
