package com.Riyaz.seatlock.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;


public record RegisterRequest(
        @NotBlank(message = "Name is required")
        @Size(max=100,message="Name must be at most 100 character")
        String Name,

        @NotBlank(message = "Name is required")
        @Size(max=150,message="Name must be at most 150 character")
        @Email(message="Email format is invalid")
        String Email,

        @NotBlank(message="Password is Required")
        @Size(min=6,max=64 ,message="Password must be with in 8 to 60 characters ")
        String Password
)
{}
