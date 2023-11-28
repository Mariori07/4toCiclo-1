package Modelo;

public class Usuario extends Persona{
    private String user;
    private String pass;
    
public Usuario(){}

public String getUser() {return user;}
public void setUser(String user) {this.user = user;}

public String getPass() {return pass;}
public void setPass(String pass) {this.pass = pass;}
}