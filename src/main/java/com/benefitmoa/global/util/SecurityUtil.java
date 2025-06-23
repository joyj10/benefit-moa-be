package com.benefitmoa.global.util;

import com.benefitmoa.auth.security.UserPrincipal;
import com.benefitmoa.global.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil() {}

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("인증된 사용자가 아닙니다.");
        }

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        return userDetails.getUserId();

    }
}
