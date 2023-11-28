package Util;
import java.io.File;
import java.io.IOException;

public class ArchivoUtil {
    public static void crearCarpeta(String path){
        File archivo = new File(path);
        File directorio = archivo.getParentFile();

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado con éxito.");
            } else {
                System.err.println("No se pudo crear el directorio.");
            }
        }
        try {
            if (!archivo.exists()) {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado con éxito.");
                } else {
                    System.err.println("No se pudo crear el archivo.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}