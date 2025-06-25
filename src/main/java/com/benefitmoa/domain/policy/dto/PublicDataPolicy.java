package com.benefitmoa.domain.policy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PublicDataPolicy {
    private String title; // 정책명
    private String userType; // 사용자 구분
    private String detailUrl; // 상세 URL
    private String serviceSummary; // 서비스 요약
    private String category; // 서비스 분야
    private String selectionCriteria; // 선정 기준
    private String supportContent; // 지원 내용
    private String targetAudience; // 지원 대상
    private String supportType; // 지원 유형
    private String applicationPeriod; // 신청 기한
    private String applicationMethod; // 신청 방법
    private String contact; // 전화 문의
    private String receptionAgency; // 접수 기관
    private String serviceId; // 서비스 ID
    private String departmentName; // 부서명
    private String organizationName; // 기관명
    private String organizationType; // 기관 유형
    private String organizationCode; // 기관 코드
    private String policyCreatedAt; // 등록일시
    private String policyUpdatedAt; // 수정일시
    private int viewCount; // 조회 수
}

