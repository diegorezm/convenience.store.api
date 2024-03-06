package com.conveniencestore.conveniencestore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")

public class ProductController {
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok().body("products");
    }
}
