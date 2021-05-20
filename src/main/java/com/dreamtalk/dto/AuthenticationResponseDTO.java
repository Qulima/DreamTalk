package com.dreamtalk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponseDTO {
    private Long userId;
    private String jwt;
}
