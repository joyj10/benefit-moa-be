package com.benefitmoa.global.validator;

import com.benefitmoa.global.exception.ErrorMessages;
import com.benefitmoa.global.exception.InvalidException;
import org.springframework.util.StringUtils;

public class UserValidator {

    private UserValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(String email, String password, String name, String nickname, String phone) {
        validateEmail(email);
        validatePassword(password);
        validateName(name);
        validateNickname(nickname);
        validatePhone(phone);
    }

    public static void validate(String name, String nickname, String phone) {
        validateName(name);
        validateNickname(nickname);
        validatePhone(phone);
    }

    private static void validateEmail(String email) {
        if (!StringUtils.hasText(email) || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidException(ErrorMessages.INVALID_EMAIL.getMessage());
        }
    }

    private static void validatePassword(String password) {
        if (!StringUtils.hasText(password) || password.length() < 8) {
            throw new InvalidException(ErrorMessages.PASSWORD_TOO_SHORT.getMessage());
        }
    }

    private static void validateName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new InvalidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }
    }

    private static void validateNickname(String nickname) {
        if (!StringUtils.hasText(nickname)) {
            throw new InvalidException(ErrorMessages.NICKNAME_REQUIRED.getMessage());
        }
    }

    private static void validatePhone(String phone) {
        if (!StringUtils.hasText(phone) || !phone.matches("^\\d{10,11}$")) {
            throw new InvalidException(ErrorMessages.PHONE_REQUIRED.getMessage());
        }
    }
}