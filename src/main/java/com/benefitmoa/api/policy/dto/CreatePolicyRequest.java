package com.benefitmoa.api.policy.dto;

import com.benefitmoa.domain.policy.entity.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicyRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String summary;

    private String region;

    private String sourceUrl;

    @NotNull
    private TargetType target;

    private String deadline;

    private String supportContent;

    private String applicationMethod;
}
