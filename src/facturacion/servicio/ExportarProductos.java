/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facturacion.servicio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author fran_
 */
public class ExportarProductos {
    public static void crearCarpetas() {
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngreso = sdf.format(fechaActual);
        File directorio = new File("C:\\Listas de Productos Excel\\" + fechaIngreso);
        directorio.mkdirs();
    }

    public static void exportarExcel(JTable t) throws IOException {
        HSSFWorkbook libro = new HSSFWorkbook();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngreso = sdf.format(fechaActual);
        crearCarpetas();
        JFileChooser chooser = new JFileChooser("C:/Listas de Productos Excel/" + fechaIngreso);
        chooser.setSelectedFile(new File("Lista Productos_" + fechaIngreso));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            String ruta = chooser.getSelectedFile().toString().concat(".xls");

            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                //Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Lista de Productos Cotización");
                hoja.setDisplayGridlines(false);
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        }
                    }
                }
                colorCeldas((HSSFWorkbook) libro, 4);
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
//me da un poco igual
    public static void colorCeldas(HSSFWorkbook hssfWorkbook, int numCelda) {
        HSSFSheet mySheet = hssfWorkbook.getSheetAt(0);

        // Creamos el estilo de celda del color ROJO
        HSSFCellStyle styleGroup3 = hssfWorkbook.createCellStyle();
        styleGroup3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleGroup3.setFillForegroundColor(HSSFColor.YELLOW.index);

        // Creamos el estilo de celda del color AMARILLO
        HSSFCellStyle styleGroup2 = hssfWorkbook.createCellStyle();
        styleGroup2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleGroup2.setFillForegroundColor(HSSFColor.RED.index);

        // Creamos el estilo de celda del color VERDE
        HSSFCellStyle styleGroup1 = hssfWorkbook.createCellStyle();
        styleGroup1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleGroup1.setFillForegroundColor(HSSFColor.WHITE.index);

        // Recorrer cada columna del excel
        // Comenzamos en 1 porque la 0 es el header del excel
        for (int i = 1; i <= mySheet.getLastRowNum(); i++) {
            HSSFRow myRow = mySheet.getRow(i);

            // Recorremos sobre cada celda de la columna seleccionada
            Iterator cellIter = myRow.cellIterator();
            while (cellIter.hasNext()) {

                HSSFCell myCell = (HSSFCell) cellIter.next();

                // Cuando sea la celda correcta
                if (myCell.getCellNum() == numCelda) {

                    // Hay que parsear el contenido de la celda de ser necesario, 
                    // La mayoría de las veces insertamos datos como "String"
                    if (Float.parseFloat(myCell.toString()) >= 0
                            && Float.parseFloat(myCell.toString()) <= 3) {

                        // Aplicamos el estilo correspondiente ROJO
                        myCell.setCellStyle(styleGroup3);
                    } else if (Float.parseFloat(myCell.toString()) > -100
                            && Float.parseFloat(myCell.toString()) < 0) {

                        // Aplicamos el estilo correspondiente AMARILLO
                        myCell.setCellStyle(styleGroup2);
                    } else if (Float.parseFloat(myCell.toString()) < 3) {

                        // Aplicamos el estilo correspondiente VERDE
                        myCell.setCellStyle(styleGroup1);
                    }
                }
            }
        }
    }

    
}
