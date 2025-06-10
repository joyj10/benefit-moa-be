package com.benefitmoa.api.policy.dto;

import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.entity.PolicyDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyResponse {
    private Long policyId;
    private String title;
    private String summary;
    private int viewCount;
    private List<PolicyDetailResponse> detailResponses;

    public static PolicyResponse from(Policy policy) {
        return from(policy, convertDetails(policy));
    }

    private static List<PolicyDetailResponse> convertDetails(Policy policy) {
        return policy.getDetails().stream().map(PolicyDetailResponse::from).toList();
    }

    public static PolicyResponse from(Policy policy, List<PolicyDetailResponse> detailResponses) {
        return PolicyResponse.builder()
                .policyId(policy.getId())
                .title(policy.getTitle())
                .summary(policy.getSummary())
                .viewCount(policy.getViewCount())
                .detailResponses(detailResponses != null ? detailResponses : Collections.emptyList())
                .build();
    }
}
