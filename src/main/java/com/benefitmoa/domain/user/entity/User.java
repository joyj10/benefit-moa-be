package com.benefitmoa.domain.user.entity;

import com.benefitmoa.domain.common.BaseTimeEntity;
import com.benefitmoa.global.validator.UserValidator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User create(String email, String password, String name, String nickname, String phone) {
        UserValidator.validate(email, password, name, nickname, phone);
        return new User(email, password, name, nickname, phone, Role.USER);
    }

    private User(String email, String password, String name, String nickname, String phone, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.role = role;
    }

    public void updateProfile(String name, String nickname, String phone) {
        UserValidator.validate(name, nickname, phone);

        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
    }
}
