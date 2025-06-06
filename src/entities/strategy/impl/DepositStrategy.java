package entities.strategy.impl;

import entities.Account;
import entities.Transaction;
import entities.strategy.TransactionStrategy;
import services.AccountService;

public class DepositStrategy implements TransactionStrategy {
    private final AccountService accountService;

    public DepositStrategy(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void procesar(Transaction transaction) {
        if (transaction.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }

        Account cuentaDestino = accountService.obtenerCuenta(transaction.getCuentaDestino());
        accountService.acreditar(cuentaDestino, transaction.getMonto());

        transaction.setReferencia("Depósito móvil");
    }
}
