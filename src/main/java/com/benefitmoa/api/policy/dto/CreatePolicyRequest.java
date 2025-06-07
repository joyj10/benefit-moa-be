package com.benefitmoa.api.policy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreatePolicyRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String summary;
}
