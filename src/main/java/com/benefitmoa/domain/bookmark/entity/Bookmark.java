package com.benefitmoa.domain.bookmark.entity;

import com.benefitmoa.domain.common.BaseTimeEntity;
import com.benefitmoa.domain.policy.entity.Policy;
import com.benefitmoa.domain.user.entity.User;
import com.benefitmoa.global.exception.InvalidException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Policy policy;

    public static Bookmark create(User user, Policy policy) {
        validate(user, policy);
        return new Bookmark(user, policy);
    }

    private Bookmark(User user, Policy policy) {
        this.user = user;
        this.policy = policy;
    }

    private static void validate(User user, Policy policy) {
        if (user == null) {
            throw new InvalidException("사용자 정보는 null 일 수 없습니다.");
        }
        if (policy == null) {
            throw new InvalidException("정책 정보는 null 일 수 없습니다.");
        }
    }
}
