package com.dreamtalk.rest;

import com.dreamtalk.domain.refresh.RefreshToken;
import com.dreamtalk.domain.user.User;
import com.dreamtalk.dto.AuthenticationRequestDTO;
import com.dreamtalk.dto.AuthenticationResponseDTO;
import com.dreamtalk.repositories.UserRepository;
import com.dreamtalk.security.jwt.JwtTokenProvider;
import com.dreamtalk.security.jwt.RefreshTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenProvider refreshTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, RefreshTokenProvider refreshTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.refreshTokenProvider = refreshTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO requestDTO,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));

            User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(()
                    -> new UsernameNotFoundException(String.format("User with email %s not found", requestDTO.getEmail())));

            refreshTokenProvider.deleteRefresh(request);
            RefreshToken refreshToken = refreshTokenProvider.createToken(user);
            refreshTokenProvider.AddTokenToCookie(refreshToken, response);

            String jwtToken = jwtTokenProvider.createToken(user.getUsername(), user.getUserRole());
            return ResponseEntity.ok(new AuthenticationResponseDTO(user.getId(), jwtToken));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new AuthenticationResponseDTO(-1L, null), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Refresh")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestBody AuthenticationRequestDTO requestDTO,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        refreshTokenProvider.deleteRefresh(request);
        RefreshToken refreshToken = refreshTokenProvider.createToken(user);
        refreshTokenProvider.AddTokenToCookie(refreshToken, response);

        return ResponseEntity.ok(jwtTokenProvider.createToken(user.getEmail(), user.getUserRole()));
    }


}
