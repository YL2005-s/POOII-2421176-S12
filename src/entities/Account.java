package entities;

public class Account {
    private String numero;
    private String banco;
    private double saldo;
    private double limiteDiario;

    public Account(String numero, String banco, double saldo, double limiteDiario) {
        this.numero = numero;
        this.banco = banco;
        this.saldo = saldo;
        this.limiteDiario = limiteDiario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(double limiteDiario) {
        this.limiteDiario = limiteDiario;
    }
}
