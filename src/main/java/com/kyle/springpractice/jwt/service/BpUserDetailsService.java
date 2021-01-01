package com.kyle.springpractice.jwt.service;

import com.kyle.springpractice.common.domain.AdminUser;
import com.kyle.springpractice.jwt.admin.domain.Admin;
import com.kyle.springpractice.jwt.admin.dto.Authorization;
import com.kyle.springpractice.jwt.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BpUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public AdminUser loadUserByUsername(String username) {
        Admin admin = adminRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("유효하지 않는 email 입니다"));


        return new AdminUser(
                admin.getEmail(), // username
                admin.getPassword(), // password
                true, // enabled
                expiredCheck(admin), // accountNonExpired
                expiredCheck(admin), // credentialsNonExpired
                !admin.isLock(), // accountNonLocked
                getAuthorities(admin), // authorities
                admin.getChannel() // channel
                );
    }



    private List<GrantedAuthority> getAuthorities(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (admin.getAuthorization().equals(Authorization.SUPER)){
            authorities.add(new SimpleGrantedAuthority("ROLE_SUPER"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }



    private boolean expiredCheck(Admin admin) {
        LocalDate today = LocalDate.now();

        return today.compareTo(admin.getUsageDateFrom())< 0 || today.compareTo(admin.getUsageDateTo()) > 0;
    }


}

