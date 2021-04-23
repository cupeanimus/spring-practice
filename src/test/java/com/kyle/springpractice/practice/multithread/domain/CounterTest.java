package com.kyle.springpractice.practice.multithread.domain;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;


//https://www.baeldung.com/java-testing-multithreaded#3-a-better-attempt-at-testing-with-concurrency
public class CounterTest {

    @Autowired
    MyCounter myCounter;

//    @Test
//    public void testCounter() {
//        MyCounter counter = new MyCounter();
//        for (int i = 0; i < 500; i++) {
//            counter.increment();
//        }
//        assertEquals(500, counter.getCount());
//    }

//3.2. First Attempt at Testing With Concurrency
//    @Test
//    public void testCounterWithConcurrency() throws InterruptedException {
//        int numberOfThreads = 10;
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//        MyCounter counter = new MyCounter();
//        for (int i = 0; i < numberOfThreads; i++) {
//            service.execute(() -> {
//                counter.increment();
//                latch.countDown();
//            });
//        }
//        latch.await();
//        assertEquals(numberOfThreads, counter.getCount());
//    }


    //3.3. A Better Attempt at Testing With Concurrency
    @Test
    public void testSummationWithConcurrency() throws InterruptedException {
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        MyCounter counter = new MyCounter();
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    // Handle exception
                }
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(numberOfThreads, counter.getCount());
    }




}