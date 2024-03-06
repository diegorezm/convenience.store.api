package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.domain.Products.ProductDTO;
import com.conveniencestore.conveniencestore.domain.Products.ProductStatusDTO;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import com.conveniencestore.conveniencestore.services.ProductService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private static final List<String> VALID_SEARCH_PARAMETERS = List.of("id", "sold", "asc", "desc");
    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false)
            String orderby,
            @RequestParam(required = false)
            String order)
    {
        String sortField = Optional.ofNullable(orderby).orElse("id");
        String sortOrder = Optional.ofNullable(order).orElse("desc");
        if(VALID_SEARCH_PARAMETERS.contains(sortField) && VALID_SEARCH_PARAMETERS.contains(sortOrder)) return ResponseEntity.ok().body(this.service.getAll(sortField, sortOrder));
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(this.service.getById(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> registerNewProduct(@RequestBody @Valid ProductDTO request) {
        try {
            Product product = this.service.insert(request);
            return ResponseEntity.status(201).body(product);
        } catch (ProductEntityNotFoundException e) {
            return ResponseEntity.status(404).body("Entity not found.");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateItemStatus(@PathVariable Integer id, @RequestBody @Valid ProductStatusDTO request) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            Product product = this.service.updateItemStatus(id, request);
            return ResponseEntity.ok().body(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            Product product = this.service.delete(id);
            return ResponseEntity.ok().body(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
