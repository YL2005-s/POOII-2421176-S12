package models.bill;

import entities.Bill;

public interface BillService {
    Bill buscarPorReferencia(String referencia);
    boolean tieneDescuento(String referencia);
    void marcarPagada(Bill factura);
}
