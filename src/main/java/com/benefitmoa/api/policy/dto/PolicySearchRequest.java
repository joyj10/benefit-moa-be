package com.benefitmoa.api.policy.dto;

import com.benefitmoa.domain.policy.entity.TargetType;
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
    private String sort;
    private String region;
    private TargetType target;
    private String keyword;

    public Pageable toPageable() {
        if (StringUtils.hasText(sort)) {
            String[] sortParts = sort.split(",");
            if (sortParts.length == 2) {
                return PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortParts[1]), sortParts[0]));
            }
        }
        return PageRequest.of(page, size);
    }

    public PolicySearchCondition toCondition() {
        return new PolicySearchCondition(region, target, keyword);
    }
}
