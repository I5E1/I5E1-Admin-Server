package com.fc5.adminback.domain.duty.service;

import com.fc5.adminback.common.exception.InvalidPageException;
import com.fc5.adminback.common.exception.NotFoundEntityException;
import com.fc5.adminback.domain.duty.dto.DutyPagingResponseDto;
import com.fc5.adminback.domain.duty.dto.DutyResponseDto;
import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
import com.fc5.adminback.domain.duty.exception.errorcode.DutyErrorCode;
import com.fc5.adminback.domain.duty.repository.DutyRepository;
import com.fc5.adminback.domain.model.Duty;
import com.fc5.adminback.domain.model.Member;
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
@Transactional
public class DutyService {

    private final DutyRepository dutyRepository;

    @Transactional(readOnly = true)
    public DutyPagingResponseDto getAll(int page) {
        Page<Duty> pages = getPages(page);

        int totalCount = Long.valueOf(dutyRepository.count()).intValue();
        List<DutyResponseDto> result = getPagingResult(pages);

        if (result.size() == 0) {
            throw new InvalidPageException(DutyErrorCode.NOT_ENOUGH_DUTY_PAGE.getMessage(), DutyErrorCode.NOT_ENOUGH_DUTY_PAGE);
        }

        return DutyPagingResponseDto.of(result, page, totalCount);
    }

    @Transactional(readOnly = true)
    public Duty get(Long dutyId) {
        return dutyRepository.findById(dutyId)
                .orElseThrow(() -> new NotFoundEntityException(DutyErrorCode.NOT_FOUND_DUTY.getMessage(), DutyErrorCode.NOT_FOUND_DUTY));
    }

    public void update(Long dutyId, UpdateDutyRequestDto updateDutyRequestDto) {
        Duty duty = get(dutyId);
        duty.updateByRequest(updateDutyRequestDto);
    }

    public void delete(Long dutyId) {
        Duty duty = get(dutyId);
        dutyRepository.delete(duty);
    }

    private List<DutyResponseDto> getPagingResult(Page<Duty> pages) {
        return pages.stream()
                .map(DutyResponseDto::of)
                .collect(Collectors.toList());
    }

    private Page<Duty> getPages(int page) {
        return dutyRepository.findAll(PageRequest.of(page - 1, 10, Sort.by(
                Sort.Order.desc("status"),
                Sort.Order.desc("createdAt")
        )));
    }

    public int getCount() {
        return Long.valueOf(dutyRepository.count()).intValue();
    }
}
