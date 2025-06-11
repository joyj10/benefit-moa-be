package com.benefitmoa.domain.policy.repository;

import com.benefitmoa.api.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PolicyRepositoryCustom {
    Page<Policy> searchByCondition(PolicySearchCondition condition, Pageable pageable);
}
