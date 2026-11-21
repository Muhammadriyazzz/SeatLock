package com.Riyaz.seatlock.service;


import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtService {
    private final SecretKey key;
    private final long expirationMs;


    public JwtService(@Value("${app.jwt.secret}")String secret ,
                      @Value("${app.jwt.expiration-ms}")long expirationMs){
        this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs=expirationMs;
    }

    public  String generateToken(UserDetails userdetails){
        Date time=new Date();
        String role=userdetails.getAuthorities().iterator().next().getAuthority();


        //continue from this

        return Jwts.builder()
                .subject(userDetails.getUsername())        // the email
                .claim("role", role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            Claims claims = parseClaims(token);   // also fails if signature is wrong or token expired
            return claims.getSubject().equals(userDetails.getUsername());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public long getExpirationMs() {
        return expirationMs;
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}




