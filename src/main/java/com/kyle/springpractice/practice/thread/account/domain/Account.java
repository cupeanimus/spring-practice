package com.kyle.springpractice.practice.thread.account.domain;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = -7524628172959175522L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private String name;

    private long balance;

    @Version
    private int version;

    public Account() {}

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public void deposit(long balance){
        this.balance += balance;
    }
}
