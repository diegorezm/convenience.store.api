package com.conveniencestore.conveniencestore.domain.Products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(ProductListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int entityId;

    private boolean sold;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    public Product(ProductDTO request){
        this.entityId = request.entityId();
    }
}

class  ProductListener{
    @PreUpdate
    public void beforeUpdate(Product entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
