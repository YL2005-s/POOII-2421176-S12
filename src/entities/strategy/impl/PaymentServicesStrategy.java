package entities.strategy.impl;

import entities.Bill;
import entities.Transaction;
import entities.strategy.TransactionStrategy;
import services.BillService;

public class PaymentServicesStrategy implements TransactionStrategy {
    private final BillService billService;

    public PaymentServicesStrategy(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void procesar(Transaction transaction) {
        Bill factura = billService.buscarPorReferencia(transaction.getReferencia());

        if (factura == null || factura.estaVencida()) {
            throw new IllegalArgumentException("Factura inválida o vencida.");
        }

        double monto = factura.getMonto();
        boolean hayPromocion = billService.tieneDescuento(transaction.getReferencia());

        if (hayPromocion) {
            monto *= 0.95;
        }

        transaction.setMonto(monto);
        billService.marcarPagada(factura);
    }
}
