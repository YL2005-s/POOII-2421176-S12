package entities.strategy.impl;

import entities.Bill;
import entities.Transaction;
import models.bill.BillService;
import entities.strategy.TransactionStrategy;

public class PaymentServicesStrategy implements TransactionStrategy {
    private final BillService facturaService;

    public PaymentServicesStrategy(BillService facturaService) {
        this.facturaService = facturaService;
    }

    @Override
    public void procesar(Transaction transaction) {
        Bill factura = facturaService.buscarPorReferencia(transaction.getReferencia());

        if (factura == null || factura.estaVencida()) {
            throw new IllegalArgumentException("Factura inválida o vencida.");
        }

        double monto = factura.getMonto();
        boolean hayPromocion = facturaService.tieneDescuento(transaction.getReferencia());

        if (hayPromocion) {
            monto *= 0.95;
        }

        transaction.setMonto(monto);
        facturaService.marcarPagada(factura);
    }
}
