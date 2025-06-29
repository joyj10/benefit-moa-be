package com.benefitmoa.domain.bookmark.controller;

import com.benefitmoa.domain.bookmark.dto.BookmarkRequest;
import com.benefitmoa.domain.bookmark.service.BookmarkService;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.global.response.ApiResult;
import com.benefitmoa.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
@Tag(name = "북마크", description = "북마크 관련 API")

public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    @Operation(
            summary = "북마크 생성",
            description = "정책을 북마크에 추가합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "북마크할 정책 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BookmarkRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "북마크 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "요청 데이터 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<Boolean> createBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkService.create(bookmarkRequest);
        return ApiResult.success(true);
    }


    @GetMapping
    @Operation(
            summary = "북마크 목록 조회",
            description = "로그인한 사용자의 북마크된 정책 목록을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "북마크 목록 반환", content = @Content(schema = @Schema(implementation = PolicyResponse.class))),
                    @ApiResponse(responseCode = "404", description = "사용자 정보를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ApiResult<List<PolicyResponse>> getBookmarks() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<PolicyResponse> result = bookmarkService.getBookmarks(userId);
        return ApiResult.success(result);
    }

}
