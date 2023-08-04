package com.fc5.adminback.domain.annual;

import com.fc5.adminback.domain.annual.mock.MockPage;
import com.fc5.adminback.domain.annual.repository.AnnualRepository;
import com.fc5.adminback.domain.annual.service.AnnualService;
import com.fc5.adminback.domain.model.Annual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AnnualServiceTest {

    @InjectMocks
    private AnnualService annualService;

    @Mock
    private AnnualRepository annualRepository;

    @Test
    @DisplayName("모든 연차조회 및 페이징 테스트")
    void getAllAnualTest() {
        // given
        int pageIndex = 3;
        Page<Annual> mockPage = new MockPage(pageIndex);
        given(annualRepository.findAll(any(PageRequest.class))).willReturn(mockPage);

        // when
        Page<Annual> page = annualService.getAll(3);

        // then
        assertThat(page.getNumber()).isEqualTo(3);
        assertThat(page.getNumberOfElements()).isEqualTo(10);
    }
}