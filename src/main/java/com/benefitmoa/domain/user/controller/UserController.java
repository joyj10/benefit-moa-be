package com.benefitmoa.domain.user.controller;

import com.benefitmoa.domain.user.dto.ProfileRequest;
import com.benefitmoa.domain.user.dto.ProfileResponse;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import com.benefitmoa.global.response.ApiResult;
import com.benefitmoa.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "사용자", description = "사용자 정보 수정 API")
public class UserController {
    private final UserService userService;

    @PatchMapping("/profile")
    @Operation(
            summary = "프로필 수정",
            description = "로그인한 사용자의 프로필 정보를 수정합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( // Swagger용은 풀 네임으로 작성
                    description = "수정할 프로필 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProfileRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "프로필 수정 성공", content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<ProfileResponse> updateProfile(@RequestBody ProfileRequest profileRequest) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userService.updateProfile(userId, profileRequest);
        return ApiResult.success(ProfileResponse.from(user));
    }

}
