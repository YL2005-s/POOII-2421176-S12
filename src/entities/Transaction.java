package entities;

public class Transaction {
    private int id;
    private Tipo tipo;
    private double monto;
    private String cuentaOrigen;
    private String cuentaDestino;
    private String referencia;
    private double comision = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        String origen = (cuentaOrigen == null || cuentaOrigen.isEmpty()) ? "N/A" : "CU" + cuentaOrigen;
        String destino = (cuentaDestino == null || cuentaDestino.isEmpty()) ? "N/A" : "CU" + cuentaDestino;

        StringBuilder sb = new StringBuilder();
        sb.append("Tipo=").append(tipo);
        sb.append(", Monto=").append(monto);
        sb.append(", Origen=").append(origen);
        sb.append(", Destino=").append(destino);

        if (referencia != null && !referencia.isEmpty()) {
            sb.append(", Referencia=").append(referencia);
        }

        return sb.toString();
    }

}
