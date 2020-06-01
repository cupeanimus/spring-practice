package com.kyle.springpractice.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "member")
@Entity
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private LocalDate dob;

    private String firstname;

    private String lastname;

    public Member(String email, String lastname){
        this.email = email;
        this.lastname = lastname;
    }

}