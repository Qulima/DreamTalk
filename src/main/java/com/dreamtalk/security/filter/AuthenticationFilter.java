package com.dreamtalk.security.filter;

import com.dreamtalk.dto.AuthenticationDTO;
import com.dreamtalk.security.userdetails.SecurityUser;
import com.dreamtalk.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationDTO creds = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationDTO.class);

            Authentication authData = new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    creds.getRole().getAuthorities()
            );

            return authenticationManager.authenticate(authData);

        } catch (IOException e) {
            log.error("Attempt authentication: IOException");
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            log.error("Attempt authentication: Could not authenticate");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        String jwtToken = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        response.addHeader(jwtTokenProvider.getHeader(), jwtToken);
    }
}
