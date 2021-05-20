package com.dreamtalk.dto;

import com.dreamtalk.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationRequestDTO {
    private final String email;
    private final String password;
}
