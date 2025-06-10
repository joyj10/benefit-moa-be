package com.benefitmoa.api.policy.dto;

import com.benefitmoa.domain.policy.entity.PolicyDetail;
import com.benefitmoa.domain.policy.entity.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDetailResponse {
    private Long policyDetailId;
    private String region;
    private String sourceUrl;
    private TargetType target;
    private String deadline;
    private String supportContent;
    private String applicationMethod;

    public static PolicyDetailResponse from(PolicyDetail policyDetail) {
        return PolicyDetailResponse.builder()
                .policyDetailId(policyDetail.getId())
                .region(policyDetail.getRegion())
                .sourceUrl(policyDetail.getSourceUrl())
                .target(policyDetail.getTarget())
                .supportContent(policyDetail.getSupportContent())
                .applicationMethod(policyDetail.getApplicationMethod())
                .build();
    }
}
