package com.benefitmoa.api.policy.dto;

import com.benefitmoa.domain.policy.entity.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDetailRequest {

    @NotNull
    private Long policyDetailId;

    @NotBlank
    private String region;

    private String sourceUrl;

    @NotBlank
    private TargetType target;

    private String deadline;

    private String supportContent;

    private String applicationMethod;
}
