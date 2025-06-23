package com.benefitmoa.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String nickname;

    @NotBlank
    private String phone;

    private String region;
}
