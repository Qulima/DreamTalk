package com.dreamtalk.security.userdetails;

import com.dreamtalk.domain.Status;
import com.dreamtalk.domain.user.Role;
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
    private final Role role;
    private final boolean isEnabled;
    private final boolean isAccountNonExpired;
    private final boolean isCredentialsNonExpired;
    private final boolean isAccountNonLocked;

    public SecurityUser(String username,
                        String password,
                        List<SimpleGrantedAuthority> authorities,
                        Status status,
                        Role role) {
        this.username = username;
        this.password = password;
        this.isEnabled = isActive(status);
        this.isAccountNonExpired = isActive(status);
        this.isAccountNonLocked = isActive(status);
        this.isCredentialsNonExpired = isActive(status);
        this.authorities = authorities;
        this.status = status;
        this.role = role;
    }

    public Role getRole() {
        return this.role;
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
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    private boolean isActive(Status status) {
        return "ACTIVE".equals(status.name());
    }
}
