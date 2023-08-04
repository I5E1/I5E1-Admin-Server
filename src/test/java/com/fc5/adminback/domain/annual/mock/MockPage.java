package com.fc5.adminback.domain.annual.mock;

import com.fc5.adminback.domain.model.Annual;
import com.fc5.adminback.domain.model.Member;
import com.fc5.adminback.domain.model.Position;
import com.fc5.adminback.domain.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MockPage implements Page<Annual> {

    private final int pageIndex;
    public MockPage(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }

    @Override
    public long getTotalElements() {
        return 0;
    }

    @Override
    public Page map(Function converter) {
        return null;
    }

    @Override
    public int getNumber() {
        return pageIndex;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getNumberOfElements() {
        return 10;
    }

    @Override
    public List getContent() {
        Member member = Member.builder()
                .email("qwer@naver.com")
                .password("123123")
                .tel("01012345678")
                .annualCount(3)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .position(Position.STAFF)
                .name("킴")
                .build();

        List<Annual> result = LongStream.range(1, 11)
                .mapToObj(idx -> {
                    return Annual.builder()
                            .id(idx)
                            .startDate(LocalDate.now())
                            .endDate(LocalDate.now())
                            .member(member)
                            .status(Status.APPROVED)
                            .summary("휴가")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .reason("휴가간다고")
                            .spentDays(1)
                            .build();
                })
                .collect(Collectors.toList());

        return result;

    }

    @Override
    public Stream<Annual> stream() {
        return getContent().stream();
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
