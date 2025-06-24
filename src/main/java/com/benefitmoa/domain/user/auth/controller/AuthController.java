package com.benefitmoa.domain.user.auth.controller;

import com.benefitmoa.domain.user.auth.dto.LoginRequest;
import com.benefitmoa.domain.user.auth.dto.LoginResponse;
import com.benefitmoa.domain.user.auth.dto.SignupRequest;
import com.benefitmoa.domain.user.auth.dto.UserResponse;
import com.benefitmoa.domain.user.auth.service.AuthService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import com.benefitmoa.global.response.ApiResponse;
import com.benefitmoa.global.util.SecurityUtil;
import com.benefitmoa.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
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

    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser() {
        Long userId = SecurityUtil.getCurrentUserId();
        return ApiResponse.success(userService.getUserInfo(userId));
    }

}
