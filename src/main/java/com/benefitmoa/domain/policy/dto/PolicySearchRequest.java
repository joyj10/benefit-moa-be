package com.benefitmoa.domain.policy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
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
@Schema(description = "정책 검색 요청 파라미터")
public class PolicySearchRequest {

    @Schema(description = "페이지 번호 (0부터 시작)", example = "0", defaultValue = "0")
    @Min(0)
    private int page = 0;

    @Schema(description = "페이지 크기", example = "20", defaultValue = "20")
    @Min(1)
    private int size = 20;

    @Schema(description = "정렬 기준 (예: createdAt,desc)", example = "createdAt,desc")
    private String sort;

    @Schema(description = "서비스 분야 카테고리", example = "주거지원")
    private String category;

    @Schema(description = "사용자 유형", example = "청년")
    private String userType;

    @Schema(description = "검색 키워드", example = "대출")
    private String keyword;

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
