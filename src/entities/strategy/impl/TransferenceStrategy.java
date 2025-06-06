package entities.strategy.impl;

import entities.Account;
import entities.Transaction;
import entities.strategy.TransactionStrategy;
import services.AccountService;

public class TransferenceStrategy implements TransactionStrategy {
    private final AccountService accountService;

    public TransferenceStrategy(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void procesar(Transaction transaction) {
        Account origen = accountService.obtenerCuenta(transaction.getCuentaOrigen());
        Account destino = accountService.obtenerCuenta(transaction.getCuentaDestino());

        double monto = transaction.getMonto();
        boolean esInterbancaria = !origen.getBanco().equals(destino.getBanco());

        if (origen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        if (monto > origen.getLimiteDiario()) {
            throw new IllegalArgumentException("Límite diario excedido.");
        }

        double comision = esInterbancaria ? monto * 0.01 : 0;
        accountService.debitar(origen, monto + comision);
        accountService.acreditar(destino, monto);

        transaction.setComision(comision);
    }
}
