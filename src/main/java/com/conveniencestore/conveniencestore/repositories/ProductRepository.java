package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.Products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
