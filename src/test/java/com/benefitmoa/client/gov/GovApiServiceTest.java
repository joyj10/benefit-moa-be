package com.benefitmoa.client.gov;

import com.benefitmoa.client.gov.dto.GovPolicyDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
@ActiveProfiles("local")
class GovApiServiceIntegrationTest {

    @Autowired
    private GovApiService govApiService;

    @Autowired
    private GovApiProperties govApiProperties;

    @Test
    void fetchServices_success() {
        // given
        int page = 1;
        int perPage = 5;

        // when
        List<GovPolicyDto> services = govApiService.fetchServices(page, perPage);

        // then
        assertThat(services).isNotNull();
        assertThat(services.size()).isEqualTo(perPage);
        log.info("✅ 응답 개수: {}", services.size());
        services.forEach(s -> log.info("{}", s.getServiceName()));
    }
}
