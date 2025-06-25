package com.benefitmoa.domain.policy.dto;

import com.benefitmoa.domain.policy.entity.Policy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyResponse {
    private Long policyId;
    private String title;
    private String userType;
    private String detailUrl;
    private String serviceSummary;
    private String category;
    private String selectionCriteria;
    private String supportContent;
    private String targetAudience;
    private String supportType;
    private String applicationPeriod;
    private String applicationMethod;
    private String contact;
    private String receptionAgency;
    private String serviceId;
    private String departmentName;
    private String organizationName;
    private String organizationType;
    private String organizationCode;
    private String policyCreatedAt;
    private String policyUpdatedAt;
    private int viewCount;

    public static PolicyResponse from(Policy policy) {
        return PolicyResponse.builder()
                .policyId(policy.getId())
                .title(policy.getTitle())
                .userType(policy.getUserType())
                .detailUrl(policy.getDetailUrl())
                .serviceSummary(policy.getServiceSummary())
                .category(policy.getCategory())
                .selectionCriteria(policy.getSelectionCriteria())
                .supportContent(policy.getSupportContent())
                .targetAudience(policy.getTargetAudience())
                .supportType(policy.getSupportType())
                .applicationPeriod(policy.getApplicationPeriod())
                .applicationMethod(policy.getApplicationMethod())
                .contact(policy.getContact())
                .receptionAgency(policy.getReceptionAgency())
                .serviceId(policy.getServiceId())
                .departmentName(policy.getDepartmentName())
                .organizationName(policy.getOrganizationName())
                .organizationType(policy.getOrganizationType())
                .organizationCode(policy.getOrganizationCode())
                .policyCreatedAt(policy.getPolicyCreatedAt())
                .policyUpdatedAt(policy.getPolicyUpdatedAt())
                .viewCount(policy.getViewCount())
                .build();
    }
}
