package com.benefitmoa.domain.policy.service;

import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.repository.PolicyRepository;
import com.benefitmoa.global.exception.InvalidException;
import com.benefitmoa.global.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("정책 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class PolicyServiceTest {

    @InjectMocks
    private PolicyService policyService;

    @Mock
    private PolicyRepository policyRepository;

    @Test
    @DisplayName("정책 등록 - 성공 : 저장된 Policy 반환")
    void testCreatePolicy_success() {
        // given
        PolicyRequest policyRequest = PolicyRequest.builder()
                .title("청년 주거 지원")
                .serviceSummary("서울시 청년 대상 주거비(월세) 지원 정책")
                .userType("청년")
                .category("주거")
                .build();

        Policy expected = Policy.from(policyRequest);
        when(policyRepository.save(any(Policy.class))).thenReturn(expected);

        // when
        PolicyResponse result = policyService.create(policyRequest);

        // then
        assertNotNull(result);
        assertEquals("청년 주거 지원", result.getTitle());
        assertEquals("서울시 청년 대상 주거비(월세) 지원 정책", result.getServiceSummary());
    }

    @Test
    @DisplayName("정책 등록 - 실패 : title이 빈값이면 예외 발생")
    void testCreatePolicy_invalid() {
        // given
        PolicyRequest policyRequest = PolicyRequest.builder()
                .title(" ")
                .serviceSummary("요약")
                .userType("청년")
                .category("주거")
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

        String updateTile = "수정된 제목";
        PolicyRequest request = PolicyRequest.builder()
                .title(updateTile)
                .serviceSummary("수정된 요약")
                .userType("청년")
                .category("주거")
                .build();

        Policy policy = Policy.from(PolicyRequest.builder()
                .title("기존 제목")
                .serviceSummary("기존 요약")
                .userType("청년")
                .category("주거")
                .build());

        ReflectionTestUtils.setField(policy, "id", policyId);

        when(policyRepository.findById(policyId)).thenReturn(Optional.of(policy));

        // when
        PolicyResponse response = policyService.update(policyId, request);

        // then
        assertEquals("수정된 제목", response.getTitle());
        assertEquals("수정된 요약", response.getServiceSummary());
    }

    @Test
    @DisplayName("정책 수정 - 실패 : 정책 ID가 존재하지 않을 경우 예외 발생")
    void testUpdatePolicy_notFound() {
        // given
        Long policyId = 999L;
        PolicyRequest request = PolicyRequest.builder()
                .title("테스트")
                .serviceSummary("요약")
                .userType("청년")
                .category("문화")
                .build();

        when(policyRepository.findById(policyId)).thenReturn(Optional.empty());

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
        PolicyRequest request = PolicyRequest.builder()
                .title("테스트 정책")
                .serviceSummary("테스트 요약")
                .userType("청년")
                .category("주거")
                .build();
        Policy policy = Policy.from(request);
        ReflectionTestUtils.setField(policy, "id", policyId);

        when(policyRepository.findById(policyId)).thenReturn(Optional.of(policy));

        // when
        PolicyResponse response = policyService.getPolicy(policyId);

        // then
        assertNotNull(response);
        assertEquals(policyId, response.getPolicyId());
        assertEquals("테스트 정책", response.getTitle());
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
        PolicySearchCondition condition = new PolicySearchCondition("주거", "청년", "청년");
        Pageable pageable = PageRequest.of(0, 10);

        PolicyRequest request = PolicyRequest.builder()
                .title("청년 주거 지원")
                .serviceSummary("서울 청년 대상 주거 지원 정책")
                .userType("청년")
                .category("주거")
                .build();
        Policy policy = Policy.from(request);
        ReflectionTestUtils.setField(policy, "id", 1L);

        Page<Policy> mockPage = new PageImpl<>(List.of(policy), pageable, 1);
        when(policyRepository.searchByCondition(condition, pageable)).thenReturn(mockPage);

        // when
        Page<PolicyResponse> result = policyService.searchPolicies(condition, pageable);

        // then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        PolicyResponse policyResponse = result.getContent().get(0);
        assertEquals("청년 주거 지원", policyResponse.getTitle());
        assertEquals("서울 청년 대상 주거 지원 정책", policyResponse.getServiceSummary());
    }

    @Test
    @DisplayName("정책 목록 조회 - 실패 : 조건에 맞는 정책이 없는 경우 빈 페이지 반환")
    void testSearchPolicies_noResults() {
        // given
        PolicySearchCondition condition = new PolicySearchCondition("문화", "전체", "제주");
        Pageable pageable = PageRequest.of(0, 10);

        when(policyRepository.searchByCondition(condition, pageable)).thenReturn(Page.empty(pageable));

        // when
        Page<PolicyResponse> result = policyService.searchPolicies(condition, pageable);

        // then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
    }

}
