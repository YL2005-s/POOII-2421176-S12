package models;

import entities.Transaction;
import entities.strategy.TransactionStrategy;
import models.repository.impl.TransactionRepository;

public class TransactionProcessor {
    private final TransactionRepository transactionRepository;
    private TransactionStrategy transactionStrategy;

    public TransactionProcessor(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }

    public void procesar(Transaction transaction) {
        transactionStrategy.procesar(transaction);
        transactionRepository.create(transaction);
    }
}
