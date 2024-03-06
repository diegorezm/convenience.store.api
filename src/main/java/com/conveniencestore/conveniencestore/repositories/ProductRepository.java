package com.conveniencestore.conveniencestore.domain.repositories;

import com.conveniencestore.conveniencestore.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
