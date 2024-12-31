package com.example.demo.service;

import com.example.demo.model.entity.Transaction;
import com.example.demo.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Save a transaction with some basic validation (optional)
    public Transaction saveTransaction(Transaction transaction) {
        // Add any validation or checks for the transaction here, if required
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        // Save the transaction
        return transactionRepository.save(transaction);
    }

    // Get transactions by user ID
    public List<Transaction> getTransactionsByUserId(Long userId) {
        // Ensure userId is not null or invalid
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        return transactionRepository.findByUserId(userId);
    }

    // Add other business methods as required
}
