package com.benefitmoa.domain.user.service;

import com.benefitmoa.domain.user.auth.dto.UserResponse;
import com.benefitmoa.domain.user.dto.ProfileRequest;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import com.benefitmoa.global.exception.ErrorCode;
import com.benefitmoa.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User updateProfile(Long userId, ProfileRequest profileRequest) {
        User user = getById(userId);
        user.updateProfile(profileRequest.getName(), profileRequest.getNickname(), profileRequest.getPhone());
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND.getMessage() + " (userId: " + id + ")"));
    }

    @Transactional(readOnly = true)
    public UserResponse getUserInfo(Long id) {
        return UserResponse.from(getById(id));
    }
}
