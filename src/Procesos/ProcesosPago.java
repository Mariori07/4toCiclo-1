package Procesos;
import Vista.*;
import Modelo.*;

public class ProcesosPago {
    public static PagoCuotaMatricula LeerUser(JIFPago f1){
        PagoCuotaMatricula pc = new PagoCuotaMatricula();
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigoEstudiante(f1.txtCodEst.getText());
        return pc;
    }
    
    public static Estudiante LeerEstudiante(JIFPago f1){
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigoEstudiante(f1.txtCodEst.getText());
        return estudiante;
    }
}