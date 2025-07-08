package com.benefitmoa.batch.job;

import com.benefitmoa.batch.processor.GovPolicyProcessor;
import com.benefitmoa.batch.writer.GovPolicyWriter;
import com.benefitmoa.client.gov.GovApiService;
import com.benefitmoa.client.gov.dto.GovPolicyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GovPolicyBatchJob {

    private final GovApiService govApiService;
    private final GovPolicyProcessor processor;
    private final GovPolicyWriter writer;

    public void execute() {
        int page = 1;
        int size = 20;
        int totalSaved = 0;

        try {
            while (true) {
                List<GovPolicyDto> govPolicies = govApiService.fetchServices(page, size);
                if (govPolicies.isEmpty()) break;

                log.info("📦 page={} 수집 정책 수: {}", page, govPolicies.size());

                govPolicies.stream()
                        .map(processor::convertToEntity)
                        .forEach(writer::savePolicy);

                totalSaved += govPolicies.size();
                page++;
            }

            log.info("✅ 전체 저장 완료. 총 {}건 저장됨", totalSaved);

        } catch (Exception e) {
            log.error("❌ 정책 배치 실행 중 예외 발생", e);
        }
    }
}
