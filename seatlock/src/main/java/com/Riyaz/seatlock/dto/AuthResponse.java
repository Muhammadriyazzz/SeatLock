package com.Riyaz.seatlock.dto;

public record AuthResponse(
        String token,
        String tokentype,
        String email,
        String role,
        long expiresInMs
) {}

