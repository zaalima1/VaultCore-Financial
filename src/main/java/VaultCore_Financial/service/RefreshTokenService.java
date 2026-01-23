package VaultCore_Financial.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import VaultCore_Financial.entity.RefreshToken;
import VaultCore_Financial.entity.User;
import VaultCore_Financial.repo.RefreshTokenRepository;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwt.refresh-exp-days}")
    private int refreshExpDays;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken create(User user) {
        String token = generateRandomToken();

        // ✅ Without Lombok builder
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(token);
        refreshToken.setExpiresAt(Instant.now().plus(refreshExpDays, ChronoUnit.DAYS));
        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }

    public boolean isValid(RefreshToken t) {
        return !t.isRevoked() && t.getExpiresAt().isAfter(Instant.now());
    }

    private String generateRandomToken() {
        byte[] bytes = new byte[48];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
