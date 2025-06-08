package com.benefitmoa.api.policy;

import com.benefitmoa.api.policy.dto.CreatePolicyRequest;
import com.benefitmoa.api.policy.dto.PolicyResponse;
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
    public ApiResponse<PolicyResponse> createPolicy(@RequestBody @Valid CreatePolicyRequest policyRequest) {
        Policy policy = policyService.create(policyRequest);
        return ApiResponse.success(PolicyResponse.from(policy));
    }
}