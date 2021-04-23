package com.kyle.springpractice.practice.multithread.domain;

import lombok.Getter;

@Getter
public class MyCounter {
    private int count;

//3.2. First Attempt at Testing With Concurrency
//    public void increment() {
//        int temp = count;
//        count = temp + 1;
//    }

//3.3. A Better Attempt at Testing With Concurrency
    public synchronized void increment() throws InterruptedException {
        int temp = count;
        wait(100);
        count = temp + 1;
    }
}
