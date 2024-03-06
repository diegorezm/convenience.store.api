package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.services.ProductEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("products/entities")
public class ProductEntityController {
    private final ProductEntityService service;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(required = false) String orderby) {
        String sortField = Optional.ofNullable(orderby).orElse("id");
        List<ProductEntity> products = service.getAll(sortField);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntity productEntity = this.service.getById(id);
        return ResponseEntity.ok().body(productEntity);
    }

    @PostMapping
    public ResponseEntity<?> registerNewProduct(@RequestBody @Valid ProductEntityDTO request) {
        ProductEntity productEntity = this.service.insert(request);
        return ResponseEntity.status(201).body(productEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductEntityDTO productEntityRecord) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntity productEntity = this.service.update(id, productEntityRecord);
        return ResponseEntity.ok().body(productEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntity productEntity = this.service.delete(id);
        return ResponseEntity.ok().body(productEntity);
    }
}
