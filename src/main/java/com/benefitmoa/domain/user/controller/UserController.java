package com.benefitmoa.domain.user.controller;

import com.benefitmoa.domain.user.dto.ProfileRequest;
import com.benefitmoa.domain.user.dto.ProfileResponse;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import com.benefitmoa.global.response.ApiResponse;
import com.benefitmoa.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/profile")
    public ApiResponse<ProfileResponse> updateProfile(@RequestBody ProfileRequest profileRequest) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userService.updateProfile(userId, profileRequest);
        return ApiResponse.success(ProfileResponse.from(user));
    }

}
