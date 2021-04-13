package com.kyle.springpractice.practice.multithread.domain;

import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.swing.text.html.Option;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    public Account findByAccountId(long accountId);
}
