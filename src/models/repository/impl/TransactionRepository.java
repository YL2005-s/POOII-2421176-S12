package models.repository.impl;

import entities.Tipo;
import entities.Transaction;
import models.repository.CRUDRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void update(Transaction transaction) {
        String sql = "UPDATE transacciones SET tipo = ?, monto = ?, cuenta_origen = ?, cuenta_destino = ?, " +
                "referencia = ?, comision = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaction.getTipo().name());
            stmt.setDouble(2, transaction.getMonto());
            stmt.setString(3, transaction.getCuentaOrigen());
            stmt.setString(4, transaction.getCuentaDestino());
            stmt.setString(5, transaction.getReferencia());
            stmt.setDouble(6, transaction.getComision());
            stmt.setInt(7, transaction.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM transacciones WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> list() {
        List<Transaction> transacciones = new ArrayList<>();
        String sql = "SELECT * FROM transacciones";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt("id"));
                t.setTipo(Tipo.valueOf(rs.getString("tipo")));
                t.setMonto(rs.getDouble("monto"));
                t.setCuentaOrigen(rs.getString("cuenta_origen"));
                t.setCuentaDestino(rs.getString("cuenta_destino"));
                t.setReferencia(rs.getString("referencia"));
                t.setComision(rs.getDouble("comision"));
                transacciones.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transacciones;
    }
}
