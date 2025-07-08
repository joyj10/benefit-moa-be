package com.benefitmoa.batch.scheduler;

import com.benefitmoa.batch.job.GovPolicyBatchJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GovPolicyScheduler {

    private final GovPolicyBatchJob govPolicyBatchJob;

    // 매일 오전 3시 실행
    @Scheduled(cron = "0 0 3 * * ?")
    public void runPolicyBatch() {
        log.info("=== [Starting batch job] GovPolicy ===");
        try {
            govPolicyBatchJob.execute();
            log.info("=== [completed batch job] GovPolicy ===");
        } catch (Exception e) {
            log.error("=== [Error batch job] GovPolicy ===", e);
        }
    }
}
