package com.benefitmoa.domain.policy.entity;

import com.benefitmoa.domain.common.BaseTimeEntity;
import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.domain.policy.dto.PublicDataPolicy;
import com.benefitmoa.global.exception.InvalidException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Policy extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;           // 정책명 (서비스명)

    @Column(nullable = false)
    private String userType;        // 사용자 구분 (개인/가구 등)

    @Column(length = 1000)
    private String detailUrl; // 정책 상세 조회 URL

    @Column(length = 1000, nullable = false)
    private String serviceSummary; // 서비스 목적 요약

    @Column(nullable = false)
    private String category; // 서비스 분야

    @Lob
    private String selectionCriteria; // 선정 기준

    @Lob
    private String supportContent; // 지원 내용

    @Lob
    private String targetAudience; // 지원 대상

    private String supportType; // 지원 유형

    @Column(length = 500)
    private String applicationPeriod; // 신청 기한

    @Column(length = 1000)
    private String applicationMethod; // 신청 방법

    private String contact; // 전화 문의
    private String receptionAgency; // 접수 기관

    private String serviceId; // 서비스 ID
    private String departmentName; // 부서명

    private String organizationName; // 소관 기관명
    private String organizationType; // 소관 기관 유형
    private String organizationCode; // 소관 기관 코드

    private String policyCreatedAt; // 정책 등록일시
    private String policyUpdatedAt; // 정책 수정일시

    private int viewCount; // 조회 수


    public static Policy from(PublicDataPolicy dto) {
        validate(dto.getTitle(), dto.getServiceSummary(), dto.getUserType(), dto.getCategory());
        return new Policy(
                dto.getTitle(),
                dto.getUserType(),
                dto.getDetailUrl(),
                dto.getServiceSummary(),
                dto.getCategory(),
                dto.getSelectionCriteria(),
                dto.getSupportContent(),
                dto.getTargetAudience(),
                dto.getSupportType(),
                dto.getApplicationPeriod(),
                dto.getApplicationMethod(),
                dto.getContact(),
                dto.getReceptionAgency(),
                dto.getServiceId(),
                dto.getDepartmentName(),
                dto.getOrganizationName(),
                dto.getOrganizationType(),
                dto.getOrganizationCode(),
                dto.getPolicyCreatedAt(),
                dto.getPolicyUpdatedAt(),
                dto.getViewCount()
        );
    }

    public static Policy from(PolicyRequest request) {
        validate(request.getTitle(), request.getServiceSummary(), request.getUserType(), request.getCategory());
        return new Policy(
                request.getTitle(),
                request.getUserType(),
                request.getDetailUrl(),
                request.getServiceSummary(),
                request.getCategory(),
                request.getSelectionCriteria(),
                request.getSupportContent(),
                request.getTargetAudience(),
                request.getSupportType(),
                request.getApplicationPeriod(),
                request.getApplicationMethod(),
                request.getContact(),
                request.getReceptionAgency(),
                request.getServiceId(),
                request.getDepartmentName(),
                request.getOrganizationName(),
                request.getOrganizationType(),
                request.getOrganizationCode(),
                null,
                null,
                0
        );
    }

    private Policy(
            String title,
            String userType,
            String detailUrl,
            String serviceSummary,
            String category,
            String selectionCriteria,
            String supportContent,
            String targetAudience,
            String supportType,
            String applicationPeriod,
            String applicationMethod,
            String contact,
            String receptionAgency,
            String serviceId,
            String departmentName,
            String organizationName,
            String organizationType,
            String organizationCode,
            String policyCreatedAt,
            String policyUpdatedAt,
            int viewCount
    ) {
        this.title = title;
        this.userType = userType;
        this.detailUrl = detailUrl;
        this.serviceSummary = serviceSummary;
        this.category = category;
        this.selectionCriteria = selectionCriteria;
        this.supportContent = supportContent;
        this.targetAudience = targetAudience;
        this.supportType = supportType;
        this.applicationPeriod = applicationPeriod;
        this.applicationMethod = applicationMethod;
        this.contact = contact;
        this.receptionAgency = receptionAgency;
        this.serviceId = serviceId;
        this.departmentName = departmentName;
        this.organizationName = organizationName;
        this.organizationType = organizationType;
        this.organizationCode = organizationCode;
        this.policyCreatedAt = policyCreatedAt;
        this.policyUpdatedAt = policyUpdatedAt;
        this.viewCount = viewCount;
    }

    public void update(PolicyRequest request) {
        validate(request.getTitle(), request.getServiceSummary(), request.getUserType(), request.getCategory());

        this.title = request.getTitle();
        this.userType = request.getUserType();
        this.detailUrl = request.getDetailUrl();
        this.serviceSummary = request.getServiceSummary();
        this.category = request.getCategory();
        this.selectionCriteria = request.getSelectionCriteria();
        this.supportContent = request.getSupportContent();
        this.targetAudience = request.getTargetAudience();
        this.supportType = request.getSupportType();
        this.applicationPeriod = request.getApplicationPeriod();
        this.applicationMethod = request.getApplicationMethod();
        this.contact = request.getContact();
        this.receptionAgency = request.getReceptionAgency();
        this.serviceId = request.getServiceId();
        this.departmentName = request.getDepartmentName();
        this.organizationName = request.getOrganizationName();
        this.organizationType = request.getOrganizationType();
        this.organizationCode = request.getOrganizationCode();
    }

    private static void validate(String title, String summary, String userType, String category) {
        if (title == null || title.isBlank()) {
            throw new InvalidException("정책 제목은 null이거나 공백일 수 없습니다.");
        }
        if (summary == null || summary.isBlank()) {
            throw new InvalidException("정책 요약은 null이거나 공백일 수 없습니다.");
        }
        if (userType == null || userType.isBlank()) {
            throw new InvalidException("사용자 구분은 null이거나 공백일 수 없습니다.");
        }
        if (category == null || category.isBlank()) {
            throw new InvalidException("서비스 분야는 null이거나 공백일 수 없습니다.");
        }
    }
}
