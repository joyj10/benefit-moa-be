package com.benefitmoa.domain.user.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "회원가입 요청 DTO")
public class SignupRequest {

    @Schema(description = "이메일 주소", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Schema(description = "비밀번호", example = "P@ssw0rd123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Schema(description = "이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Schema(description = "닉네임", example = "길동이")
    private String nickname;

    @Schema(description = "전화번호", example = "010-1234-5678", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "전화번호는 필수입니다.")
    private String phone;

    @Schema(description = "거주 지역", example = "서울특별시")
    private String region;
}
