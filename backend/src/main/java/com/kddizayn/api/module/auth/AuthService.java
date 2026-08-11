package com.kddizayn.api.module.auth;

import com.kddizayn.api.module.auth.dto.LoginRequest;
import com.kddizayn.api.module.auth.dto.TokenResponse;
import com.kddizayn.api.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    public TokenResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String accessToken  = tokenProvider.generateToken(auth);
        String refreshToken = tokenProvider.generateRefreshToken(auth.getName());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(expirationMs / 1000)
                .build();
    }

    public TokenResponse refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new com.kddizayn.api.exception.UnauthorizedException("Invalid or expired refresh token");
        }
        String username     = tokenProvider.getUsernameFromToken(refreshToken);
        String newAccess    = tokenProvider.generateRefreshToken(username); // reuse same builder
        String newRefresh   = tokenProvider.generateRefreshToken(username);

        return TokenResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .tokenType("Bearer")
                .expiresIn(expirationMs / 1000)
                .build();
    }
}
