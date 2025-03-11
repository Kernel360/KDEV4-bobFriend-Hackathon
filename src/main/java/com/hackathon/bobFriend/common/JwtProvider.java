package com.hackathon.bobFriend.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private static final String secretKey = "my-secret-key-my-secret-key-my-secret-key";
    private static final long validityInMilliseconds = 100000000;

    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    // JWT 생성
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT에서 email 추출
    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token.replace("Bearer ", "")).getBody().getSubject();
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace("Bearer ", ""));
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
