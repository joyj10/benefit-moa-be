package com.benefitmoa.auth.service;

import com.benefitmoa.domain.user.auth.dto.SignupRequest;
import com.benefitmoa.domain.user.auth.service.AuthService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import com.benefitmoa.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@DisplayName("auth 서비스 테스트")
class AuthServiceTest {

    private AuthService authService;
    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        authService = mock(AuthService.class);
        userService = mock(UserService.class);
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);

    }

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
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));


        // when
        authService.signup(signupRequest);

        // then
        verify(userRepository).existsByEmail(email);
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }
}
