package com.benefitmoa.domain.user.auth.service;

import com.benefitmoa.domain.user.auth.dto.SignupRequest;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Auth 서비스 테스트")
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @DisplayName("회원가입 - 성공: 유효한 요청 시 회원가입 성공")
    @Test
    void testSignup_success() {
        // given
        String email = "test@test.com";
        String password = "password123";

        SignupRequest signupRequest = SignupRequest.builder()
                .email(email)
                .password(password)
                .name("Test User")
                .nickname("testuser")
                .phone("010-1234-1324")
                .build();

        when(userRepository.existsByEmail(email)).thenReturn(false);

        String encodedPassword = "encoded-password123";
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));


        // when
        authService.signup(signupRequest);

        // then
        verify(userRepository).existsByEmail(email);
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }
}
