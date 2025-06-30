package com.benefitmoa.client.gov;

import com.benefitmoa.client.gov.dto.GovPolicyDto;
import com.benefitmoa.client.gov.dto.GovPolicyApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GovApiService {

    private final WebClient.Builder webClientBuilder;
    private final GovApiProperties govApiProperties;

    public List<GovPolicyDto> fetchServices(int page, int perPage) {
        String baseUrl = govApiProperties.getBaseUrl();
        String apiKey = UriUtils.decode(govApiProperties.getKey(), StandardCharsets.UTF_8);

        WebClient webClient = webClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .build();

        GovPolicyApiResponse response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/serviceList")
                        .queryParam("page", page)
                        .queryParam("perPage", perPage)
                        .queryParam("serviceKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GovPolicyApiResponse.class)
                .block();

        log.info("✅ Gov API 응답 = {}", response);

        return response != null ? response.getData() : List.of();
    }
}
