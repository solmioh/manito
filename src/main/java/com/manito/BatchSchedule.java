package com.manito;

import com.manito.service.GroupQueryService;
import com.manito.service.GroupWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchSchedule {

    private final GroupQueryService groupQueryService;
    private final GroupWriteService groupWriteService;

    @Scheduled(cron = "0 0 0 * * *")
    public void testSchedule() {
        var groups = groupQueryService.findByStatus(Status.PROCEEDING);
        groupWriteService.checkEndDate(groups);
    }
}
