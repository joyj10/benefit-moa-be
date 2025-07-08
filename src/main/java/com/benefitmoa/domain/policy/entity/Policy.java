package com.benefitmoa.domain.policy.entity;

import com.benefitmoa.client.gov.dto.GovPolicyDto;
import com.benefitmoa.domain.common.BaseTimeEntity;
import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.global.exception.InvalidException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
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

    @Column(length = 1000)
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


    public static Policy from(PolicyRequest request) {
        validate(request.getTitle(), request.getServiceSummary(), request.getUserType(), request.getCategory());
        return Policy.builder()
                .title(request.getTitle())
                .userType(request.getUserType())
                .detailUrl(request.getDetailUrl())
                .serviceSummary(request.getServiceSummary())
                .category(request.getCategory())
                .selectionCriteria(request.getSelectionCriteria())
                .supportContent(request.getSupportContent())
                .targetAudience(request.getTargetAudience())
                .supportType(request.getSupportType())
                .applicationPeriod(request.getApplicationPeriod())
                .applicationMethod(request.getApplicationMethod())
                .contact(request.getContact())
                .receptionAgency(request.getReceptionAgency())
                .serviceId(request.getServiceId())
                .departmentName(request.getDepartmentName())
                .organizationName(request.getOrganizationName())
                .organizationType(request.getOrganizationType())
                .organizationCode(request.getOrganizationCode())
                .policyCreatedAt(null)
                .policyUpdatedAt(null)
                .viewCount(0)
                .build();
    }

    public static Policy from(GovPolicyDto dto) {
        validate(dto.getServiceName(), dto.getServiceSummary(), dto.getUserType(), dto.getServiceCategory());
        return Policy.builder()
                .title(dto.getServiceName())                      // 서비스명 → title
                .userType(dto.getUserType())                      // 사용자구분
                .serviceSummary(dto.getServiceSummary())          // 서비스목적요약
                .category(dto.getServiceCategory())               // 서비스분야
                .detailUrl(dto.getDetailUrl())                    // 상세조회URL
                .selectionCriteria(dto.getSelectionCriteria())    // 선정기준
                .supportContent(dto.getSupportContent())          // 지원내용
                .targetAudience(dto.getSupportTarget())           // 지원대상
                .supportType(dto.getSupportType())                // 지원유형
                .applicationPeriod(dto.getApplicationPeriod())    // 신청기한
                .applicationMethod(dto.getApplicationMethod())    // 신청방법
                .contact(dto.getContact())                        // 전화문의
                .receptionAgency(dto.getReceptionAgency())        // 접수기관
                .serviceId(dto.getServiceId())                    // 서비스ID
                .departmentName(dto.getTeamName())                // 부서명
                .organizationName(dto.getDepartmentName())        // 소관기관명
                .organizationType(dto.getDepartmentType())        // 소관기관유형
                .organizationCode(dto.getDepartmentCode())        // 소관기관코드
                .policyCreatedAt(dto.getCreatedAt())              // 등록일시
                .policyUpdatedAt(dto.getUpdatedAt())              // 수정일시
                .viewCount(dto.getViewCount())                    // 조회수
                .build();
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
