package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.domain.Products.ProductDTO;
import com.conveniencestore.conveniencestore.domain.Products.ProductStatusDTO;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import com.conveniencestore.conveniencestore.repositories.ProductEntityRepository;
import com.conveniencestore.conveniencestore.repositories.ProductRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ServiceInterface<Product, ProductDTO> {
    private final ProductRepository productRepository;
    private final ProductEntityRepository productEntityRepository;

    public Product insert(ProductDTO data) {
        this.productEntityRepository.findById(data.entityId()).orElseThrow(ProductEntityNotFoundException::new);
        Product product = new Product(data);
        product = this.productRepository.save(product);
        return product;
    }

    public List<Product> getAll(String orderby, String order) {
        Sort.Direction direction;
        switch (order) {
            case "asc" -> {
                direction = Sort.Direction.ASC;
            }
            case "desc" -> {
                direction = Sort.Direction.DESC;
            }
            default -> {
                direction = Sort.DEFAULT_DIRECTION;
            }
        }
        return this.productRepository.findAll(Sort.by(direction, orderby));
    }

    public List<Product> getAllSold() {
        return this.productRepository.getAllSoldProducts();
    }

    public List<Product> getAllNotSold() {
        return this.productRepository.getAllNotSoldProducts();
    }

    public Product getById(int id) {
        return this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product delete(int id) {
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
        return product;
    }

    public Product update(int id, ProductDTO data) {
        return null;
    }

    public Product updateItemStatus(int id, ProductStatusDTO data) {
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setSold(data.sold());
        product.setUpdatedAt(LocalDateTime.now());
        product = this.productRepository.save(product);
        return product;
    }
}
