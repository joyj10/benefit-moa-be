package com.benefitmoa.domain.policy.entity;

import com.benefitmoa.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Policy policy;

    private String region;

    private String sourceUrl;

    @Enumerated(EnumType.STRING)
    private TargetType target;

    private LocalDateTime deadline;

    @Lob
    private String supportContent;

    @Lob
    private String applicationMethod;

    public static PolicyDetail create(Policy policy, String region, String sourceUrl,
                                      TargetType target, LocalDateTime deadline,
                                      String supportContent, String applicationMethod) {
        PolicyDetail detail = new PolicyDetail();
        detail.policy = policy;
        detail.region = region;
        detail.target = target;
        detail.sourceUrl = sourceUrl;
        detail.deadline = deadline;
        detail.supportContent = supportContent;
        detail.applicationMethod = applicationMethod;
        return detail;
    }

    public void update(String region, String sourceUrl, TargetType target, LocalDateTime deadline,
                       String supportContent, String applicationMethod) {
        this.region = region;
        this.sourceUrl = sourceUrl;
        this.target = target;
        this.deadline = deadline;
        this.supportContent = supportContent;
        this.applicationMethod = applicationMethod;
    }

    // 연관 관계 편의 메서드
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

}
