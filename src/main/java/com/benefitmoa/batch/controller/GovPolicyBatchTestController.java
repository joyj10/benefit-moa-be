package com.benefitmoa.batch.controller;

import com.benefitmoa.batch.job.GovPolicyBatchJob;
import com.benefitmoa.global.response.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/batch/gov-policy")
@RequiredArgsConstructor
public class GovPolicyBatchTestController {

    private final GovPolicyBatchJob govPolicyBatchJob;
    @PostMapping
    public ApiResult<String> runGovPolicyBatch() {
        log.info("# 정책 배치 수동 실행 시도");
        govPolicyBatchJob.execute();
        return ApiResult.success("# 정책 배치 수동 실행 완료");
    }
}
