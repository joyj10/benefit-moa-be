package com.benefitmoa.domain.user.service;

import com.benefitmoa.api.user.dto.ProfileRequest;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User updateProfile(ProfileRequest profileRequest) {
        User user = userRepository.findByEmail(profileRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        user.updateProfile(profileRequest.getName(), profileRequest.getNickname(), profileRequest.getPhone());
        return userRepository.save(user);
    }
}
