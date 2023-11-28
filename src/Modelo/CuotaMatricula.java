package Modelo;
import java.util.Date;

public class CuotaMatricula {
    private int idCuota;
    private String descripcion;
    private double monto;
    private Date fechaInicio;
    private Date fechaLimite;
    private Estudiante estudiante;

    public CuotaMatricula(int idCuota, String descripcion, double monto, Date fechaInicio, Date fechaLimite, Estudiante estudiante) {
        this.idCuota = idCuota;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.estudiante = estudiante;
    }

    // Getters y setters
    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }   
}