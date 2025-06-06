package models;

import entities.Transaction;
import entities.strategy.TransactionStrategy;
import models.repository.impl.TransactionRepository;

public class TransactionProcessor {
    private final TransactionRepository transactionRepository;

    public TransactionProcessor(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void procesar(Transaction transaccion, TransactionStrategy strategy) {
        strategy.procesar(transaccion);
        transactionRepository.create(transaccion);
    }
}
