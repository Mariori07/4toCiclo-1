package Modelo;
import java.util.Date;

public class Pago {
    private String idPago;
    private Date fechaPago;
    private Double monto;

    public Pago() {}

    public Pago(String idPago, Date fechaPago, Double monto) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", fechaPago=" + fechaPago + ", monto=" + monto +'}';
    }
}