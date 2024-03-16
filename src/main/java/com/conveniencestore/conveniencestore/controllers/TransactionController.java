package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import com.conveniencestore.conveniencestore.domain.Transactions.Transaction;
import com.conveniencestore.conveniencestore.domain.Transactions.TransactionDTO;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.ProductIsSoldException;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.TransactionNotFoundException;
import com.conveniencestore.conveniencestore.services.ProductService;
import com.conveniencestore.conveniencestore.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @GetMapping
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(required = false)
            String order
    ){
        String sortOrder = Optional.ofNullable(order).orElse("asc");
        if(!sortOrder.equals("asc") && !sortOrder.equals("desc")) {
            ErrorDTO error = new ErrorDTO("Request param is not valid.", 404);
            return ResponseEntity.status(400).body(error);
        }
        return ResponseEntity.ok().body(this.service.getAll("id", sortOrder));
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Integer id){
        try {
            Transaction transaction = this.service.getById(id);
            return ResponseEntity.ok().body(transaction);
        }catch (TransactionNotFoundException e){
            ErrorDTO error = new ErrorDTO("This transaction was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getTransactionByProductId(@PathVariable Integer id){
        Transaction transaction = this.service.getByProductId(id);
        if (transaction == null) {
            ErrorDTO error = new ErrorDTO("This transaction was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(transaction);
    }
    @PostMapping
    public  ResponseEntity<?> registerNewTransaction(@RequestBody @Valid TransactionDTO data) {
        try {
            Transaction transaction = this.service.insert(data);
            return ResponseEntity.status(201).body(transaction);
        }catch (ProductNotFoundException e ){
            ErrorDTO error = new ErrorDTO("This product was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }catch (ProductIsSoldException e){
            ErrorDTO error = new ErrorDTO("This product has already been sold.", 404);
            return ResponseEntity.status(401).body(error);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer id){
        Transaction transaction = this.service.delete(id);
        return ResponseEntity.ok().body(transaction);
    }
}
