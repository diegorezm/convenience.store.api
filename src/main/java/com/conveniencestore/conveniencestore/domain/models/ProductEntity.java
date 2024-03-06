package com.conveniencestore.conveniencestore.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "ProductEntity")
@Getter
@Setter
@AllArgsConstructor
@EntityListeners(ProductEntityListener.class)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
class ProductEntityListener {
    @PreUpdate
    public void beforeUpdate(ProductEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
