package com.benefitmoa.domain.user.service;

import com.benefitmoa.domain.user.dto.ProfileRequest;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import com.benefitmoa.global.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    @DisplayName("프로필 업데이트 - 성공 : 이메일로 조회 후 프로필 수정 및 저장")
    void testUpdateProfile_success() {
        // given
        String email = "test@example.com";
        ProfileRequest profileRequest = new ProfileRequest(email, "새 이름", "새 닉네임", "01012345678");
        User user = mock(User.class);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // when
        User updatedUser = userService.updateProfile(profileRequest);

        // then
        verify(user).updateProfile("새 이름", "새 닉네임", "01012345678");
        verify(userRepository).save(user);
        assertNotNull(updatedUser);
    }

    @Test
    @DisplayName("프로필 업데이트 - 실패 : 이메일로 유저를 찾지 못한 경우 예외 발생")
    void testUpdateProfile_notFound() {
        // given
        String email = "notfound@example.com";
        ProfileRequest profileRequest = new ProfileRequest(email, "이름", "닉네임", "01012345678");

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.updateProfile(profileRequest);
        });

        assertTrue(exception.getMessage().contains("email: " + email));
    }
}
