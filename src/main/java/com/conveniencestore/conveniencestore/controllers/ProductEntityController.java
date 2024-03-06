package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.models.ProductEntity;
import com.conveniencestore.conveniencestore.domain.records.ProductEntityRecord;
import com.conveniencestore.conveniencestore.services.ProductEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products/entities")
public class ProductEntityController {
    private final ProductEntityService service;
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductEntity> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<?> registerNewProduct(@RequestBody @Valid ProductEntityRecord request){
      ProductEntity productEntity = this.service.insert(request);
      return ResponseEntity.status(201).body(productEntity);
    }
}
