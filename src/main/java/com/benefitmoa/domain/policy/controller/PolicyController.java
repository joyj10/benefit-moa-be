package com.benefitmoa.domain.policy.controller;

import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.dto.PolicySearchRequest;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policies")
public class PolicyController {
    private final PolicyService policyService;

    @GetMapping("/{id}")
    public ApiResponse<PolicyResponse> getPolicy(@PathVariable Long id) {
        PolicyResponse result = policyService.getPolicy(id);
        return ApiResponse.success(result);
    }

    @GetMapping
    public ApiResponse<Page<PolicyResponse>> getPolicies(@ModelAttribute PolicySearchRequest request) {
        Pageable pageable = request.toPageable();
        PolicySearchCondition condition = request.toCondition();

        Page<PolicyResponse> result = policyService.searchPolicies(condition, pageable);
        return ApiResponse.success(result);
    }
}
