package com.benefitmoa.domain.policy.service;

import com.benefitmoa.api.policy.dto.CreatePolicyRequest;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.entity.PolicyDetail;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import com.benefitmoa.global.util.DateTimeUtils;
import jakarta.validation.Valid;
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
    public Policy create(CreatePolicyRequest policyRequest) {
        Policy policy = Policy.create(policyRequest.getTitle(), policyRequest.getSummary());
        policy.addDetail(buildPolicyDetail(policy, policyRequest));
        return policyRepository.save(policy);
    }

    private PolicyDetail buildPolicyDetail(Policy policy, CreatePolicyRequest request) {
        return PolicyDetail.create(
                policy,
                request.getRegion(),
                request.getSourceUrl(),
                request.getTarget(),
                DateTimeUtils.parse(request.getDeadline()),
                request.getSupportContent(),
                request.getApplicationMethod()
        );
    }
}
