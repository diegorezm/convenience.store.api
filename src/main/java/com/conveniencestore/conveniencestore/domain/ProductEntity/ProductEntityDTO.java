package com.conveniencestore.conveniencestore.domain.ProductEntity;

import jakarta.validation.constraints.NotNull;

public record ProductEntityDTO(
        @NotNull(message = "Name cannot be null")
        String name
) {
}
