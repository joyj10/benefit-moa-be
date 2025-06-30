package com.benefitmoa.client.gov.dto;

import lombok.Data;

import java.util.List;

@Data
public class GovPolicyApiResponse {
    private int currentCount;
    private List<GovPolicyDto> data;
    private int page;
    private int perPage;
    private int totalCount;
}
