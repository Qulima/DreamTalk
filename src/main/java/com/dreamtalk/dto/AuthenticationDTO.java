package com.dreamtalk.dto;

import com.dreamtalk.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationDTO {
    private final String email;
    private final String password;
    private final Role role;
}
