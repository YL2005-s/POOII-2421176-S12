package models.repository.impl;

import entities.Transaction;
import models.repository.CRUDRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionRepository extends CRUDRepository<Transaction> {

    @Override
    public void create(Transaction transaction) {
        String sql = "INSERT INTO transacciones (tipo, monto, cuenta_origen, cuenta_destino, referencia, comision) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaction.getTipo().name());
            stmt.setDouble(2, transaction.getMonto());
            stmt.setString(3, transaction.getCuentaOrigen());
            stmt.setString(4, transaction.getCuentaDestino());
            stmt.setString(5, transaction.getReferencia());
            stmt.setDouble(6, transaction.getComision());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Transaction> list() {
        return List.of();
    }
}
