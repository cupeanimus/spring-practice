package com.kyle.springpractice.practice.thread.account.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "Document")
public class Document {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private Long seq;

    public Document() {
    }

    public Document(String name) {
        this.name = name;
    }

    public Document(String name, long seq) {
        this.name = name;
        this.seq = nextSeq(seq);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getSeq() {
        return seq;
    }

    public Long nextSeq(Long seq) {
        if (seq == null) {
            return  1l;
        }
        return seq++;
    }
}
