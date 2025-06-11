package com.benefitmoa.domain.policy.service;

import com.benefitmoa.api.policy.dto.PolicyDetailRequest;
import com.benefitmoa.api.policy.dto.PolicyRequest;
import com.benefitmoa.api.policy.dto.PolicyResponse;
import com.benefitmoa.api.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.entity.PolicyDetail;
import com.benefitmoa.domain.policy.entity.TargetType;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import com.benefitmoa.global.exception.InvalidException;
import com.benefitmoa.global.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @DisplayName("정책 등록 - 성공 : 저장된 Policy 반환")
    void testCreatePolicy_success() {
        // given
        String title = "청년 주거 지원";
        String summary = "서울시 청년 대상 주거비(월세) 지원 정책";

        PolicyDetailRequest detailRequest = new PolicyDetailRequest(
                null, // policyDetailId는 생성 시 없으니 null로 둬도 됨
                "서울",
                "https://www.test.com/test/1",
                TargetType.YOUTH,
                "2025-07-01T23:59:59",
                "서울시 청년 대상 주거비(월세) 지원 정책 - 상반기",
                "서울시 홈페이지 신청"
        );

        PolicyRequest policyRequest = PolicyRequest.builder()
                .title(title)
                .summary(summary)
                .policies(List.of(detailRequest))
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
    @DisplayName("정책 등록 - 실패 : title이 빈값이면 예외 발생")
    void testCreatePolicy_invalid() {
        // given
        String title = " "; // 공백 제목 (빈값 테스트)
        String summary = "서울시 청년 대상 주거비(월세) 지원 정책";

        PolicyDetailRequest detailRequest = new PolicyDetailRequest(
                null, // 신규 등록 시 policyDetailId는 없음
                "서울",
                "https://www.test.com/test/1",
                TargetType.YOUTH,
                "2025-07-01T23:59:59",
                "서울시 청년 대상 주거비(월세) 지원 정책 - 상반기",
                "서울시 홈페이지 신청"
        );

        PolicyRequest policyRequest = PolicyRequest.builder()
                .title(title)
                .summary(summary)
                .policies(List.of(detailRequest))
                .build();

        // when & then
        InvalidException exception = assertThrows(InvalidException.class, () -> policyService.create(policyRequest));

        assertEquals("정책 제목은 null이거나 공백일 수 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("정책 수정 - 성공 : 요청 데이터로 수정")
    void testUpdatePolicy_success() {
        // given
        Long policyId = 1L;
        Policy policy = mock(Policy.class);
        PolicyDetail existingDetail = mock(PolicyDetail.class);

        // 기존 PolicyDetail (id = 100)
        when(existingDetail.getId()).thenReturn(100L);
        when(policy.getDetails()).thenReturn(List.of(existingDetail));

        // 요청 객체
        PolicyDetailRequest detailRequest = new PolicyDetailRequest(
                100L,
                "서울",
                "https://example.com",
                TargetType.YOUTH,
                "2025-07-01T23:59:59",
                "지원내용",
                "신청방법"
        );
        PolicyRequest policyRequest = PolicyRequest.builder()
                .title("수정된 제목")
                .summary("수정된 요약")
                .policies(List.of(detailRequest))
                .build();

        when(policyRepository.findById(policyId)).thenReturn(Optional.of(policy));

        // when
        Policy result = policyService.update(policyId, policyRequest);

        // then
        verify(policy).update("수정된 제목", "수정된 요약");
        verify(existingDetail).update(
                eq("서울"),
                eq("https://example.com"),
                eq(TargetType.YOUTH),
                any(), // LocalDateTime
                eq("지원내용"),
                eq("신청방법")
        );
        verify(policy).clearDetails();
        verify(policy).addDetails(anyList());
        assertNotNull(result);
    }

    @Test
    @DisplayName("정책 수정 - 실패 : 정책 ID가 존재하지 않을 경우 예외 발생")
    void testUpdatePolicy_notFound() {
        // given
        Long policyId = 999L;
        when(policyRepository.findById(policyId)).thenReturn(Optional.empty());

        PolicyRequest request = PolicyRequest.builder()
                .title("테스트")
                .summary("요약")
                .policies(List.of())
                .build();

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> policyService.update(policyId, request));

        assertTrue(exception.getMessage().contains("PolicyId: 999"));
    }

    @Test
    @DisplayName("정책 삭제 - 성공 : 존재하는 정책 ID 삭제 시 정상적으로 삭제")
    void testDeletePolicy_success() {
        // given
        Long policyId = 1L;
        Policy mockPolicy = mock(Policy.class);
        when(policyRepository.findById(policyId)).thenReturn(Optional.of(mockPolicy));

        // when
        policyService.delete(policyId);

        // then
        verify(policyRepository).delete(mockPolicy);
    }

    @Test
    @DisplayName("정책 삭제 - 실패 : 정책 ID가 존재하지 않을 경우 예외 발생")
    void testDeletePolicy_notFound() {
        // given
        Long policyId = 999L;
        when(policyRepository.findById(policyId)).thenReturn(Optional.empty());

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> policyService.delete(policyId));
        assertTrue(exception.getMessage().contains("PolicyId: 999"));
    }

    @Test
    @DisplayName("정책 단건 조회 - 성공 : 존재하는 정책 ID 조회 시 상세 정보 반환")
    void testGetPolicy_success() {
        // given
        Long policyId = 1L;
        Policy mockPolicy = mock(Policy.class);

        // Policy 엔티티의 getter 동작 설정
        when(mockPolicy.getId()).thenReturn(policyId);
        when(mockPolicy.getTitle()).thenReturn("테스트 정책");
        when(mockPolicy.getSummary()).thenReturn("테스트 요약");
        when(mockPolicy.getViewCount()).thenReturn(100);
        when(mockPolicy.getDetails()).thenReturn(Collections.emptyList());

        when(policyRepository.findById(policyId)).thenReturn(Optional.of(mockPolicy));

        // when
        PolicyResponse response = policyService.getPolicy(policyId);

        // then
        assertNotNull(response);
        assertEquals(policyId, response.getPolicyId());
        assertEquals("테스트 정책", response.getTitle());
        assertEquals("테스트 요약", response.getSummary());
        assertEquals(100, response.getViewCount());
        assertNotNull(response.getDetailResponses());
        assertTrue(response.getDetailResponses().isEmpty());
    }

    @Test
    @DisplayName("정책 단건 조회 - 실패 : 존재하지 않는 정책 ID 조회 시 예외 발생")
    void testGetPolicy_notFound() {
        // given
        Long policyId = 999L;
        when(policyRepository.findById(policyId)).thenReturn(Optional.empty());

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> policyService.getPolicy(policyId));
        assertTrue(exception.getMessage().contains("PolicyId: 999"));
    }

    @Test
    @DisplayName("정책 목록 조회 - 성공 : 조건에 맞는 정책 목록을 페이징으로 반환")
    void testSearchPolicies_success() {
        // given
        PolicySearchCondition condition = new PolicySearchCondition("서울", TargetType.YOUTH, "주거");
        Pageable pageable = PageRequest.of(0, 10);

        Policy mockPolicy = mock(Policy.class);
        when(mockPolicy.getId()).thenReturn(1L);
        when(mockPolicy.getTitle()).thenReturn("청년 주거 지원");
        when(mockPolicy.getSummary()).thenReturn("서울 청년 대상 주거 지원 정책");
        when(mockPolicy.getViewCount()).thenReturn(500);
        when(mockPolicy.getDetails()).thenReturn(Collections.emptyList());

        Page<Policy> mockPage = new PageImpl<>(List.of(mockPolicy), pageable, 1);
        when(policyRepository.searchByCondition(condition, pageable)).thenReturn(mockPage);

        // when
        Page<PolicyResponse> result = policyService.searchPolicies(condition, pageable);

        // then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        PolicyResponse policyResponse = result.getContent().get(0);
        assertEquals(1L, policyResponse.getPolicyId());
        assertEquals("청년 주거 지원", policyResponse.getTitle());
        assertEquals("서울 청년 대상 주거 지원 정책", policyResponse.getSummary());
        assertEquals(500, policyResponse.getViewCount());
        assertTrue(policyResponse.getDetailResponses().isEmpty());
    }

    @Test
    @DisplayName("정책 목록 조회 - 실패 : 조건에 맞는 정책이 없는 경우 빈 페이지 반환")
    void testSearchPolicies_noResults() {
        // given
        PolicySearchCondition condition = new PolicySearchCondition("제주", TargetType.ALL, "문화");
        Pageable pageable = PageRequest.of(0, 10);

        when(policyRepository.searchByCondition(condition, pageable))
                .thenReturn(Page.empty(pageable));

        // when
        Page<PolicyResponse> result = policyService.searchPolicies(condition, pageable);

        // then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
    }



}