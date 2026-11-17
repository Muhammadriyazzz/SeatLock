package com.Riyaz.seatlock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
public record Loginrequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email format is invalid")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {}
