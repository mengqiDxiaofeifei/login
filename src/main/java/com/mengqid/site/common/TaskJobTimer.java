package com.mengqid.site.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TaskJobTimer {


    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduled1() {
        log.info("现在时间是" + new Date());
    }
}
