package com.benefitmoa.domain.policy.repository;

import com.benefitmoa.api.policy.dto.PolicySearchCondition;
import com.benefitmoa.domain.policy.entity.Policy;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static com.benefitmoa.domain.policy.entity.QPolicy.policy;
import static com.benefitmoa.domain.policy.entity.QPolicyDetail.policyDetail;

@RequiredArgsConstructor
public class PolicyRepositoryImpl implements PolicyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Policy> searchByCondition(PolicySearchCondition condition, Pageable pageable) {
        List<Policy> content = queryFactory
                .selectFrom(policy)
                .distinct()
                .leftJoin(policy.details, policyDetail).fetchJoin()
                .where(buildPredicate(condition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(toOrderSpecifiers(pageable))
                .fetch();

        Long total = queryFactory
                .select(policy.count())
                .from(policy)
                .leftJoin(policy.details, policyDetail)
                .where(buildPredicate(condition))
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanBuilder buildPredicate(PolicySearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.getRegion())) {
            builder.and(policyDetail.region.eq(condition.getRegion()));
        }

        if (condition.getTarget() != null) {
            builder.and(policyDetail.target.eq(condition.getTarget()));
        }

        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(policy.title.containsIgnoreCase(condition.getKeyword())
                    .or(policy.summary.containsIgnoreCase(condition.getKeyword())));
        }

        return builder;
    }

    private OrderSpecifier<?>[] toOrderSpecifiers(Pageable pageable) {
        if (!pageable.getSort().isSorted()) {
            return new OrderSpecifier<?>[0];
        }

        return StreamSupport.stream(pageable.getSort().spliterator(), false)
                .map(order -> {
                    boolean asc = order.isAscending();
                    String property = order.getProperty();

                    return switch (property) {
                        case "deadline" -> asc ? policyDetail.deadline.asc() : policyDetail.deadline.desc();
                        case "title" -> asc ? policy.title.asc() : policy.title.desc();
                        default -> null;
                    };
                })
                .filter(Objects::nonNull)
                .toArray(OrderSpecifier[]::new);
    }
}
