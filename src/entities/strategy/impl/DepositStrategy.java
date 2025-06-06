package entities.strategy.impl;

import entities.Account;
import entities.Transaction;
import models.account.AccountService;
import entities.strategy.TransactionStrategy;

public class DepositStrategy implements TransactionStrategy {
    private final AccountService accountService;

    public DepositStrategy(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void procesar(Transaction transaction) {
        if (transaction.getMonto() <= 0) {
            throw new IllegalArgumentException("Monto debe ser positivo.");
        }

        Account cuentaDestino = accountService.obtenerCuenta(transaction.getCuentaDestino());
        accountService.acreditar(cuentaDestino, transaction.getMonto());

        transaction.setReferencia("Depósito móvil");
    }
}
