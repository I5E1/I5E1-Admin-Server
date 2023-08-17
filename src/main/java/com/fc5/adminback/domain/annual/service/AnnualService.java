package com.fc5.adminback.domain.annual.service;

import com.fc5.adminback.common.exception.InvalidUpdateStatusException;
import com.fc5.adminback.common.exception.NotFoundEntityException;
import com.fc5.adminback.domain.annual.dto.AnnualPagingResponseDto;
import com.fc5.adminback.domain.annual.dto.AnnualResponseDto;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualResponseDto;
import com.fc5.adminback.domain.annual.exception.errorcode.AnnualErrorCode;
import com.fc5.adminback.domain.annual.repository.AnnualRepository;
import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return annualRepository.findAllWithOrder(PageRequest.of(page - 1, 10));
    }

    @Transactional
    public UpdateAnnualResponseDto update(Long annualId, UpdateAnnualRequestDto updateAnnualRequestDto) {
        Annual annual = get(annualId);

        validateRequest(annual, updateAnnualRequestDto);

        annual.updateByRequest(updateAnnualRequestDto);

        UpdateAnnualResponseDto result = UpdateAnnualResponseDto.builder()
                .status(annual.getStatus().getDescription())
                .build();

        return result;
    }

    private void validateRequest(Annual annual, UpdateAnnualRequestDto updateAnnualRequestDto) {
        if (!(annual.getStatus().equals(Status.REQUESTED) || annual.getStatus().equals(Status.APPROVED))) {
            throw new InvalidUpdateStatusException(AnnualErrorCode.INVALID_UPDATE_STATUS.getMessage(), AnnualErrorCode.INVALID_UPDATE_STATUS);
        }

        if (annual.getStatus().equals(Status.REQUESTED)) {
            if (!(updateAnnualRequestDto.getStatus().equals(Status.APPROVED) || updateAnnualRequestDto.getStatus().equals(Status.REJECTED))) {
                throw new InvalidUpdateStatusException(AnnualErrorCode.INVALID_UPDATE_STATUS.getMessage(), AnnualErrorCode.INVALID_UPDATE_STATUS);
            }
            return;
        }

        if (!updateAnnualRequestDto.getStatus().equals(Status.REJECTED)) {
            throw new InvalidUpdateStatusException(AnnualErrorCode.INVALID_UPDATE_STATUS.getMessage(), AnnualErrorCode.INVALID_UPDATE_STATUS);
        }
    }

    public Annual get(Long annualId) {
        return getAnnual(annualId);
    }

    @Transactional
    public void delete(Long annualId) {
        Annual annual = get(annualId);
        annualRepository.delete(annual);
    }

    public AnnualPagingResponseDto getAllAnnuals(int currentPage) {
        Page<Annual> pages = getAll(currentPage);
        int totalCount = annualRepository.findAll().size();
        List<AnnualResponseDto> annuals = pages.stream()
                .map(AnnualResponseDto::of)
                .collect(Collectors.toList());
        return AnnualPagingResponseDto.of(annuals, currentPage, totalCount);
    }

    private Annual getAnnual(Long annualId) {
        return annualRepository.findById(annualId)
                .orElseThrow(() -> new NotFoundEntityException(AnnualErrorCode.NOT_FOUND_ANNUAL.getMessage(), AnnualErrorCode.NOT_FOUND_ANNUAL));
    }

    public int getCount() {
        return Long.valueOf(annualRepository.count()).intValue();
    }
}
