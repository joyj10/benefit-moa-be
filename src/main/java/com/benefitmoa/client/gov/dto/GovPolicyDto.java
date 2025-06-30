package com.benefitmoa.client.gov.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GovPolicyDto {

    @JsonProperty("서비스ID")
    private String serviceId;

    @JsonProperty("서비스명")
    private String serviceName;

    @JsonProperty("소관기관명")
    private String departmentName;

    @JsonProperty("상세조회URL")
    private String detailUrl;

    @JsonProperty("신청기한")
    private String applicationPeriod;

    @JsonProperty("신청방법")
    private String applicationMethod;

    @JsonProperty("지원내용")
    private String supportContent;

    @JsonProperty("지원대상")
    private String supportTarget;

    @JsonProperty("지원유형")
    private String supportType;

    @JsonProperty("조회수")
    private int viewCount;

    @JsonProperty("등록일시")
    private String createdAt;

    @JsonProperty("부서명")
    private String teamName;

    @JsonProperty("사용자구분")
    private String userType;

    @JsonProperty("서비스목적요약")
    private String serviceSummary;

    @JsonProperty("서비스분야")
    private String serviceCategory;

    @JsonProperty("선정기준")
    private String selectionCriteria;

    @JsonProperty("소관기관유형")
    private String departmentType;

    @JsonProperty("소관기관코드")
    private String departmentCode;

    @JsonProperty("수정일시")
    private String updatedAt;

    @JsonProperty("전화문의")
    private String contact;

    @JsonProperty("접수기관")
    private String receptionAgency;
}
