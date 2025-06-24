package com.benefitmoa.domain.user.service;

import com.benefitmoa.domain.user.auth.dto.UserResponse;
import com.benefitmoa.domain.user.dto.ProfileRequest;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import com.benefitmoa.global.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("사용자 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("프로필 업데이트 - 성공 : 이메일로 조회 후 프로필 수정 및 저장")
    void testUpdateProfile_success() {
        // given
        Long userId = 1L;
        String email = "test@example.com";
        ProfileRequest profileRequest = new ProfileRequest(email, "새 이름", "새 닉네임", "01012345678");
        User user = mock(User.class);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // when
        User updatedUser = userService.updateProfile(userId, profileRequest);

        // then
        verify(user).updateProfile("새 이름", "새 닉네임", "01012345678");
        verify(userRepository).save(user);
        assertNotNull(updatedUser);
    }

    @Test
    @DisplayName("프로필 업데이트 - 실패 : 이메일로 유저를 찾지 못한 경우 예외 발생")
    void testUpdateProfile_notFound() {
        // given
        Long userId = 1L;
        String email = "notfound@example.com";
        ProfileRequest profileRequest = new ProfileRequest(email, "이름", "닉네임", "010-1234-5678");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> {
            userService.updateProfile(userId, profileRequest);
        });
    }

    @DisplayName("내 정보 조회 - 성공: 사용자 ID로 UserResponse 반환")
    @Test
    void testGetUserInfo_success() {
        // given
        Long userId = 1L;
        User mockUser = User.create("test@example.com", "encodedPw", "테스트", "nickname", "010-1234-1234");
        ReflectionTestUtils.setField(mockUser, "id", userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // when
        UserResponse result = userService.getUserInfo(userId);

        // then
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("테스트", result.getName());
        assertEquals("nickname", result.getNickname());

        verify(userRepository).findById(userId);
    }
}
