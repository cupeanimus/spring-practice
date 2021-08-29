package com.kyle.springpractice.practice.thread.account.service;

import com.kyle.springpractice.practice.thread.account.domain.Document;
import com.kyle.springpractice.practice.thread.account.domain.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Transactional
    public void selectAndUpdate(Integer i) {
        log.info("{}번째 호출",i);
        Long maxSeq = documentRepository.findMaxSeq();
        log.info("max Seq : "+maxSeq);
        documentRepository.save(new Document("test"+i,maxSeq));
    }

    @Transactional
    public void parallelUpdate(int i) {
        log.info("{}번째 호출",i);
        try {
            documentRepository.updateSequence(1l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
