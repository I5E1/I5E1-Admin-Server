package com.fc5.adminback.domain.annual.service;

import com.fc5.adminback.common.exception.InvalidUpdateStatusException;
import com.fc5.adminback.common.exception.NotFoundEntityException;
import com.fc5.adminback.domain.annual.dto.AnnualPagingResponseDto;
import com.fc5.adminback.domain.annual.dto.AnnualResponseDto;
import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
import com.fc5.adminback.domain.annual.exception.errorcode.AnnualErrorCode;
import com.fc5.adminback.domain.annual.repository.AnnualRepository;
import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Status;
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

        validateRequest(annual, updateAnnualRequestDto);

        annual.updateByRequest(updateAnnualRequestDto);
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

    @Transactional
    public void validatePeriod(Long annualId, UpdateAnnualRequestDto updateAnnualRequestDto) {
        Annual annual = getAnnual(annualId);
        // TODO Paging Response Dto (멤버, 연차 당직) totalPages -> totalCount로 변경하기
        // TODO 신청 중 -> 미승인
        // TODO 승인완료 -> 승인
        // TODO 취소완료 -> 취소
        // TODO 요청반려 -> 반려
        // TODO 완료 -> 완료
        // TODO 연차, 당직 수정 -> status만 보내기
        // TODO Logout API 문서 작성
        // TODO Admin, Main 코드 서버에 반영하기
//        List<Annual> annualsByInvalidDate = annualRepository.findAnnualByInvalidDate(annual.getMember().getId(), updateAnnualRequestDto.getStartDate(), updateAnnualRequestDto.getEndDate());

//        if (annualsByInvalidDate.size() != 0) {
//            throw new OverlappingPeriodException(AnnualErrorCode.OVERLAPPING_PERIOD.getMessage(), AnnualErrorCode.OVERLAPPING_PERIOD);
//        }
    }

    private Annual getAnnual(Long annualId) {
        return annualRepository.findById(annualId)
                .orElseThrow(() -> new NotFoundEntityException(AnnualErrorCode.NOT_FOUND_ANNUAL.getMessage(), AnnualErrorCode.NOT_FOUND_ANNUAL));
    }

    public int getCount() {
        return Long.valueOf(annualRepository.count()).intValue();
    }
}
