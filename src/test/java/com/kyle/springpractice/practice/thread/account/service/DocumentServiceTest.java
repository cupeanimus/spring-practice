package com.kyle.springpractice.practice.thread.account.service;

import com.kyle.springpractice.practice.thread.account.domain.Document;
import com.kyle.springpractice.practice.thread.account.domain.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @BeforeEach
    public void setUp() {
        documentRepository.deleteAll();

    }

    @Test
    void 조회후_업데이트() {

        //given
        int trials = 50;

        //when
        AtomicReference<Integer> count = new AtomicReference<>(0);
        IntStream.range(1,trials).parallel().forEach(
                i -> {
                    count.getAndSet(count.get() + 1);
                    documentService.selectAndUpdate(i);
                }
        );
        System.out.println("count : "+count);


        //then
        Long maxSeq = documentRepository.findMaxSeq();
        assertThat(maxSeq).isEqualTo(new Long(trials));
    }

    @Test
    void 바로_업데이트(){

        //given
        documentRepository.save(new Document("바로업데이트",1l));
        Document document = documentRepository.findById(1l).orElseThrow(() -> new IllegalArgumentException("test실패"));
        assertThat(document.getId()).isEqualTo(1l);

        //when
        int trials = 50;
        AtomicReference<Integer> count = new AtomicReference<>(0);
        IntStream.range(1,trials).parallel().forEach(
                i -> {
                    count.getAndSet(count.get() + 1);
                    documentService.parallelUpdate(i);
                }
        );
        System.out.println("count : "+count);


        //then
        Document result = documentRepository.findById(1l).orElseThrow(() -> new IllegalArgumentException("test실패"));
        assertThat(result.getSeq()).isEqualTo(trials);

    }

    @Test
    void 단일_업데이트() {
        //given
        documentRepository.save(new Document("단일업데이트",1l));
        Document document = documentRepository.findById(1l).orElseThrow(() -> new IllegalArgumentException("test실패"));
        assertThat(document.getId()).isEqualTo(1l);

        //when
        documentService.parallelUpdate(1);

        //then
        Document result = documentRepository.findById(1l).orElseThrow(() -> new IllegalArgumentException("test실패"));
        assertThat(result.getSeq()).isEqualTo(2l);
    }

    @Test
    void longEqualTest() {
        long a = 1l;
        long b = 1l;
        assertThat(a).isEqualTo(b);
    }


}