package com.dreamtalk.security.filter;

import com.dreamtalk.security.jwt.JwtAuthenticationException;
import com.dreamtalk.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//TODO Make AuthFilter
public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public AuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        super(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken(request);

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            response.sendError(e.getHttpStatus().value());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) throws JwtAuthenticationException {
        String token = jwtTokenProvider.resolveToken(req);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUsername(token);

                if (username != null) {
                    List<SimpleGrantedAuthority> authorities = jwtTokenProvider.getRole(token).getAuthorities();
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            }
        } catch (JwtAuthenticationException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
        throw new JwtAuthenticationException("JWT token is expired or invalid");
    }
}
