package com.benefitmoa.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프로필 수정 요청 DTO")
public class ProfileRequest {

    @Schema(description = "이메일 주소", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Schema(description = "이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Schema(description = "닉네임", example = "길동이", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @Schema(description = "전화번호", example = "010-1234-5678", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "전화번호는 필수입니다.")
    private String phone;
}
