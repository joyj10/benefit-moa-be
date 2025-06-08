package com.benefitmoa.domain.policy.service;

import com.benefitmoa.api.policy.dto.CreatePolicyRequest;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.entity.TargetType;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import com.benefitmoa.global.exception.InvalidException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("정책 서비스 테스트")
class PolicyServiceTest {

    private PolicyService policyService;
    private PolicyRepository policyRepository;

    @BeforeEach
    void setUp() {
        policyRepository = mock(PolicyRepository.class);
        policyService = new PolicyService(policyRepository);
    }

    @Test
    @DisplayName("정책 등록 시, 저장된 Policy 반환")
    void testCreatePolicy() {
        // given
        String title = "청년 주거 지원";
        String summary = "서울시 청년 대상 주거비(월세) 지원 정책";

        CreatePolicyRequest policyRequest = CreatePolicyRequest.builder()
                .title(title)
                .summary(summary)
                .region("서울")
                .deadline("2025-07-01T23:59:59")
                .sourceUrl("https://www.test.com/test/1")
                .target(TargetType.YOUTH)
                .supportContent("서울시 청년 대상 주거비(월세) 지원 정책 - 상반기")
                .applicationMethod("서울시 홈페이지 신청")
                .build();

        Policy expected = Policy.create(title, summary);
        when(policyRepository.save(any(Policy.class))).thenReturn(expected);

        // when
        Policy result = policyService.create(policyRequest);

        // then
        assertNotNull(result, "저장된 Policy가 null이면 안됨");
        assertEquals(title, result.getTitle());
        assertEquals(summary, result.getSummary());
    }

    @Test
    @DisplayName("정책 등록 시 title이 빈값이면 예외 발생")
    void testCreatePolicy_withNullTitle_throwsException() {
        // given
        String title = " ";
        String summary = "서울시 청년 대상 주거비(월세) 지원 정책";

        CreatePolicyRequest policyRequest = CreatePolicyRequest.builder()
                .title(title)
                .summary(summary)
                .region("서울")
                .deadline("2025-07-01T23:59:59")
                .sourceUrl("https://www.test.com/test/1")
                .target(TargetType.YOUTH)
                .supportContent("서울시 청년 대상 주거비(월세) 지원 정책 - 상반기")
                .applicationMethod("서울시 홈페이지 신청")
                .build();

        // when & then
        InvalidException exception = assertThrows(InvalidException.class, () -> {
            policyService.create(policyRequest);
        });

        assertEquals("정책 제목은 null이거나 공백일 수 없습니다.", exception.getMessage());
    }
}