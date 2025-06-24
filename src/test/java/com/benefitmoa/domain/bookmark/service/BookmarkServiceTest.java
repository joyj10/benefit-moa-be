package com.benefitmoa.domain.bookmark.service;

import com.benefitmoa.domain.bookmark.dto.BookmarkRequest;
import com.benefitmoa.domain.bookmark.entity.Bookmark;
import com.benefitmoa.domain.bookmark.repository.BookmarkRepository;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import com.benefitmoa.global.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@DisplayName("북마크 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;
    @Mock
    private UserService userService;
    @Mock
    private PolicyService policyService;

    @InjectMocks
    private BookmarkService bookmarkService;

    @Test
    @DisplayName("북마크 생성 - 성공 : 저장된 북마크 id 반환")
    void testCreateBookmark_success() {
        // given
        Long userId = 1L;
        Long policyId = 10L;

        BookmarkRequest bookmarkRequest = BookmarkRequest.builder()
                .userId(userId)
                .policyId(policyId)
                .build();

        User user = mock(User.class);
        Policy policy = mock(Policy.class);
        Bookmark bookmark = mock(Bookmark.class);

        when(userService.getById(userId)).thenReturn(user);
        when(policyService.getById(policyId)).thenReturn(policy);
        when(bookmarkRepository.save(any(Bookmark.class))).thenReturn(bookmark);
        when(bookmark.getId()).thenReturn(100L);

        // when
        Long createdBookmarkId = bookmarkService.create(bookmarkRequest);

        // then
        assertNotNull(createdBookmarkId);
        verify(userService).getById(userId);
        verify(policyService).getById(policyId);
        verify(bookmarkRepository).save(any(Bookmark.class));
    }

    @Test
    @DisplayName("북마크 생성 - 실패: 사용자 또는 정책이 null일 경우 예외 발생")
    void testCreateBookmark_invalid() {
        // given
        Long userId = 1L;
        Long policyId = 10L;

        BookmarkRequest bookmarkRequest = BookmarkRequest.builder()
                .userId(userId)
                .policyId(policyId)
                .build();

        when(userService.getById(userId)).thenReturn(null); // 사용자 없음

        // when & then
        InvalidException exception = assertThrows(InvalidException.class, () -> bookmarkService.create(bookmarkRequest));
        assertEquals("사용자 정보는 null 일 수 없습니다.", exception.getMessage());
    }

    @DisplayName("북마크 리스트 조회 - 성공: 사용자의 북마크 리스트 반환")
    @Test
    void testGetBookmarks() {
        // given
        Long userId = 1L;
        User user = User.create("test@example.com", "pw12341234", "홍길동", "nickname", "010-1234-5678");
        ReflectionTestUtils.setField(user, "id", userId);

        Policy policy = Policy.create("정책명", "설명");
        ReflectionTestUtils.setField(policy, "id", 10L);

        Bookmark bookmark = Bookmark.create(user, policy);
        ReflectionTestUtils.setField(bookmark, "id", 100L);

        when(userService.getById(userId)).thenReturn(user);
        when(bookmarkRepository.findAllWithPolicyByUserId(userId)).thenReturn(List.of(bookmark));

        // when
        List<PolicyResponse> result = bookmarkService.getBookmarks(userId);

        // then
        assertEquals(1, result.size());
        assertEquals("정책명", result.get(0).getTitle());
    }

}
