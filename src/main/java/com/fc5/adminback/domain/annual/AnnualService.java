package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.model.Annual;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnualService {

    private final AnnualRepository annualRepository;
    public Page<Annual> getAll(int page) {
        return annualRepository.findAll(PageRequest.of(page - 1, 2, Sort.by(
                Sort.Order.desc("status"),
                Sort.Order.desc("createdAt")
        )));
    }
}
