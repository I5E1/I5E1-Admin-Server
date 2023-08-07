package com.fc5.adminback.domain.annual.service;

import com.fc5.adminback.common.exception.NotFoundEntityException;
import com.fc5.adminback.common.exception.OverlappingPeriodException;
import com.fc5.adminback.domain.annual.dto.AnnualPagingResponseDto;
import com.fc5.adminback.domain.annual.dto.AnnualResponseDto;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.exception.errorcode.AnnualErrorCode;
import com.fc5.adminback.domain.annual.repository.AnnualRepository;
import com.fc5.adminback.domain.model.Annual;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnnualService {

    private final AnnualRepository annualRepository;

    public Page<Annual> getAll(int page) {
        return annualRepository.findAll(PageRequest.of(page - 1, 10, Sort.by(
                Sort.Order.desc("status"),
                Sort.Order.desc("createdAt")
        )));
    }

    @Transactional
    public void update(Long annualId, UpdateAnnualRequestDto updateAnnualRequestDto) {
        Annual annual = get(annualId);
        annual.updateByRequest(updateAnnualRequestDto);
    }

    public Annual get(Long annualId) {
        return annualRepository.findById(annualId)
                .orElseThrow(() -> new NotFoundEntityException(AnnualErrorCode.NOT_FOUND_ANNUAL.getMessage(), AnnualErrorCode.NOT_FOUND_ANNUAL));
    }

    @Transactional
    public void delete(Long annualId) {
        Annual annual = get(annualId);
        annualRepository.delete(annual);
    }

    public AnnualPagingResponseDto getAllAnnuals(int currentPage) {
        Page<Annual> pages = getAll(currentPage);
        int totalPages = pages.getTotalPages();
        List<AnnualResponseDto> annuals = pages.stream()
                .map(AnnualResponseDto::of)
                .collect(Collectors.toList());
        return AnnualPagingResponseDto.of(annuals, currentPage, totalPages);
    }

    @Transactional
    public void validatePeriod(Long annualId, UpdateAnnualRequestDto updateAnnualRequestDto) {
        Annual annual = annualRepository.findById(annualId)
                .orElseThrow(() -> new NotFoundEntityException(AnnualErrorCode.NOT_FOUND_ANNUAL.getMessage(), AnnualErrorCode.NOT_FOUND_ANNUAL));

        List<Annual> annualsByInvalidDate = annualRepository.findAnnualByInvalidDate(annual.getMember().getId(), updateAnnualRequestDto.getStartDate(), updateAnnualRequestDto.getEndDate());

        if (annualsByInvalidDate.size() != 0) {
            throw new OverlappingPeriodException(AnnualErrorCode.OVERLAPPING_PERIOD.getMessage(), AnnualErrorCode.OVERLAPPING_PERIOD);
        }
    }
}
