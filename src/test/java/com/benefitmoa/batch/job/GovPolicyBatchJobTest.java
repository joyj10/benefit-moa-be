package com.benefitmoa.batch.job;

import com.benefitmoa.batch.processor.GovPolicyProcessor;
import com.benefitmoa.batch.writer.GovPolicyWriter;
import com.benefitmoa.client.gov.GovApiService;
import com.benefitmoa.client.gov.dto.GovPolicyDto;
import com.benefitmoa.domain.policy.entity.Policy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("정부 정책 배치 잡 테스트")
@ExtendWith(MockitoExtension.class)
class GovPolicyBatchJobTest {
    @Mock
    private GovApiService govApiService;

    @Mock
    private GovPolicyProcessor processor;

    @Mock
    private GovPolicyWriter writer;

    @InjectMocks
    private GovPolicyBatchJob batchJob;

    @Test
    @DisplayName("정책 배치 잡 실행 - 성공: 정책 저장 확인")
    void testExecute_success() {
        // given
        GovPolicyDto dto = new GovPolicyDto();
        dto.setServiceName("청년 주거 지원");
        dto.setServiceSummary("요약");
        dto.setUserType("청년");
        dto.setServiceCategory("주거");

        Policy entity = Policy.from(dto);
        when(govApiService.fetchServices(1, 20)).thenReturn(List.of(dto));
        when(processor.convertToEntity(dto)).thenReturn(entity);

        // when
        batchJob.execute();

        // then
        verify(writer).savePolicy(entity);
    }
}
