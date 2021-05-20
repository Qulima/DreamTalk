package com.dreamtalk.security.jwt;

import com.dreamtalk.domain.Status;
import com.dreamtalk.domain.refresh.RefreshToken;
import com.dreamtalk.domain.user.User;
import com.dreamtalk.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenProvider {

    @Value("${jwt.refresh.validity}")
    private Long validity;

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

    public void deleteToken(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }

    public Optional<Cookie> resolveRefresh(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> "Refresh".equals(cookie.getName()))
                .findFirst();
    }

    private void saveToken(RefreshToken token) {
        refreshTokenRepository.save(token);
    }
}
