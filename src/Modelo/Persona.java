package Modelo;

public abstract class Persona {
    private String Nombre;
    private String Apellido;
    private int DNI;
    private String Telefono;
    
    public Persona(){}

    public Persona(String Nombre, String Apellido, int DNI, String Telefono) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Telefono = Telefono;
    }

    public String getNombre() {return Nombre;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}

    public String getApellido() {return Apellido;}
    public void setApellido(String Apellido) {this.Apellido = Apellido;}

    public int getDNI() {return DNI;}
    public void setDNI(int DNI) {this.DNI = DNI;}

    public String getTelefono() {return Telefono;}
    public void setTelefono(String Telefono) {this.Telefono = Telefono;}
}