package com.benefitmoa.domain.policy.service;

import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PolicyService {
    private final PolicyRepository policyRepository;

    @Transactional
    public Policy create(String title, String summary) {
        Policy policy = Policy.create(title, summary);
        policyRepository.save(policy);
        return policy;
    }
}
