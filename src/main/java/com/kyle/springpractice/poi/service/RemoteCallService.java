//package com.kyle.springpractice.poi.service;
//
//import javafx.beans.value.ObservableBooleanValue;
//import org.springframework.retry.annotation.Backoff;
//import org.springframework.retry.annotation.Retryable;
//
//public interface RemoteCallService {
//    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 0))
//    ObservableBooleanValue call(String url);
//}
//
//
