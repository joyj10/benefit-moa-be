package com.benefitmoa.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JWT token 서비스 테스트")
@SpringBootTest
@TestPropertySource(properties = {
        "jwt.secret=test-secret-key-which-is-long-enough-to-pass-123456"
})
class JwtTokenProviderTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @DisplayName("토큰 생선 및 유효성 검사")
    @Test
    void testCreateTokenAndValid() {
        long userId = 1L;
        String name = "Test User";
        String email = "test@test.com";

        // given & when
        String token = jwtTokenProvider.createToken(userId, name, email);

        // then
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals(jwtTokenProvider.getUserIdFromToken(token), userId);
    }
}
