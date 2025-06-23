package com.benefitmoa.domain.bookmark.controller;

import com.benefitmoa.domain.bookmark.dto.BookmarkRequest;
import com.benefitmoa.domain.policy.dto.PolicyResponse;
import com.benefitmoa.domain.bookmark.service.BookmarkService;
import com.benefitmoa.global.response.ApiResponse;
import com.benefitmoa.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ApiResponse<Boolean> createBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkService.create(bookmarkRequest);
        return ApiResponse.success(true);
    }

    @GetMapping
    public ApiResponse<List<PolicyResponse>> getBookmarks() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<PolicyResponse> result = bookmarkService.getBookmarks(userId);
        return ApiResponse.success(result);
    }

}
