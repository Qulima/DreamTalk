package com.dreamtalk.security.jwt;

import com.dreamtalk.domain.user.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expired}")
    private Long validity;

    @PostConstruct
    private void init() {
        this.secret = Base64.getEncoder()
                .encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String email, Role role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + validity);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(header);
    }

    public String getHeader() {
        return header;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.FORBIDDEN);
        }
    }

    public Role getRole(String token) {
        try {
            return (Role) Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody().get("role");
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.FORBIDDEN);
        }
    }
}
