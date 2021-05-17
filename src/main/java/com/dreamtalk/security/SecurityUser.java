package com.dreamtalk.security;

import com.dreamtalk.domain.Status;
import com.dreamtalk.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final Status status;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, Status status) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive(this.status);
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive(this.status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive(this.status);
    }

    @Override
    public boolean isEnabled() {
        return isActive(this.status);
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                isActive(user.getStatus()),
                isActive(user.getStatus()),
                isActive(user.getStatus()),
                isActive(user.getStatus()),
                user.getUserRole().getAuthorities()
                );
    }

    private static boolean isActive(Status status) {
        return "ACTIVE".equals(status.name());
    }
}
