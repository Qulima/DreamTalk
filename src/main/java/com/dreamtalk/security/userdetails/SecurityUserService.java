package com.dreamtalk.security.userdetails;

import com.dreamtalk.domain.user.User;
import com.dreamtalk.repositories.UserRepository;
import com.dreamtalk.security.userdetails.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@Slf4j
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public SecurityUserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() -> {
            log.warn(String.format("User with email %s not found", email));
            throw new UsernameNotFoundException(String.format("User with email %s not found", email));
        });
        return new SecurityUser(
                user.getUsername(),
                user.getPassword(),
                user.getUserRole().getAuthorities(),
                user.getStatus(),
                user.getUserRole()
        );
    }
}
