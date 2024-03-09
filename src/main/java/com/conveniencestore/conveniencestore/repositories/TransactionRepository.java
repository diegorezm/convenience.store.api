package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.Transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByProductID(int id);
}
