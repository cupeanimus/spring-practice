package com.kyle.springpractice.jwt.admin.domain;

import com.kyle.springpractice.common.domain.BaseTimeEntity;
import com.kyle.springpractice.common.util.StringAttributeConverter;
import com.kyle.springpractice.jwt.admin.dto.Authorization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ad_admin")
@NoArgsConstructor
public class Admin extends BaseTimeEntity implements Serializable {
    private static final long serialVersionUID = -1222465864492458660L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = StringAttributeConverter.class)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authorization authorization;

    private String reservationMenuAuthorization;

    private String channel;

    private LocalDate usageDateFrom;

    private LocalDate usageDateTo;

    private String company;

    @Convert(converter = StringAttributeConverter.class)
    private String responsibility;

    @Convert(converter = StringAttributeConverter.class)
    private String contactNumber;

    @Convert(converter = StringAttributeConverter.class)
    private String registerer;

    private int failureCount = 0;

    private boolean isLock = false;

    private LocalDateTime lastLoginTime;

    private LocalDateTime lastPasswordUpdateTime;

}
