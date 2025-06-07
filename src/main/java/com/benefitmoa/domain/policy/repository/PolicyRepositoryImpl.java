package com.benefitmoa.domain.policy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PolicyRepositoryImpl implements PolicyRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}
