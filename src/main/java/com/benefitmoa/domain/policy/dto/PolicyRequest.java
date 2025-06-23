package com.benefitmoa.domain.policy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String summary;

    private List<PolicyDetailRequest> policies;
}
