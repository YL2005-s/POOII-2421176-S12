package entities.strategy.impl;

import entities.Account;
import entities.Transaction;
import models.account.AccountService;
import entities.strategy.TransactionStrategy;

public class TransferenceStrategy implements TransactionStrategy {
    private final AccountService cuentaService;

    public TransferenceStrategy(AccountService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @Override
    public void procesar(Transaction transaction) {
        Account origen = cuentaService.obtenerCuenta(transaction.getCuentaOrigen());
        Account destino = cuentaService.obtenerCuenta(transaction.getCuentaDestino());

        double monto = transaction.getMonto();
        boolean esInterbancaria = !origen.getBanco().equals(destino.getBanco());

        if (origen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        if (monto > origen.getLimiteDiario()) {
            throw new IllegalArgumentException("Límite diario excedido.");
        }

        double comision = esInterbancaria ? monto * 0.01 : 0;
        cuentaService.debitar(origen, monto + comision);
        cuentaService.acreditar(destino, monto);

        transaction.setComision(comision);
    }
}
