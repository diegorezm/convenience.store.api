package com.conveniencestore.conveniencestore.domain.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull(message = "Please provide a name.")
        String username,
        @NotNull(message = "Please provide a password.")
        String password,
        @NotNull(message = "Please provide a email.")
        @Email
        String email,
        @NotNull(message = "Please provide a valid role.")
        String role
) {
}
