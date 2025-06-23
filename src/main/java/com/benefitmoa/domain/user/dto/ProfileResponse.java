package com.benefitmoa.domain.user.dto;

import com.benefitmoa.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private String email;
    private String name;
    private String nickname;
    private String phone;

    public static ProfileResponse from(User user) {
        return ProfileResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .build();
    }
}
