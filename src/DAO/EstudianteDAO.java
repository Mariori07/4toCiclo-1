/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Estudiante;
import Util.ArchivoUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    private static final String ESTUDIANTES_FILE = "src/com/archivos_txt/estudiantes.txt";
    private List<Estudiante> estudiantes;

    public EstudianteDAO() {
        estudiantes = new ArrayList<>();
        ArchivoUtil.crearCarpeta(ESTUDIANTES_FILE);
        cargarEstudiantesDesdeArchivo();
    }

    private void cargarEstudiantesDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ESTUDIANTES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String codigoEstudiante = partes[0];
                String nombre = partes[1];
                String apellido = partes[2];
                int dni = Integer.parseInt(partes[3]);
                String telefono = partes[4];
                String grado = partes[5];
                String seccion = partes[6];

                // Crear un objeto Estudiante
                Estudiante estudiante = new Estudiante(codigoEstudiante, nombre, apellido, dni, telefono, grado, seccion);

                // Agregar el estudiante a la lista
                estudiantes.add(estudiante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEstudiantesEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ESTUDIANTES_FILE))) {
            for (Estudiante estudiante : estudiantes) {
                writer.println(estudiante.getCodigoEstudiante() + "," + estudiante.getNombre() + "," +
                        estudiante.getApellido() + "," + estudiante.getDNI() + "," + estudiante.getTelefono() +
                        "," + estudiante.getGrado() + "," + estudiante.getSeccion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        guardarEstudiantesEnArchivo();
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudiantes;
    }

    public Estudiante obtenerEstudiantePorCodigo(String codigoEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigoEstudiante().equals(codigoEstudiante)) {
                return estudiante;
            }
        }
        return null; // Estudiante no encontrado
    }

    public void actualizarEstudiante(Estudiante estudianteActualizado) {
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = estudiantes.get(i);
            if (estudiante.getCodigoEstudiante().equals(estudianteActualizado.getCodigoEstudiante())) {
                estudiantes.set(i, estudianteActualizado);
                guardarEstudiantesEnArchivo();
                return;
            }
        }
    }

    public void eliminarEstudiantePorCodigo(String codigoEstudiante) {
        estudiantes.removeIf(estudiante -> estudiante.getCodigoEstudiante().equals(codigoEstudiante));
        guardarEstudiantesEnArchivo();
    }
}

