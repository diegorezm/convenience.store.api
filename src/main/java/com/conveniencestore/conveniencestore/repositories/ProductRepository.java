package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.Products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Products p WHERE p.sold=FALSE")
    List<Product> getAllSoldProducts();
    @Query("SELECT p FROM Products p WHERE p.sold=TRUE")
    List<Product> getAllNotSoldProducts();
}
