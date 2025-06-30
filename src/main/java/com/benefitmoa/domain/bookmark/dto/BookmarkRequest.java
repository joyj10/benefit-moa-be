package com.benefitmoa.domain.bookmark.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "북마크 생성 요청 DTO")
public class BookmarkRequest {

    @Schema(description = "사용자 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Long userId;

    @Schema(description = "정책 ID", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Long policyId;
}
