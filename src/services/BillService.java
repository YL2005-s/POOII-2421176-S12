package services;

import entities.Bill;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BillService {
    private final Map<String, Bill> facturas = new HashMap<>();

    public BillService() {
        facturas.put("AGUA123", new Bill("AGUA123", 150.0, LocalDate.now().plusDays(3)));
        facturas.put("LUZ789", new Bill("LUZ789", 80.0, LocalDate.now().minusDays(1)));
    }

    public Bill buscarPorReferencia(String referencia) {
        return facturas.get(referencia);
    }

    public boolean tieneDescuento(String referencia) {
        return "AGUA123".equals(referencia);
    }

    public void marcarPagada(Bill factura) {
        factura.setPagada(true);
    }
}
