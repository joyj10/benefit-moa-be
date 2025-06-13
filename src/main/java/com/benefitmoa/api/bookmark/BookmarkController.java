package com.benefitmoa.api.bookmark;

import com.benefitmoa.api.bookmark.dto.BookmarkRequest;
import com.benefitmoa.domain.bookmark.service.BookmarkService;
import com.benefitmoa.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
