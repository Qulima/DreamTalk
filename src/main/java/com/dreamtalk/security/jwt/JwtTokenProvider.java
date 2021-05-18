package com.dreamtalk.security.jwt;

import com.dreamtalk.domain.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
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
        claims.put("role", role.name());
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
}
