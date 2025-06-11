package com.benefitmoa.api.user.dto;

import com.benefitmoa.api.policy.dto.PolicyDetailResponse;
import com.benefitmoa.api.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

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
