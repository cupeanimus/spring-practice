package com.kyle.springpractice.practice.jpa;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "line")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Line(String name) {
        this.name = name;
    }

    public Line() {
    }
}
