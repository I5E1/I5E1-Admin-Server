package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.model.Annual;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnualService {

    private final AnnualRepository annualRepository;
    public Page<Annual> getAll(int page) {
        return annualRepository.findAll(PageRequest.of(page - 1, 2, Sort.by(
                Sort.Order.desc("status"),
                Sort.Order.desc("createdAt")
        )));
    }

    public void update(Annual annual, UpdateAnnualRequestDto updateAnnualRequestDto) {
        annual.updateByRequest(updateAnnualRequestDto);

        annualRepository.save(annual);
    }

    public Annual get(Long annualId) {
        return annualRepository.findById(annualId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 연차입니다."));
    }
}
