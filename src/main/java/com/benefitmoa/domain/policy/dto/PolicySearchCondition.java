package com.benefitmoa.domain.policy.dto;

import com.benefitmoa.domain.policy.entity.TargetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicySearchCondition {
    private String region;
    private TargetType target;
    private String keyword;
}
