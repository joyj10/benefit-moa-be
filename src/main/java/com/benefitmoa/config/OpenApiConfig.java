package com.benefitmoa.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new Components().addSecuritySchemes("JWT", createJwtSecurityScheme()))
                .info(new Info()
                        .title("BenefitMoa API")
                        .version("v1")
                        .description("정부 지원 혜택 모아보기 플랫폼 API 문서"));
    }

    private SecurityScheme createJwtSecurityScheme() {
        return new SecurityScheme()
                .name("Authorization")
                .description("JWT 인증 헤더: Bearer {token}")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
