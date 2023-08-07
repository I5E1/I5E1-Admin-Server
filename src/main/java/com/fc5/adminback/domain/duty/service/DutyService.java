package com.fc5.adminback.domain.duty.service;

import com.fc5.adminback.domain.duty.dto.DutyPagingResponseDto;
import com.fc5.adminback.domain.duty.dto.DutyResponseDto;
import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
import com.fc5.adminback.domain.duty.repository.DutyRepository;
import com.fc5.adminback.domain.model.Duty;
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

    public DutyPagingResponseDto getAll(int page) {
        Page<Duty> pages = getPages(page);

        int totalPage = pages.getTotalPages();
        List<DutyResponseDto> result = getPagingResult(pages);

        if (result.size() == 0) {
            throw new IllegalArgumentException("더 이상 당직 정보가 존재하지 않습니다.");
        }

        return DutyPagingResponseDto.of(result, page, totalPage);
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

    public Duty get(Long dutyId) {
        return dutyRepository.findById(dutyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 당직 정보입니다"));
    }

    public void update(Long dutyId, UpdateDutyRequestDto updateDutyRequestDto) {
        Duty duty = get(dutyId);
        duty.updateByRequest(updateDutyRequestDto);

        dutyRepository.save(duty);
    }

    public void delete(Long dutyId) {
        Duty duty = get(dutyId);
        dutyRepository.delete(duty);
    }
}
