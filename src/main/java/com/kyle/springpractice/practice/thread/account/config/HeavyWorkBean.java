package com.kyle.springpractice.practice.thread.account.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class HeavyWorkBean {
    CountDownLatch cl;

    public void setCountdownLatch(CountDownLatch cl) {
        this.cl = cl;
    }

    @Async
    public void heavyWork(int i) throws InterruptedException {
        Thread.sleep(1000);
        log.trace("아주 무거운 작업을 {} 번째 진행중.", i);
        cl.countDown();
    }
}
