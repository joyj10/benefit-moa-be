package com.benefitmoa.domain.policy.repository;

import com.benefitmoa.domain.policy.dto.PolicySearchCondition;
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

@RequiredArgsConstructor
public class PolicyRepositoryImpl implements PolicyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Policy> searchByCondition(PolicySearchCondition condition, Pageable pageable) {
        List<Policy> content = queryFactory
                .selectFrom(policy)
                .where(buildPredicate(condition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(toOrderSpecifiers(pageable))
                .fetch();

        Long total = queryFactory
                .select(policy.count())
                .from(policy)
                .where(buildPredicate(condition))
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0L : total);
    }

    private BooleanBuilder buildPredicate(PolicySearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(policy.title.containsIgnoreCase(condition.getKeyword())
                    .or(policy.serviceSummary.containsIgnoreCase(condition.getKeyword()))
                    .or(policy.supportContent.containsIgnoreCase(condition.getKeyword()))
                    .or(policy.targetAudience.containsIgnoreCase(condition.getKeyword())));
        }

        if (StringUtils.hasText(condition.getCategory())) {
            builder.and(policy.category.eq(condition.getCategory()));
        }

        if (StringUtils.hasText(condition.getUserType())) {
            builder.and(policy.userType.eq(condition.getUserType()));
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
                        case "title" -> asc ? policy.title.asc() : policy.title.desc();
                        case "createdAt" -> asc ? policy.createdAt.asc() : policy.createdAt.desc();
                        case "viewCount" -> asc ? policy.viewCount.asc() : policy.viewCount.desc();
                        default -> null;
                    };
                })
                .filter(Objects::nonNull)
                .toArray(OrderSpecifier[]::new);
    }
}
