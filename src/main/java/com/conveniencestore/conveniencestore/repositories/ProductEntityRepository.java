package com.conveniencestore.conveniencestore.domain.repositories;

import com.conveniencestore.conveniencestore.domain.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}
