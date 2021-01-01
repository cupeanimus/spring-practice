package com.kyle.springpractice.common.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AdminUser extends User {
    private String channel;

    public AdminUser(String username, String password, boolean isEnabled, boolean isAccountNonExpired,
                     boolean isCredentialsNonExpired, boolean isAccountNonLocked,
                     Collection<? extends GrantedAuthority> authorities, String channel) {
        super(username, password, isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, authorities);
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}

