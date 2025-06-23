package com.benefitmoa.domain.policy.controller;

import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/policies")
public class AdminPolicyController {
    private final PolicyService policyService;

    @PostMapping
    public ApiResponse<PolicyResponse> createPolicy(@RequestBody @Valid PolicyRequest policyRequest) {
        Policy policy = policyService.create(policyRequest);
        return ApiResponse.success(PolicyResponse.from(policy));
    }

    @PatchMapping("/{id}")
    public ApiResponse<PolicyResponse> updatePolicy(@PathVariable Long id,
                                                    @RequestBody @Valid PolicyRequest policyRequest) {
        Policy policy = policyService.update(id, policyRequest);
        return ApiResponse.success(PolicyResponse.from(policy));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePolicy(@PathVariable Long id) {
        policyService.delete(id);
        return ApiResponse.success(null);
    }

}
