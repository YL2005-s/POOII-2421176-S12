package entities.strategy;

import entities.Transaction;

public interface TransactionStrategy {
    void procesar(Transaction transaction);
}
