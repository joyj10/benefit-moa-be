package com.benefitmoa.domain.policy.entity;
import lombok.Getter;

@Getter
public enum TargetType {
    YOUTH("청년"),
    ELDERLY("노년층"),
    WOMAN("여성"),
    DISABLED("장애인"),
    ALL("전체 대상");

    private final String description;

    TargetType(String description) {
        this.description = description;
    }
}

