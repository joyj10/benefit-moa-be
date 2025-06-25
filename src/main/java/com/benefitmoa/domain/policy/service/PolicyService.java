package com.benefitmoa.domain.policy.service;

import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import com.benefitmoa.global.exception.ErrorCode;
import com.benefitmoa.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PolicyService {
    private final PolicyRepository policyRepository;

    @Transactional
    public PolicyResponse create(PolicyRequest policyRequest) {
        Policy policy = Policy.from(policyRequest);
        return PolicyResponse.from(policyRepository.save(policy));
    }

    @Transactional
    public PolicyResponse update(Long policyId, PolicyRequest policyRequest) {
        Policy policy = this.getById(policyId);
        policy.update(policyRequest);

        return PolicyResponse.from(policy);
    }

    @Transactional
    public void delete(Long policyId) {
        Policy policy = this.getById(policyId);
        policyRepository.delete(policy);
    }

    @Transactional(readOnly = true)
    public Policy getById(Long policyId) {
        return policyRepository.findById(policyId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND.getMessage() + " (PolicyId: " + policyId + ")"));
    }

    @Transactional(readOnly = true)
    public PolicyResponse getPolicy(Long id) {
        Policy policy = this.getById(id);
        return PolicyResponse.from(policy);
    }

    @Transactional(readOnly = true)
    public Page<PolicyResponse> searchPolicies(PolicySearchCondition condition, Pageable pageable) {
        Page<Policy> policies = policyRepository.searchByCondition(condition, pageable);
        return policies.map(PolicyResponse::from);
    }
}
