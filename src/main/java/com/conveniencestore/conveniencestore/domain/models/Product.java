package com.conveniencestore.conveniencestore.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Products")
@Getter
@Setter
@AllArgsConstructor
@EntityListeners(ProductListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int entity_id;

    private boolean sold;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

class  ProductListener{
    @PreUpdate
    public void beforeUpdate(Product entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
