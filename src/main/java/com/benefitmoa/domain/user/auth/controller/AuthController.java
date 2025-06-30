package com.benefitmoa.domain.user.auth.controller;

import com.benefitmoa.domain.user.auth.dto.LoginRequest;
import com.benefitmoa.domain.user.auth.dto.LoginResponse;
import com.benefitmoa.domain.user.auth.dto.SignupRequest;
import com.benefitmoa.domain.user.auth.dto.UserResponse;
import com.benefitmoa.domain.user.auth.service.AuthService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import com.benefitmoa.global.response.ApiResult;
import com.benefitmoa.global.util.SecurityUtil;
import com.benefitmoa.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "인증", description = "회원가입 / 로그인 / 내 정보 조회 API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtTokenProvider  jwtTokenProvider;

    @PostMapping("/signup")
    @Operation(
            summary = "회원가입",
            description = "사용자 정보를 입력받아 회원가입을 수행합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "회원가입 요청 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SignupRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "회원가입 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<Boolean> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ApiResult.success(true);
    }

    @PostMapping("/login")
    @Operation(
            summary = "로그인",
            description = "이메일과 비밀번호로 로그인하고 JWT 액세스 토큰을 반환합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "로그인 요청 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
                    @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        String accessToken = jwtTokenProvider.createToken(user.getId(), user.getName(), user.getEmail());
        return ApiResult.success(new LoginResponse(accessToken));
    }

    @GetMapping("/me")
    @Operation(
            summary = "내 정보 조회",
            description = "현재 로그인한 사용자의 정보를 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "사용자 정보 반환", content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<UserResponse> getCurrentUser() {
        Long userId = SecurityUtil.getCurrentUserId();
        return ApiResult.success(userService.getUserInfo(userId));
    }

}
