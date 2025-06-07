package com.benefitmoa.api.policy.dto;

import com.benefitmoa.domain.policy.entity.Policy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponse {
    private Long id;
    private String title;
    private String summary;
    private int viewCount;

    public static PolicyResponse from(Policy policy) {
        return new PolicyResponse(
                policy.getId(),
                policy.getTitle(),
                policy.getSummary(),
                policy.getViewCount()
        );
    }
}
