package com.benefitmoa.auth.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "jwt.secret=test-secret-key"
})
class JwtTokenProviderTest {
    @Autowired
    private  JwtTokenProvider jwtTokenProvider;

    @DisplayName("토큰 생선 및 유효성 검사")
    @Test
    void testCreateTokenAndValid() {
        long userId = 1L;
        // given & when
        String token = jwtTokenProvider.createToken(userId);

        // then
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals(jwtTokenProvider.getUserIdFromToken(token), userId);

    }
}
