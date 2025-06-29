package com.benefitmoa.domain.policy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "정책 생성 및 수정 요청 DTO")
public class PolicyRequest {
    @Schema(description = "정책명", example = "청년 전세 자금 지원", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "정책명은 필수입니다.")
    private String title;

    @Schema(description = "사용자 구분", example = "청년")
    private String userType;

    @Schema(description = "정책 상세 URL", example = "https://www.gov.kr/policy/detail/1234")
    private String detailUrl;

    @Schema(description = "서비스 요약", example = "청년을 위한 전세자금 지원 사업입니다.")
    private String serviceSummary;

    @Schema(description = "서비스 분야", example = "주거지원")
    private String category;

    @Schema(description = "선정 기준", example = "소득 기준, 연령 조건")
    private String selectionCriteria;

    @Schema(description = "지원 내용", example = "최대 1억원까지 대출 지원")
    private String supportContent;

    @Schema(description = "지원 대상", example = "만 19세 ~ 39세 청년")
    private String targetAudience;

    @Schema(description = "지원 유형", example = "현금 지원, 대출")
    private String supportType;

    @Schema(description = "신청 기한", example = "2025-12-31")
    private String applicationPeriod;

    @Schema(description = "신청 방법", example = "온라인 신청")
    private String applicationMethod;

    @Schema(description = "전화 문의", example = "02-123-4567")
    private String contact;

    @Schema(description = "접수 기관", example = "서울시청")
    private String receptionAgency;

    @Schema(description = "서비스 ID", example = "SVC123456")
    private String serviceId;

    @Schema(description = "부서명", example = "청년정책과")
    private String departmentName;

    @Schema(description = "기관명", example = "서울특별시")
    private String organizationName;

    @Schema(description = "기관 유형", example = "지자체")
    private String organizationType;

    @Schema(description = "기관 코드", example = "11000")
    private String organizationCode;
}
