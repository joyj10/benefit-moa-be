package com.benefitmoa.domain.policy.controller;

import com.benefitmoa.domain.policy.dto.PolicyRequest;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.global.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/policies")
@Tag(name = "관리자 정책 관리", description = "관리자용 정책 생성/수정/삭제 API")
public class AdminPolicyController {
    private final PolicyService policyService;

    @PostMapping
    @Operation(
            summary = "정책 생성",
            description = "새로운 정책을 등록합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "생성할 정책 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PolicyRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "정책 생성 성공", content = @Content(schema = @Schema(implementation = PolicyResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<PolicyResponse> createPolicy(@RequestBody @Valid PolicyRequest policyRequest) {
        return ApiResult.success(policyService.create(policyRequest));
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "정책 수정",
            description = "기존 정책을 수정합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "수정할 정책 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PolicyRequest.class))
            ),
            parameters = {
                    @Parameter(name = "id", description = "정책 ID", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "정책 수정 성공", content = @Content(schema = @Schema(implementation = PolicyResponse.class))),
                    @ApiResponse(responseCode = "404", description = "정책을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<PolicyResponse> updatePolicy(@PathVariable Long id,
                                                  @RequestBody @Valid PolicyRequest policyRequest) {
        return ApiResult.success(policyService.update(id, policyRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "정책 삭제",
            description = "정책을 삭제합니다.",
            parameters = {
                    @Parameter(name = "id", description = "정책 ID", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "정책 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "정책을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<Void> deletePolicy(@PathVariable Long id) {
        policyService.delete(id);
        return ApiResult.success(null);
    }

}
