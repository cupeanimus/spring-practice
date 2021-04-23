package com.kyle.springpractice.practice.multithread.service;

import com.kyle.springpractice.practice.multithread.config.HeavyWorkBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest {

    @Autowired
    HeavyWorkBean hBean;

    @Test
    public void asyncTest() throws Exception {
        CountDownLatch cl = new CountDownLatch(10);	// 10번 count down 설정
        hBean.setCountdownLatch(cl);
        for (int i = 0; i < 10; i++) {
            hBean.heavyWork(i);
        }
        boolean wellDone =  cl.await(20, TimeUnit.SECONDS);	// 10회 동작 또는 20s까지 대기
        System.out.println(String.format("메인스레드 종료 (timeout=%b)", !wellDone));
        assertEquals(true, wellDone);
    }
}