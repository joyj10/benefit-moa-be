package com.benefitmoa.api.policy;

import com.benefitmoa.api.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
