package com.kyle.springpractice.practice.multithread.service;

import com.kyle.springpractice.practice.multithread.domain.Account;
import com.kyle.springpractice.practice.multithread.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public long deposit(long accountId, long amount){
        Account account = accountRepository.findByAccountId(accountId);
        log.info("thread = " + Thread.currentThread().getName() + ", " + "currBalance = " + account.getBalance());
        account.deposit(amount);
        log.info("thread = " + Thread.currentThread().getName() + ", " + "currBalance = " + account.getBalance());
        return account.getBalance();
    }

}
