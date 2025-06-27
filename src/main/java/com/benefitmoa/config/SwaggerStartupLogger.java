package com.benefitmoa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SwaggerStartupLogger implements ApplicationRunner {

    @Value("${server.port:8080}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void run(ApplicationArguments args) {
        String url = "http://localhost:" + port + contextPath + "/swagger-ui/index.html";
        log.info("\n\n✅ Swagger UI: " + url + "\n");
    }
}
