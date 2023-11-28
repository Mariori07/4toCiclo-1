package Procesos;
import Vista.*;
import Modelo.*;

public class ProcesosLogin {
    public static void IniciarForma(Login f1){
        f1.setTitle("LOGIN");
        f1.setVisible(true);
        f1.setLocationRelativeTo(null);
    }
    
    public static Usuario LeerUser(Login f1){
        Usuario us = new Usuario();
        us.setUser(f1.txtUsuario.getText());
        us.setPass(String.valueOf(f1.psPass.getPassword()));
        return us;
    }
}