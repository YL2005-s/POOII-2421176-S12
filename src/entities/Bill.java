package entities;

import java.time.LocalDate;

public class Bill {
    private String referencia;
    private double monto;
    private LocalDate vencimiento;
    private boolean pagada;

    public Bill(String referencia, double monto, LocalDate vencimiento) {
        this.referencia = referencia;
        this.monto = monto;
        this.vencimiento = vencimiento;
        this.pagada = false;
    }

    public boolean estaVencida() {
        return LocalDate.now().isAfter(vencimiento);
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
}
