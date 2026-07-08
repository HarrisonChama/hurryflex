package com.hurryflex.hurryflex.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
            "hurryflex_super_secret_key_which_should_be_long_enough_123456";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // =========================
    // GENERATE TOKEN
    // =========================
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =========================
    // EXTRACT USERNAME
    // =========================
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // =========================
    // EXTRACT ROLE
    // =========================
    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // =========================
    // VALIDATION
    // =========================
    public boolean isTokenValid(String token, String email) {
        try {
            return extractUsername(token).equals(email)
                    && !isExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}