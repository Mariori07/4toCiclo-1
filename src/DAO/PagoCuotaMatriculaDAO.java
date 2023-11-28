/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.CuotaMatricula;
import Modelo.Estudiante;
import Modelo.Pago;
import Modelo.PagoCuotaMatricula;
import Util.ArchivoUtil;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PagoCuotaMatriculaDAO {

    private static final String PAGO_CUOTA_FILE = "src/com/archivos_txt/pagosCuotas.txt";
    private List<PagoCuotaMatricula> pagosCuotas;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static PagoDAO pagoController = new PagoDAO();
    private static CuotaMatriculaDAO cuotaMatriculaController = new CuotaMatriculaDAO();
    private static int contadorID = 1;

    public PagoCuotaMatriculaDAO() {
        pagosCuotas = new ArrayList<>();
        ArchivoUtil.crearCarpeta(PAGO_CUOTA_FILE);
        cargarPagosCuotasDesdeArchivo();
    }

    private void cargarPagosCuotasDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PAGO_CUOTA_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idPagoCuota = Integer.parseInt(partes[0]);
                String idPago = partes[1];
                int idCuota = Integer.parseInt(partes[2]);

                Pago pago = pagoController.obtenerPagoPorId(idPago);
                CuotaMatricula cuota = cuotaMatriculaController.obtenerCuotaMatriculaPorId(idCuota);

                PagoCuotaMatricula pagoCuota = new PagoCuotaMatricula(idPagoCuota, pago, cuota);

                // Agregar el pagoCuota a la lista
                pagosCuotas.add(pagoCuota);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarPagosCuotasEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PAGO_CUOTA_FILE))) {
            for (PagoCuotaMatricula pagoCuota : pagosCuotas) {
                writer.println(pagoCuota.getIdPagoCuota() + "," + pagoCuota.getPago().getIdPago() + "," + pagoCuota.getCuotaMatricula().getIdCuota());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PagoCuotaMatricula> buscarPorCodigoEstudiante(String codigoEstudiante) {
        List<PagoCuotaMatricula> pagosPorEstudiante = new ArrayList<>();

        for (PagoCuotaMatricula pagoCuotaMatricula : pagosCuotas) {
            Estudiante estudiante = pagoCuotaMatricula.getCuotaMatricula().getEstudiante();
            if (estudiante.getCodigoEstudiante().equals(codigoEstudiante)) {
                pagosPorEstudiante.add(pagoCuotaMatricula);
            }
        }

        return pagosPorEstudiante;
    }

    public void crearPagoCuotaMatricula(PagoCuotaMatricula pagoCuotaMatricula) {
        pagoCuotaMatricula.setIdPagoCuota(generarId());
        pagosCuotas.add(pagoCuotaMatricula);
        guardarPagosCuotasEnArchivo();
    }
    
    public int generarId() {
        Random random = new Random();
        return random.nextInt(100000); 
    }

    public List<PagoCuotaMatricula> obtenerTodosLosPagosCuotas() {
        return pagosCuotas;
    }

    public PagoCuotaMatricula obtenerPagoCuotaMatriculaPorId(int idPagoCuota) {
        for (PagoCuotaMatricula pagoCuota : pagosCuotas) {
            if (pagoCuota.getIdPagoCuota() == idPagoCuota) {
                return pagoCuota;
            }
        }
        return null; // PagoCuotaMatricula no encontrada
    }

    public void actualizarPagoCuotaMatricula(PagoCuotaMatricula pagoCuotaMatriculaActualizada) {
        for (int i = 0; i < pagosCuotas.size(); i++) {
            PagoCuotaMatricula pagoCuota = pagosCuotas.get(i);
            if (pagoCuota.getIdPagoCuota() == pagoCuotaMatriculaActualizada.getIdPagoCuota()) {
                pagosCuotas.set(i, pagoCuotaMatriculaActualizada);
                guardarPagosCuotasEnArchivo();
                return;
            }
        }
    }

    public void eliminarPagoCuotaMatriculaPorId(int idPagoCuota) {
        pagosCuotas.removeIf(pagoCuota -> pagoCuota.getIdPagoCuota() == idPagoCuota);
        guardarPagosCuotasEnArchivo();
    }

    public void generarReporteEstudiante(Estudiante estudiante, List<PagoCuotaMatricula> pagosCuotaMatricula, String codigoPago) {
        Document document = new Document();

        try {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fechaActual = dateFormat1.format(new Date());

            String ruta = "src/com/reportes/Boleta_Pago_Estudiante_" + fechaActual + ".pdf";
            ArchivoUtil.crearCarpeta(ruta);
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            // Título del reporte
            Paragraph titulo = new Paragraph("Boleta de Pagos - Estudiante", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            String fechaPago = dateFormat.format(pagosCuotaMatricula.get(0).getPago().getFechaPago());
            // Datos del estudiante
            Paragraph datosEstudiante = new Paragraph();
            datosEstudiante.add(new Chunk("Fecha de Pago: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            datosEstudiante.add(fechaPago+ "\n");
            datosEstudiante.add(new Chunk("Código: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            datosEstudiante.add(estudiante.getCodigoEstudiante() + "\n");
            datosEstudiante.add(new Chunk("Nombre: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            datosEstudiante.add(estudiante.getNombre() + " " + estudiante.getApellido() + "\n");
            datosEstudiante.add(new Chunk("Grado: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            datosEstudiante.add(estudiante.getGrado() + "\n");
            datosEstudiante.add(new Chunk("Sección: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            datosEstudiante.add(estudiante.getSeccion() + "\n\n");
            document.add(datosEstudiante);

            // Tabla de cuotas pagadas
            PdfPTable tablaCuotas = new PdfPTable(2);
            tablaCuotas.setWidthPercentage(100);

            PdfPCell cellDescripcion = new PdfPCell(new Phrase("Descripción", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            PdfPCell cellMonto = new PdfPCell(new Phrase("Monto", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

            tablaCuotas.addCell(cellDescripcion);
            tablaCuotas.addCell(cellMonto);

            double totalPagado = 0.0;

            for (PagoCuotaMatricula pago : pagosCuotaMatricula) {
                if (pago.getCuotaMatricula().getEstudiante().getCodigoEstudiante().equalsIgnoreCase(estudiante.getCodigoEstudiante()) &&
                        pago.getPago().getIdPago().equalsIgnoreCase(codigoPago)) {
                    tablaCuotas.addCell(pago.getCuotaMatricula().getDescripcion());
                    tablaCuotas.addCell(String.valueOf(pago.getCuotaMatricula().getMonto()));
                    totalPagado += pago.getCuotaMatricula().getMonto();
                }
            }

            document.add(tablaCuotas);

            // Total pagado
            Paragraph total = new Paragraph("\nTotal pagado: " + totalPagado, FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            document.add(total);

            document.close();

            System.out.println("Boleta de pago generada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
