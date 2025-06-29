package com.benefitmoa.domain.user.auth.service;

import com.benefitmoa.domain.user.auth.dto.SignupRequest;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import com.benefitmoa.global.exception.ErrorMessages;
import com.benefitmoa.global.exception.InvalidException;
import com.benefitmoa.global.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.benefitmoa.global.exception.ErrorMessages.EMAIL_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidException(EMAIL_ALREADY_EXISTS.getMessage());
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userRepository.save(User.create(request.getEmail(), encodedPassword, request.getName(), request.getNickname(), request.getPhone()));
    }

    @Transactional(readOnly = true)
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("로그인 실패 - 존재하지 않는 이메일 (email: {})", email);
                    return new UnauthorizedException(ErrorMessages.EMAIL_OR_PASSWORD_INVALID.getMessage());
                });

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("로그인 실패 - 비밀번호 불일치 (email: {})", email);
            throw new UnauthorizedException(ErrorMessages.EMAIL_OR_PASSWORD_INVALID.getMessage());
        }

        log.info("로그인 성공 (userId: {}, email: {})", user.getId(), user.getEmail());
        return user;
    }
}
