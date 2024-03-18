package com.conveniencestore.conveniencestore.domain.users;

public record UserResponseDTO(
        Integer id,
        String username,
        String email,
        UserRoles role
) {
}
