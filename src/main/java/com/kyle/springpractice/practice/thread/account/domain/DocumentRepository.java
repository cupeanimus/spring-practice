package com.kyle.springpractice.practice.thread.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "select max(seq) from document for update", nativeQuery = true)
    Long findMaxSeq();



    @Modifying
    @Query(value = "update document set seq = seq +1 where id = ?1", nativeQuery = true)
    void updateSequence(Long id) throws Exception;
}
