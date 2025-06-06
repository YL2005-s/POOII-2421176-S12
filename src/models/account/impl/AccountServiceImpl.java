package models.account.impl;

import entities.Account;
import models.account.AccountService;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    private final Map<String, Account> cuentas = new HashMap<>();;

    public AccountServiceImpl() {
        cuentas.put("123", new Account("123", "BancoA", 5000.0, 2000.0));
        cuentas.put("456", new Account("456", "BancoB", 3000.0, 1000.0));
    }

    @Override
    public Account obtenerCuenta(String numeroCuenta) {
        Account cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        return cuenta;
    }

    @Override
    public void debitar(Account cuenta, double monto) {
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
    }

    @Override
    public void acreditar(Account cuenta, double monto) {
        cuenta.setSaldo(cuenta.getSaldo() + monto);
    }
}
