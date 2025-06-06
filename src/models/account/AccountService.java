package models.account;

import entities.Account;

public interface AccountService {
    Account obtenerCuenta(String numeroCuenta);
    void debitar(Account cuenta, double monto);
    void acreditar(Account cuenta, double monto);
}
