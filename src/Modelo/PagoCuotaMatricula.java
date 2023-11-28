package Modelo;

public class PagoCuotaMatricula {
    private int idPagoCuota;
    private Pago pago;
    private CuotaMatricula cuotaMatricula;

    public PagoCuotaMatricula() {}

    public PagoCuotaMatricula(int idPagoCuota, Pago pago, CuotaMatricula cuotaMatricula) {
        this.idPagoCuota = idPagoCuota;
        this.pago = pago;
        this.cuotaMatricula = cuotaMatricula;
    }

    public int getIdPagoCuota() {
        return idPagoCuota;
    }

    public void setIdPagoCuota(int idPagoCuota) {
        this.idPagoCuota = idPagoCuota;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public CuotaMatricula getCuotaMatricula() {
        return cuotaMatricula;
    }

    public void setCuotaMatricula(CuotaMatricula cuotaMatricula) {
        this.cuotaMatricula = cuotaMatricula;
    }
    
}
