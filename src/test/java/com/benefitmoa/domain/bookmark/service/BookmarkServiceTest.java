package com.benefitmoa.domain.bookmark.service;

import com.benefitmoa.api.bookmark.dto.BookmarkRequest;
import com.benefitmoa.domain.bookmark.entity.Bookmark;
import com.benefitmoa.domain.bookmark.repository.BookmarkRepository;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import com.benefitmoa.global.exception.InvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("북마크 서비스 테스트")
class BookmarkServiceTest {

    private BookmarkService bookmarkService;
    private BookmarkRepository bookmarkRepository;
    private UserService userService;
    private PolicyService policyService;

    @BeforeEach
    void setUp() {
        bookmarkRepository = mock(BookmarkRepository.class);
        userService = mock(UserService.class);
        policyService = mock(PolicyService.class);
        bookmarkService = new BookmarkService(bookmarkRepository, userService, policyService);
    }

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

}