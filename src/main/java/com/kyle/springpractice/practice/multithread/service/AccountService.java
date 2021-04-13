package com.kyle.springpractice.practice.multithread.service;

import com.kyle.springpractice.practice.multithread.domain.Account;
import com.kyle.springpractice.practice.multithread.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public long deposit(long accountId, long amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 accountId 입니다"));
        account.deposit(amount);
        return account.getBalance();
    }

}
