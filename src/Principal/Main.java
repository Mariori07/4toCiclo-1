package Principal;
import Controlador.ControladorLogin;
import Vista.*;

public class Main {
    public static Login f1;

    public static void main(String[] args) {
        f1 = new Login();
        ControladorLogin log = new ControladorLogin(f1);
        
        JIFAyuda fa = new JIFAyuda();
        fa.setVisible(true);
    }
}