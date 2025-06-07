package com.benefitmoa.api.policy;

import com.benefitmoa.domain.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policies")
public class PolicyController {
    private final PolicyService policyService;
}
