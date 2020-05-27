package com.kyle.springpractice;

import com.kyle.springpractice.service.RemoteCallService;
import com.kyle.springpractice.service.RetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;

import static javafx.beans.binding.Bindings.when;
import static javax.print.attribute.URISyntax.verify;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
public class SpringRetryTest {

    @MockBean
    private MessageClient messageClient;

    @Autowired
    private RetryService retryService;

    @Test
    void retry() {
        // given
        final String message = "";

        // when
        when(messageClient.send(message)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> retryService.send(message));

        // then
        verify(messageClient, times(3)).send(message);
    }



}
