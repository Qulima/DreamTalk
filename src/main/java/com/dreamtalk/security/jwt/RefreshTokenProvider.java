package com.dreamtalk.security.jwt;

import com.dreamtalk.domain.Status;
import com.dreamtalk.domain.refresh.RefreshToken;
import com.dreamtalk.domain.user.User;
import com.dreamtalk.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenProvider {

    @Value("${jwt.refresh.validity}")
    private int validity;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenProvider(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createToken(User user) {
        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + validity);
        RefreshToken refreshToken = new RefreshToken(expiresIn, UUID.randomUUID(), user);
        refreshToken.setCreated(now);
        refreshToken.setUpdated(now);
        refreshToken.setStatus(Status.ACTIVE);
        saveToken(refreshToken);
        return refreshToken;
    }

    public RefreshToken retrieveToken(String token) {
        return refreshTokenRepository.findByRefreshToken(UUID.fromString(token));
    }

    public void AddTokenToCookie(RefreshToken token, HttpServletResponse response) {
        Cookie cookie = new Cookie("Refresh", token.getRefreshToken().toString());
        cookie.setMaxAge(validity);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public void deleteRefresh(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> requestRefresh = resolveRefresh(cookies);
            if (requestRefresh.isPresent()) {
                String refreshToken = requestRefresh.get().getValue();
                RefreshToken token = retrieveToken(refreshToken);
                deleteToken(token);
            }
        }
    }

    public Optional<Cookie> resolveRefresh(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> "Refresh".equals(cookie.getName()))
                .findFirst();
    }

    private void saveToken(RefreshToken token) {
        refreshTokenRepository.save(token);
    }

    private void deleteToken(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }
}
