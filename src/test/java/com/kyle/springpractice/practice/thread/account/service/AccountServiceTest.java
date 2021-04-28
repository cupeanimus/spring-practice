package com.kyle.springpractice.practice.thread.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountServiceTest {

    private static final ExecutorService service = Executors.newFixedThreadPool(100);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;




    private long accountId;


    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
        Account account = new Account("테스트계좌");
        account = accountRepository.save(account);
        accountId = account.getAccountId();

    }

    @Test
    public void SimultaneousDepositPassWithNoRaceCondition() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                accountService.deposit(accountId, 10);
                latch.countDown();
            });
        }
        latch.await();
        Account richAccount = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("잘못된 accountId 입니다."));
        assertThat(richAccount.getBalance()).isEqualTo(10 * 100);

    }


}