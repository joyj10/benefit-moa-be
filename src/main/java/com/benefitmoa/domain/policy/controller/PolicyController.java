package com.benefitmoa.domain.policy.controller;

import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.dto.PolicySearchRequest;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.global.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequiredArgsConstructor
@RequestMapping("/policies")
@Tag(name = "정책 조회", description = "사용자용 정책 상세 및 검색 API")
public class PolicyController {
    private final PolicyService policyService;

    @GetMapping("/{id}")
    @Operation(
            summary = "정책 상세 조회",
            description = "정책 ID를 통해 단건 상세 정보를 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "정책 ID", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "정책 상세 반환", content = @Content(schema = @Schema(implementation = PolicyResponse.class))),
                    @ApiResponse(responseCode = "404", description = "정책을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<PolicyResponse> getPolicy(@PathVariable Long id) {
        PolicyResponse result = policyService.getPolicy(id);
        return ApiResult.success(result);
    }

    @GetMapping
    @Operation(
            summary = "정책 목록 검색",
            description = "검색 조건(키워드, 대상, 지역 등)과 페이지네이션 정보를 기반으로 정책 목록을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "검색된 정책 페이지 반환",
                            content = @Content(schema = @Schema(implementation = PolicyResponse.class)))
            }
    )
    public ApiResult<Page<PolicyResponse>> getPolicies(@Parameter(description = "정책 검색 조건") @ModelAttribute PolicySearchRequest request) {
        Pageable pageable = request.toPageable();
        PolicySearchCondition condition = request.toCondition();

        Page<PolicyResponse> result = policyService.searchPolicies(condition, pageable);
        return ApiResult.success(result);
    }
}
