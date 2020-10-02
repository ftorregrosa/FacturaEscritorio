/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import facturacioncarniceria.datos.Producto;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.ProductoEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.ProductoDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VProducto;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author JORDY
 */
public class CProducto implements KeyListener, MouseListener, ActionListener {

    VProducto vproducto;
    Context contextProducto;
    VPrincipal vprincipal;
    ProductoDAO productoDAO;
    ProductoEstrategia strategyProducto = new ProductoEstrategia();
    Conexion connectionBD;
    Validaciones validar = new Validaciones();

    DefaultTableModel modeloTablaProducto = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 5) {
                return true;
            } else {
                return false;
            }
        }
    };
    ColorTabla colorfilas = new ColorTabla();

    TableRowSorter trs;

    public CProducto(Context context, VProducto vproducto, VPrincipal vmain) {
        productoDAO = new ProductoDAO();
        this.vproducto = vproducto;
        this.contextProducto = context;
        this.vprincipal = vmain;
        this.contextProducto = new Context(strategyProducto);

        this.vproducto.getBtnGuardar().addActionListener(this);
        this.vproducto.getBtnExportarTodo().addActionListener(this);
        this.vproducto.getBtnActualizar().addActionListener(this);
        this.vproducto.getBtnFiltrarBuscar().addActionListener(this);
        this.vproducto.getBtnModificar().addActionListener(this);
        this.vproducto.getTxtBuscarPoductos().addKeyListener(this);
        this.vproducto.getTablaProductosTodos().addMouseListener(this);

//        this.vproducto.getTxtBuscarBajoStock().addKeyListener(this);
//        this.vproducto.getTablaPoductosBajoStock().addMouseListener(this);
//        
//        this.vproducto.getTxtBuscarStockCero().addKeyListener(this);
//        this.vproducto.getTablaProductosCero().addMouseListener(this);
        this.connectionBD = new Conexion();

        modeloTablaProducto.addColumn("Codigo");
        modeloTablaProducto.addColumn("Medida");
        modeloTablaProducto.addColumn("Nombre");
        modeloTablaProducto.addColumn("Precio");
        modeloTablaProducto.addColumn("Stock");
        modeloTablaProducto.addColumn("IVA");
        modeloTablaProducto.addColumn("TIPO");
        modeloTablaProducto.addColumn("PUBLICO");
        modeloTablaProducto.addColumn("TARJETA");
        vproducto.getTablaProductosTodos().setModel(modeloTablaProducto);
    }

    public void validarCampos() {
        validar.validarNumeros(vproducto.getTxtPvpProducto());
        validar.validarNumeros(vproducto.getTxtCantidadProducto());
    }

    public void iniciarVentana() {
        vproducto.show();
        validarCampos();
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(0).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(1).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(2).setPreferredWidth(300);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(3).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(4).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(5).setPreferredWidth(50);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(6).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(7).setPreferredWidth(70);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(8).setPreferredWidth(70);

        contextProducto.RunVisualizar(modeloTablaProducto, 1);

        ultimoTabla();

        vproducto.getTxtMedidaProducto().setDocument(new Validaciones());
        vproducto.getTxtNombreProducto().setDocument(new Validaciones());
        vproducto.getTxtBuscarPoductos().setDocument(new Validaciones());
        vproducto.getTxtTipoProducto().setDocument(new Validaciones());

        vproducto.getBtnModificar().setEnabled(false);

        vprincipal.getLblCedula().getText();

        vproducto.getTablaProductosTodos().setDefaultRenderer(vproducto.getTablaProductosTodos().getColumnClass(0), colorfilas);

        // contextProducto.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
        contextProducto.RunLlenarCombo(vproducto.getCbTipos());
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(vproducto.getRbtnIvaSi());
        grupo.add(vproducto.getRbtnIvaNo());
        ivaCinco();
    }

    public void cleanTable() {
        for (int i = 0; i < vproducto.getTablaProductosTodos().getRowCount(); i++) {
            modeloTablaProducto.removeRow(i);
            i -= 1;
        }
    }

    public void ultimoTabla() {
        int indiceUltimaFila = modeloTablaProducto.getRowCount() - 1;
        if (indiceUltimaFila >= 0) {
            // vproducto.getTxtCodigoProductos().setText(vproducto.getTablaProductosTodos().getValueAt(indiceUltimaFila, 0).toString());
            int codigoMasUno = Integer.parseInt(vproducto.getTablaProductosTodos().getValueAt(indiceUltimaFila, 0).toString());
            int total = codigoMasUno + 1;

            if (codigoMasUno >= 1 && codigoMasUno <= 9) {
                vproducto.getTxtCodigoProductos().setText("000" + total);
            }
            if (codigoMasUno >= 10 && codigoMasUno <= 99) {
                vproducto.getTxtCodigoProductos().setText("00" + total);
            }
            if (codigoMasUno >= 100 && codigoMasUno <= 999) {
                vproducto.getTxtCodigoProductos().setText("0" + total);
            } else {
                vproducto.getTxtCodigoProductos().setText("" + total);
            }
        }

    }

    public void limpiarCampos() {
        vproducto.getTxtCodigoProductos().setText("");
        vproducto.getTxtNombreProducto().setText("");
        vproducto.getTxtPvpProducto().setText("");
        vproducto.getTxtMedidaProducto().setText("");
        vproducto.getTxtCantidadProducto().setText("");
        vproducto.getTxtTipoProducto().setText("");
    }

    public String tieneIva() {
        String iva = "NO";
        if (vproducto.getRbtnIvaSi().isSelected()) {
            iva = "SI";
            return iva;
        }
        return iva;
    }

    public void colorCeldas(HSSFWorkbook hssfWorkbook, int numCelda) {
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

    public void crearCarpetas() {
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngreso = sdf.format(fechaActual);
        File directorio = new File("C:\\Listas de Productos Excel\\" + fechaIngreso);
        directorio.mkdirs();
    }

    public void exportarExcel(JTable t) throws IOException {
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

    public void ivaCinco() {

        for (int j = 0; j < vproducto.getTablaProductosTodos().getRowCount(); j++) {
            if (vproducto.getTablaProductosTodos().getValueAt(j, 5).toString().equals("SI")) {
                double totalProductos1 = 0;
                totalProductos1 = ((Float.parseFloat(vproducto.getTablaProductosTodos().getValueAt(j, 3).toString()) * 0.12) + Float.parseFloat(vproducto.getTablaProductosTodos().getValueAt(j, 3).toString()));
                totalProductos1 = Math.round(totalProductos1 * 100) / 100d;

                modeloTablaProducto.setValueAt(Double.toString(totalProductos1), j, 7);
                modeloTablaProducto.fireTableRowsUpdated(j, 7);

                double totalProducto = ((totalProductos1 * 0.05) + totalProductos1);
                totalProducto = Math.round(totalProducto * 100) / 100d;

                modeloTablaProducto.setValueAt(Double.toString(totalProducto), j, 8);
                modeloTablaProducto.fireTableRowsUpdated(j, 8);
                totalProductos1 = 0;
            }else{
                double totalProductos1 = 0;
                totalProductos1 = ((Float.parseFloat(vproducto.getTablaProductosTodos().getValueAt(j, 3).toString()) * 1));
                totalProductos1 = Math.round(totalProductos1 * 100) / 100d;

                modeloTablaProducto.setValueAt(Double.toString(totalProductos1), j, 7);
                modeloTablaProducto.fireTableRowsUpdated(j, 7);

                double totalProducto = ((totalProductos1 * 0.05) + totalProductos1);
                totalProducto = Math.round(totalProducto * 100) / 100d;

                modeloTablaProducto.setValueAt(Double.toString(totalProducto), j, 8);
                modeloTablaProducto.fireTableRowsUpdated(j, 8);
                totalProductos1 = 0;
                
                
            }
            
            
        }

    }

    public void sumarPrecioProductos() {
        double totalProductos1 = 0;
        for (int j = 0; j < vproducto.getTablaProductosTodos().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + Float.parseFloat(vproducto.getTablaProductosTodos().getValueAt(j, 4).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            vproducto.getTxtCantidadkilos().setText(Double.toString(totalProductos1));
        }
    }

    @Override
    public void keyReleased(KeyEvent ae) {
        if (vproducto.getTxtBuscarPoductos() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vproducto.getTxtBuscarPoductos().getText(), 2));
        }

    }

    @Override
    public void keyTyped(KeyEvent ae) {
        if (vproducto.getTxtBuscarPoductos() == ae.getSource()) {
            trs = new TableRowSorter(modeloTablaProducto);
            vproducto.getTablaProductosTodos().setRowSorter(trs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed() && vproducto.getTablaProductosTodos() == e.getSource()) {
            try {
                int fila = vproducto.getTablaProductosTodos().getSelectedRow();
                if (fila >= 0) {
                    vproducto.getTxtCodigoProductos().setText(vproducto.getTablaProductosTodos().getValueAt(fila, 0).toString());
                    vproducto.getTxtMedidaProducto().setText(vproducto.getTablaProductosTodos().getValueAt(fila, 1).toString());
                    vproducto.getTxtNombreProducto().setText(vproducto.getTablaProductosTodos().getValueAt(fila, 2).toString());
                    vproducto.getTxtPvpProducto().setText(vproducto.getTablaProductosTodos().getValueAt(fila, 3).toString());
                    vproducto.getTxtCantidadProducto().setText(vproducto.getTablaProductosTodos().getValueAt(fila, 4).toString());
                    vproducto.getTxtTipoProducto().setText(vproducto.getTablaProductosTodos().getValueAt(fila, 6).toString());
                    vproducto.getBtnGuardar().setEnabled(false);
                    vproducto.getBtnModificar().setEnabled(true);
                    vproducto.getTablaProductosTodos().setEnabled(false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "No se Guardo");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.vproducto.getBtnExportarTodo() == ae.getSource()) {
            try {
                exportarExcel(vproducto.getTablaProductosTodos());
            } catch (IOException ex) {
                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (this.vproducto.getBtnGuardar() == ae.getSource()) {
            try {
                Producto producto = new Producto();
                if (vproducto.getTxtNombreProducto().getText().equals("") || vproducto.getTxtMedidaProducto().getText().equals("") || vproducto.getTxtPvpProducto().getText().equals("") || vproducto.getTxtCantidadProducto().getText().equals("") || vproducto.getTxtTipoProducto().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");

                } else {
                    producto.setCodigo(vproducto.getTxtCodigoProductos().getText());
                    producto.setMedida(vproducto.getTxtMedidaProducto().getText());
                    producto.setNomProducto(vproducto.getTxtNombreProducto().getText());
                    producto.setPrecio(Float.parseFloat(vproducto.getTxtPvpProducto().getText()));
                    producto.setExistente(Float.parseFloat(vproducto.getTxtCantidadProducto().getText()));
                    String comprobarIva = tieneIva();
                    producto.setTieneIva(comprobarIva);
                    producto.setTipoproducto(vproducto.getTxtTipoProducto().getText());
                    contextProducto.RunAnadir(producto, 1);
                    limpiarCampos();
                    cleanTable();
                    contextProducto.RunVisualizar(modeloTablaProducto, 1);
                    ivaCinco();
                    ultimoTabla();
                    
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } else if (vproducto.getBtnActualizar() == ae.getSource()) {

            cleanTable();
            
            contextProducto.RunVisualizar(modeloTablaProducto, 1);
            ivaCinco();

        } else if (vproducto.getBtnFiltrarBuscar() == ae.getSource()) {

            cleanTable();
            ivaCinco();
            try {
                contextProducto.RunVisualizarAnular(modeloTablaProducto, vproducto.getCbTipos().getSelectedItem().toString());
                sumarPrecioProductos();
            } catch (NullPointerException e) {

            }
            

        } else if (vproducto.getBtnModificar() == ae.getSource()) {
            Producto producto = new Producto();

            producto.setCodigo(vproducto.getTxtCodigoProductos().getText());
            producto.setMedida(vproducto.getTxtMedidaProducto().getText());
            producto.setNomProducto(vproducto.getTxtNombreProducto().getText());
            producto.setPrecio(Float.parseFloat(vproducto.getTxtPvpProducto().getText()));
            producto.setExistente(Float.parseFloat(vproducto.getTxtCantidadProducto().getText()));
            String comprobarIva = tieneIva();
            producto.setTieneIva(comprobarIva);
            producto.setTipoproducto(vproducto.getTxtTipoProducto().getText());
            contextProducto.RunModificar(producto, vproducto.getTxtCodigoProductos().getText());
            vproducto.getTablaProductosTodos().setEnabled(true);
            limpiarCampos();
            cleanTable();
            contextProducto.RunVisualizar(modeloTablaProducto, 1);
            ivaCinco();
            ultimoTabla();
            vproducto.getBtnGuardar().setEnabled(true);
            vproducto.getBtnModificar().setEnabled(false);

        }

    }
}
