package com.benefitmoa.domain.policy.entity;

import com.benefitmoa.domain.common.BaseTimeEntity;
import com.benefitmoa.global.exception.InvalidException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Policy extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private int viewCount;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PolicyDetail> details = new ArrayList<>();

    // 정적 생성 메서드
    public static Policy create(String title, String summary) {
        validate(title, summary);
        return new Policy(title, summary);
    }

    // private 생성자 사용
    private Policy(String title, String summary) {
        this.title = title;
        this.summary = summary;
        this.viewCount = 0;
    }

    // 입력값 검증
    private static void validate(String title, String summary) {
        if (title == null || title.isBlank()) {
            throw new InvalidException("정책 제목은 null이거나 공백일 수 없습니다.");
        }
        if (summary == null || summary.isBlank()) {
            throw new InvalidException("정책 요약은 null이거나 공백일 수 없습니다.");
        }
    }

    public void addDetail(PolicyDetail detail) {
        details.add(detail);
        detail.setPolicy(this);  // 연관 관계 설정
    }
}
