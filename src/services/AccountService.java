package services;

import entities.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> cuentas = new HashMap<>();;

    public AccountService() {
        cuentas.put("123", new Account("123", "BancoA", 5000.0, 2000.0));
        cuentas.put("456", new Account("456", "BancoB", 3000.0, 1000.0));
    }

    public Account obtenerCuenta(String numeroCuenta) {
        Account cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        return cuenta;
    }

    public void debitar(Account cuenta, double monto) {
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
    }

    public void acreditar(Account cuenta, double monto) {
        cuenta.setSaldo(cuenta.getSaldo() + monto);
    }
}
