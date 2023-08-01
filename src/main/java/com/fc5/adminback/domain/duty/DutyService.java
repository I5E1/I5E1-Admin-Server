package com.fc5.adminback.domain.duty;

import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Duty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DutyService {

    private final DutyRepository dutyRepository;

    public Page<Duty> getAll(int page) {
        return dutyRepository.findAll(PageRequest.of(page - 1, 2, Sort.by(
                Sort.Order.desc("status"),
                Sort.Order.desc("createdAt")
        )));
    }

    public Duty get(Long dutyId) {
        return dutyRepository.findById(dutyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 당직 정보입니다"));
    }

    public void update(Duty duty, UpdateDutyRequestDto updateDutyRequestDto) {
        duty.updateByRequest(updateDutyRequestDto);

        dutyRepository.save(duty);
    }
}
