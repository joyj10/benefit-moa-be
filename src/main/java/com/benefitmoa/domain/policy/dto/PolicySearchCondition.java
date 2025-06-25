package com.benefitmoa.domain.policy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicySearchCondition {
    private String keyword;      // 검색 키워드 (정책명, 요약 등)
    private String category;     // 서비스 분야 (예: 주거, 금융)
    private String userType;     // 사용자 구분 (예: 개인, 가구)
}
