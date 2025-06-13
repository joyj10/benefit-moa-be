package com.benefitmoa.domain.bookmark.service;

import com.benefitmoa.api.bookmark.dto.BookmarkRequest;
import com.benefitmoa.domain.bookmark.entity.Bookmark;
import com.benefitmoa.domain.bookmark.repository.BookmarkRepository;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.policy.service.PolicyService;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;
    private final PolicyService policyService;

    @Transactional
    public Long create(BookmarkRequest bookmarkRequest) {
        User user = userService.getById(bookmarkRequest.getUserId());
        Policy policy = policyService.getById(bookmarkRequest.getPolicyId());
        Bookmark bookmark = Bookmark.create(user, policy);
        return bookmarkRepository.save(bookmark).getId();
    }
}
