package com.benefitmoa.domain.policy.repository;

import com.benefitmoa.domain.policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>, PolicyRepositoryCustom {
}
