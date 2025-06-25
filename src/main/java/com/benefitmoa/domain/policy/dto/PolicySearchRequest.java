package com.benefitmoa.domain.policy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class PolicySearchRequest {

    private int page = 0;
    private int size = 20;
    private String sort;             // e.g. "createdAt,desc"
    private String category;         // 서비스 분야
    private String userType;         // 사용자 유형
    private String keyword;          // 키워드 검색

    public Pageable toPageable() {
        if (StringUtils.hasText(sort)) {
            String[] sortParts = sort.split(",");
            if (sortParts.length == 2) {
                try {
                    return PageRequest.of(
                            page,
                            size,
                            Sort.by(Sort.Direction.fromString(sortParts[1].trim()), sortParts[0].trim())
                    );
                } catch (IllegalArgumentException e) {
                    // 무효한 정렬 방식 대응
                }
            }
        }
        return PageRequest.of(page, size);
    }

    public PolicySearchCondition toCondition() {
        return new PolicySearchCondition(keyword, category, userType);
    }
}
