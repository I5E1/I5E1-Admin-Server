package com.fc5.adminback.domain.annual;

import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.model.Annual;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/annual")
public class AnnualController {

    private final AnnualService annualService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<AnnualResponseDto> result = annualService.getAll().stream()
                .map(AnnualResponseDto::of)
                .collect(Collectors.toList());
        return APIDataResponse.of(HttpStatus.OK, "모든 연차 조회에 성공하였습니다.", result);
    }
}
