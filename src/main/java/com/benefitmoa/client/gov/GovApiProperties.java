package com.benefitmoa.client.gov;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "external.gov.api")
public class GovApiProperties {
    private String baseUrl;
    private String key;
}
