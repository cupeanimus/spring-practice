package com.kyle.springpractice.practice.multithread.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Entity(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long balance;

    public Account() {}

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public void deposit(long balance){
        this.balance += balance;
    }
}
