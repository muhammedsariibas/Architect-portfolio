package com.kddizayn.api.module.auth;

import com.kddizayn.api.module.auth.dto.LoginRequest;
import com.kddizayn.api.module.auth.dto.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/v1/auth/login
     * Returns a Bearer token pair for valid admin credentials.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * POST /api/v1/auth/refresh-token
     * Accepts the refresh token in the Authorization header and issues a new pair.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refresh(
            @RequestHeader("Authorization") String bearerToken) {

        String token = bearerToken.startsWith("Bearer ")
                ? bearerToken.substring(7)
                : bearerToken;

        return ResponseEntity.ok(authService.refreshToken(token));
    }
}
