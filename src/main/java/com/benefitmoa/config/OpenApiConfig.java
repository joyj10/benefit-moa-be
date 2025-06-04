package com.benefitmoa.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BenefitMoa API")
                        .version("v1")
                        .description("정부 지원 혜택 모아보기 플랫폼 API 문서"));
    }
}
