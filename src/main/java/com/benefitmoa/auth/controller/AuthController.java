package com.benefitmoa.auth.controller;

import com.benefitmoa.auth.dto.LoginRequest;
import com.benefitmoa.auth.dto.LoginResponse;
import com.benefitmoa.auth.dto.SignupRequest;
import com.benefitmoa.auth.service.AuthService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.global.response.ApiResponse;
import com.benefitmoa.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider  jwtTokenProvider;

    @PostMapping("/signup")
    public ApiResponse<Boolean> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ApiResponse.success(true);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        String accessToken = jwtTokenProvider.createToken(user.getId(), user.getName(), user.getEmail());
        return ApiResponse.success(new LoginResponse(accessToken));
    }
}
