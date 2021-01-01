package com.kyle.springpractice.poi.entity;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
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