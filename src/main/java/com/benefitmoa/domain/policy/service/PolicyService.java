package com.benefitmoa.domain.policy.service;

import com.benefitmoa.domain.policy.dto.PolicyDetailRequest;
import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.entity.PolicyDetail;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import com.benefitmoa.global.exception.ErrorCode;
import com.benefitmoa.global.exception.NotFoundException;
import com.benefitmoa.global.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PolicyService {
    private final PolicyRepository policyRepository;

    @Transactional
    public Policy create(PolicyRequest policyRequest) {
        Policy policy = Policy.create(policyRequest.getTitle(), policyRequest.getSummary());
        policy.addDetails(buildPolicyDetails(policy, policyRequest.getPolicies()));
        return policyRepository.save(policy);
    }

    private List<PolicyDetail> buildPolicyDetails(Policy policy, List<PolicyDetailRequest> detailRequests) {
        if (detailRequests == null || detailRequests.isEmpty()) {
            return Collections.emptyList();
        }

        return detailRequests.stream()
                .map(detailRequest ->
                        PolicyDetail.create(
                            policy,
                            detailRequest.getRegion(),
                            detailRequest.getSourceUrl(),
                            detailRequest.getTarget(),
                            DateTimeUtils.parse(detailRequest.getDeadline()),
                            detailRequest.getSupportContent(),
                            detailRequest.getApplicationMethod()
                ))
                .toList();
    }

    @Transactional
    public Policy update(Long policyId, PolicyRequest policyRequest) {
        Policy policy = this.getById(policyId);
        policy.update(policyRequest.getTitle(), policyRequest.getSummary());

        // 기존 details 비교 및 수정
        Map<Long, PolicyDetail> existingDetails = policy.getDetails().stream()
                .collect(Collectors.toMap(PolicyDetail::getId, Function.identity()));

        List<PolicyDetail> updatedDetails = new ArrayList<>();

        for (PolicyDetailRequest detailRequest : policyRequest.getPolicies()) {
            Long detailId = detailRequest.getPolicyDetailId();
            PolicyDetail detail = existingDetails.get(detailId);

            if (detail != null) {
                detail.update(
                        detailRequest.getRegion(),
                        detailRequest.getSourceUrl(),
                        detailRequest.getTarget(),
                        DateTimeUtils.parse(detailRequest.getDeadline()),
                        detailRequest.getSupportContent(),
                        detailRequest.getApplicationMethod()
                );
                updatedDetails.add(detail);
                existingDetails.remove(detailId); // 남은 건 삭제 대상
            } else {
                PolicyDetail newDetail = PolicyDetail.create(
                        policy,
                        detailRequest.getRegion(),
                        detailRequest.getSourceUrl(),
                        detailRequest.getTarget(),
                        DateTimeUtils.parse(detailRequest.getDeadline()),
                        detailRequest.getSupportContent(),
                        detailRequest.getApplicationMethod()
                );
                updatedDetails.add(newDetail);
            }
        }

        // PolicyDetail 삭제: 요청에 없는 기존 디테일
        for (PolicyDetail toRemove : existingDetails.values()) {
            policy.removeDetail(toRemove);
        }

        // 업데이트된 리스트로 교체
        policy.clearDetails();
        policy.addDetails(updatedDetails);

        return policy;
    }

    @Transactional
    public void delete(Long policyId) {
        Policy policy = this.getById(policyId);
        policy.clearDetails();
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
