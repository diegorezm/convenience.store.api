package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.domain.Products.ProductDTO;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import com.conveniencestore.conveniencestore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private static final List<String> VALID_SEARCH_PARAMETERS = List.of("id", "entityId", "sold", "asc", "desc", "true", "false");

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false, defaultValue = "id")
            String orderby,
            @RequestParam(required = false, defaultValue = "asc")
            String order,
            @RequestParam(required = false)
            String sold
    ) {
        if (VALID_SEARCH_PARAMETERS.contains(orderby)
                && VALID_SEARCH_PARAMETERS.contains(order)
                && sold != null
                && VALID_SEARCH_PARAMETERS.contains(sold)
        ) {
            return ResponseEntity.ok().body(this.service.getAll(orderby, order, sold));
        } else if (
                VALID_SEARCH_PARAMETERS.contains(orderby) && VALID_SEARCH_PARAMETERS.contains(order)) {
            return ResponseEntity.ok().body(this.service.getAll(orderby, order));
        }
        ErrorDTO error = new ErrorDTO("Request param is not valid.", 400);
        return ResponseEntity.status(400).body(error);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(this.service.getById(id));
        } catch (ProductNotFoundException e) {
            ErrorDTO error = new ErrorDTO("This product was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
    }

    @GetMapping("/entity/{id}")
    public ResponseEntity<?> getProductByEntityId(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(this.service.getAllByEntityId(id));
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO("Something went wrong!.", 500);
            return ResponseEntity.status(500).body(error);
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

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        try {
            Product product = this.service.delete(id);
            return ResponseEntity.ok().body(product);
        } catch (ProductNotFoundException e) {
            ErrorDTO error = new ErrorDTO("This product was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
    }
}
