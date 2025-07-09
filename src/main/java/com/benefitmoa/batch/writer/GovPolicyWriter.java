package com.benefitmoa.batch.writer;

import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GovPolicyWriter {
    private final PolicyRepository policyRepository;

    public void savePolicy(Policy policy) {
        // 추후 중복 체크 로직 추가 예정
        policyRepository.save(policy);
    }
}
