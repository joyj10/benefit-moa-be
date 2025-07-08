package com.benefitmoa.batch.processor;

import com.benefitmoa.client.gov.dto.GovPolicyDto;
import com.benefitmoa.domain.policy.entity.Policy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class GovPolicyProcessor {
    public Policy convertToEntity(GovPolicyDto dto) {
        if (!StringUtils.hasText(dto.getServiceSummary())) {
            log.warn("💡 요약정보 누락 → '정보 없음'으로 대체됨: {}", dto.getServiceName());
            dto.setServiceSummary("정보 없음");
        }

        return Policy.from(dto);
    }
}
