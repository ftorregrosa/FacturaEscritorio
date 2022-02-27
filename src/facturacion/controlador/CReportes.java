/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controlador;

import facturacion.servicio.Context;
import facturacion.modelo.Conexion;
import facturacion.modelo.ReportesDAO;
import facturacion.servicio.Validaciones;
import facturacion.vista.VPrincipal;
import facturacion.vista.VReportes;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author JORDY
 */
public class CReportes implements KeyListener, MouseListener, ActionListener {

    VReportes vreportes;
    Context contextReportes;
    VPrincipal vprincipal;
    ReportesDAO reportesDAO;
    Conexion connectionBD;
    Validaciones validar =  new Validaciones();
    
    DefaultTableModel  modeloTablaVentas = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3) {
                return true;
            } else {
                return false;
            }
        }
    };
    
    DefaultTableModel  modeloTablaVentas1 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3) {
                return true;
            } else {
                return false;
            }
        }
    };
    
    DefaultTableModel  modeloTablaCompras = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3) {
                return true;
            } else {
                return false;
            }
        }
    };
    
    DefaultTableModel  modeloTablaCompras1 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3) {
                return true;
            } else {
                return false;
            }
        }
    };

    
    TableRowSorter trs;
    TableRowSorter trs1;
    TableRowSorter trs2;

    public CReportes(Context context, VReportes vreportes, VPrincipal vmain) {
        reportesDAO = new ReportesDAO();
        this.vreportes = vreportes;
        this.contextReportes = context;
        this.vprincipal = vmain;
        this.contextReportes = new Context(strategyReportes);
        
        this.vreportes.getTxtBuscarProducto().addKeyListener(this);
        this.vreportes.getTxtCodigoProducto().addKeyListener(this);
        this.vreportes.getTxtProveedorBuscar().addKeyListener(this);
        
        this.vreportes.getBtnActualizar().addActionListener(this);
        this.vreportes.getBtnActualizarCompra().addActionListener(this);
        this.vreportes.getBtnExportarCompras().addActionListener(this);
//       // this.vreportes.getJmiModificar().addActionListener(this);
        this.vreportes.getBtnExportarVentas().addActionListener(this);
        this.vreportes.getBtnExportar1().addActionListener(this);
        this.vreportes.getBtnExportar2().addActionListener(this);
        
        this.vreportes.getBtnActualizar1().addActionListener(this);
//       // this.vreportes.getJmiModificar().addActionListener(this);
//        this.vreportes.getTxtBuscar().addKeyListener(this);
//        this.vreportes.getTablaUsuario().addMouseListener(this);

        this.connectionBD = new Conexion();

        modeloTablaCompras.addColumn("#");
        modeloTablaCompras.addColumn("TIPO");
        modeloTablaCompras.addColumn("FECHA");
        modeloTablaCompras.addColumn("IDENT. RUC");
        modeloTablaCompras.addColumn("NOM. PROVEEDOR");
        modeloTablaCompras.addColumn("NUM. FACTURA");
        modeloTablaCompras.addColumn("SUB 12%");
        modeloTablaCompras.addColumn("BASE 0%");
        modeloTablaCompras.addColumn("IVA 12%");
        modeloTablaCompras.addColumn("TOTAL");
        modeloTablaCompras.addColumn("PAGO");
        modeloTablaCompras.addColumn("REALIZADO");
        vreportes.getTablaReporteCompra().setModel(modeloTablaCompras);
        
        modeloTablaCompras1.addColumn("#");
        modeloTablaCompras1.addColumn("FACT. COMPRA");
        modeloTablaCompras1.addColumn("COD. PRODUCTO");
        modeloTablaCompras1.addColumn("NOMBRE");
        modeloTablaCompras1.addColumn("CANTIDAD");
        modeloTablaCompras1.addColumn("PRECIO");
        modeloTablaCompras1.addColumn("TOTAL");
        modeloTablaCompras1.addColumn("FECHA");
        modeloTablaCompras1.addColumn("PROVEEDOR");
        modeloTablaCompras1.addColumn("DIRECCION");
        modeloTablaCompras1.addColumn("TELEFONO");
        vreportes.getTablaReporteCompra1().setModel(modeloTablaCompras1);

        modeloTablaVentas.addColumn("#");
        modeloTablaVentas.addColumn("TIPO");
        modeloTablaVentas.addColumn("FECHA");
        modeloTablaVentas.addColumn("IDENT. CEDULA");
        modeloTablaVentas.addColumn("NOM. CLIENTE");
        modeloTablaVentas.addColumn("NUM. FACTURA");
        modeloTablaVentas.addColumn("SUB 12%");
        modeloTablaVentas.addColumn("BASE 0%");
        modeloTablaVentas.addColumn("IVA 12%");
        modeloTablaVentas.addColumn("TOTAL");
        modeloTablaVentas.addColumn("PAGO");
        modeloTablaVentas.addColumn("REALIZADO");
        modeloTablaVentas.addColumn("ANULADO");
        vreportes.getTablaReporteVenta().setModel(modeloTablaVentas);
        
        modeloTablaVentas1.addColumn("#");
        modeloTablaVentas1.addColumn("TIPO");
        modeloTablaVentas1.addColumn("FECHA");
        modeloTablaVentas1.addColumn("IDENT. CEDULA");
        modeloTablaVentas1.addColumn("NOM. CLIENTE");
        modeloTablaVentas1.addColumn("NUM. FACTURA");
        modeloTablaVentas1.addColumn("SUB 12%");
        modeloTablaVentas1.addColumn("BASE 0%");
        modeloTablaVentas1.addColumn("IVA 12%");
        modeloTablaVentas1.addColumn("TOTAL");
        modeloTablaVentas1.addColumn("PAGO");
        modeloTablaVentas1.addColumn("REALIZADO");

        vreportes.getTablaReporteVenta1().setModel(modeloTablaVentas1);
    }

    public void validarCampos() {

    }

    public void iniciarVentana() {
        vreportes.show();
        validarCampos();
        
        vreportes.getTxtCodigoProducto().setDocument(new Validaciones());
        vreportes.getTxtProveedorBuscar().setDocument(new Validaciones());
        vreportes.getTxtBuscarProducto().setDocument(new Validaciones());
        
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(0).setPreferredWidth(2);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(1).setPreferredWidth(50);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(2).setPreferredWidth(40);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(3).setPreferredWidth(60);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(4).setPreferredWidth(150);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(5).setPreferredWidth(80);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(6).setPreferredWidth(20);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(7).setPreferredWidth(20);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(8).setPreferredWidth(20);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(9).setPreferredWidth(20);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(10).setPreferredWidth(20);
        vreportes.getTablaReporteCompra().getColumnModel().getColumn(11).setPreferredWidth(100);
        
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(0).setPreferredWidth(15);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(1).setPreferredWidth(25);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(2).setPreferredWidth(15);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(3).setPreferredWidth(200);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(4).setPreferredWidth(25);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(5).setPreferredWidth(25);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(6).setPreferredWidth(25);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(7).setPreferredWidth(25);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(8).setPreferredWidth(100);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(9).setPreferredWidth(200);
        vreportes.getTablaReporteCompra1().getColumnModel().getColumn(10).setPreferredWidth(50);
        
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(0).setPreferredWidth(2);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(1).setPreferredWidth(50);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(2).setPreferredWidth(40);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(3).setPreferredWidth(60);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(4).setPreferredWidth(150);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(5).setPreferredWidth(80);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(6).setPreferredWidth(20);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(7).setPreferredWidth(20);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(8).setPreferredWidth(20);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(9).setPreferredWidth(20);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(10).setPreferredWidth(20);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(11).setPreferredWidth(100);
        vreportes.getTablaReporteVenta().getColumnModel().getColumn(12).setPreferredWidth(50);
        
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(0).setPreferredWidth(2);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(1).setPreferredWidth(50);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(2).setPreferredWidth(40);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(3).setPreferredWidth(60);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(4).setPreferredWidth(150);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(5).setPreferredWidth(80);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(6).setPreferredWidth(20);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(7).setPreferredWidth(20);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(8).setPreferredWidth(20);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(9).setPreferredWidth(20);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(10).setPreferredWidth(20);
        vreportes.getTablaReporteVenta1().getColumnModel().getColumn(11).setPreferredWidth(100);

        
        Date fechaActual = new Date();
        vreportes.getDcInicial().setDate(fechaActual);
        vreportes.getDcFinal().setDate(fechaActual);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        vreportes.getDcInicial1().setDate(fechaActual);
        vreportes.getDcFinal1().setDate(fechaActual);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        String fechaIngreso = sdf.format(vreportes.getDcInicial().getDate());
        String fechaIngresoFin = sdf.format(vreportes.getDcFinal().getDate());
        
     
        String fechaIngresoFin1 = sdf1.format(vreportes.getDcFinal1().getDate());

        contextReportes.RunvisualizeCompraVenta(modeloTablaCompras, fechaIngreso, fechaIngresoFin, 1);
        contextReportes.RunvisualizeCompraVenta(modeloTablaVentas, fechaIngreso, fechaIngresoFin, 2);
        
        contextReportes.RunvisualizeCompraVenta(modeloTablaVentas1, fechaIngreso, fechaIngresoFin1, 3);
        
        contextReportes.RunVisualizar(modeloTablaCompras1, 0);

        vprincipal.getLblCedula().getText();
        // contextReportes.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
    }

    public void cleanTableCompra() {
        for (int i = 0; i < vreportes.getTablaReporteCompra().getRowCount(); i++) {
            modeloTablaCompras.removeRow(i);
            i -= 1;
        }
    }
    
    public void cleanTableCompra1() {
        for (int i = 0; i < vreportes.getTablaReporteCompra1().getRowCount(); i++) {
            modeloTablaCompras1.removeRow(i);
            i -= 1;
        }
    }

    public void cleanTableVenta() {
        for (int i = 0; i < vreportes.getTablaReporteVenta().getRowCount(); i++) {
            modeloTablaVentas.removeRow(i);
            i -= 1;
        }
    }
    
    public void cleanTableVenta1() {
        for (int i = 0; i < vreportes.getTablaReporteVenta1().getRowCount(); i++) {
            modeloTablaVentas1.removeRow(i);
            i -= 1;
        }
    }

    public void limpiarCampos() {
        vreportes.getTxtBuscarProducto().setText(null);
        vreportes.getTxtCodigoProducto().setText("");
        vreportes.getTxtProveedorBuscar().setText("");
    }

    @Override
    public void keyReleased(KeyEvent ae) {
         if (vreportes.getTxtCodigoProducto()== ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)"+vreportes.getTxtCodigoProducto().getText(), 2));
        }
         if (vreportes.getTxtBuscarProducto()== ae.getSource()) {
            trs1.setRowFilter(RowFilter.regexFilter("(?i)"+vreportes.getTxtBuscarProducto().getText(), 3));
        }
         if (vreportes.getTxtProveedorBuscar()== ae.getSource()) {
            trs2.setRowFilter(RowFilter.regexFilter("(?i)"+vreportes.getTxtProveedorBuscar().getText(), 8));
        }

    }

    @Override
    public void keyTyped(KeyEvent ae) {
        if (vreportes.getTxtCodigoProducto()== ae.getSource()) {
           trs = new TableRowSorter(modeloTablaCompras1);
           vreportes.getTablaReporteCompra1().setRowSorter(trs);
        }
        if (vreportes.getTxtBuscarProducto()== ae.getSource()) {
           trs1 = new TableRowSorter(modeloTablaCompras1);
           vreportes.getTablaReporteCompra1().setRowSorter(trs1);
        }
        if (vreportes.getTxtProveedorBuscar()== ae.getSource()) {
           trs2 = new TableRowSorter(modeloTablaCompras1);
           vreportes.getTablaReporteCompra1().setRowSorter(trs2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getClickCount() == 2 && !e.isConsumed() && vreportes.getTablaUsuario()== e.getSource()) {
//            try {
//                int fila = vreportes.getTablaUsuario().getSelectedRow();
//                if (fila >= 0) {
//                    vreportes.getTxtCedula().setText(vreportes.getTablaUsuario().getValueAt(fila,0).toString());
//                    vreportes.getTxtNombres().setText(vreportes.getTablaUsuario().getValueAt(fila,1).toString());
//                    vreportes.getTxtApellido().setText(vreportes.getTablaUsuario().getValueAt(fila,2).toString());
//                    vreportes.getBtnGuardarUsuario().setEnabled(false);
//                    vreportes.getBtnModificarUsuario().setEnabled(true);
//                    vreportes.getTablaUsuario().setEnabled(false);
//                }
//            }catch (Exception ex) {
//                JOptionPane.showMessageDialog(vprincipal, "No se Guardo");
//            }
//        }
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
    
        public void crearCarpetas(String compraVenta, String fechaInicio, String fechaFinal) {

        File directorio = new File("A:\\COMPRA VENTA\\" + compraVenta+"_"+fechaInicio+"_"+fechaFinal);
        directorio.mkdirs();
    }

    public void exportarExcel(JTable t, String compraVenta) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngreso = sdf.format(vreportes.getDcInicial().getDate());
        String fechaIngresoFin = sdf.format(vreportes.getDcFinal().getDate());
        crearCarpetas(compraVenta,fechaIngreso,fechaIngresoFin);
        
        JFileChooser chooser = new JFileChooser("A:/COMPRA VENTA/" + compraVenta+"_"+fechaIngreso+"_"+fechaIngresoFin);
        chooser.setSelectedFile(new File("REPORTE_" + compraVenta+"_"+fechaIngreso+"_"+fechaIngresoFin));
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
                Workbook libro = new HSSFWorkbook();
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
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
    
    
    public void cajaNum(){
        int totalColumnaCompra = vreportes.getTablaReporteVenta1().getRowCount();
        vreportes.getTxtNumFacturas().setText(Integer.toString(totalColumnaCompra));
        double totalProductos1 = 0;
        for (int j = 0; j < vreportes.getTablaReporteVenta1().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + Float.parseFloat(vreportes.getTablaReporteVenta1().getValueAt(j, 9).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            System.out.println(totalProductos1);
            vreportes.getTxtCaja().setText(Double.toString(totalProductos1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.vreportes.getBtnExportarVentas() == ae.getSource()) {
            try {
                exportarExcel(vreportes.getTablaReporteVenta(),"VENTAS");
            } catch (IOException ex) {
                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.vreportes.getBtnExportarCompras()== ae.getSource()) {
            try {
                exportarExcel(vreportes.getTablaReporteCompra(),"COMPRAS");
            } catch (IOException ex) {
                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (this.vreportes.getBtnExportar1()== ae.getSource()) {
            try {
                exportarExcel(vreportes.getTablaReporteVenta1(),"VENTAS/CAJA");
            } catch (IOException ex) {
                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (this.vreportes.getBtnExportar2()== ae.getSource()) {
            try {
                exportarExcel(vreportes.getTablaReporteCompra1(),"COMPRAS/PROVEEDORES");
            } catch (IOException ex) {
                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.vreportes.getBtnActualizar() == ae.getSource()) {
            cleanTableCompra();
            cleanTableVenta();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String fechaIngreso = sdf.format(vreportes.getDcInicial().getDate());
            String fechaIngresoFin = sdf.format(vreportes.getDcFinal().getDate());

//        vreportes.getDcInicial().setFormatoFecha(fechaActual);
//        vreportes.getDcFinal().setDate(fechaActual);
            contextReportes.RunvisualizeCompraVenta(modeloTablaCompras, fechaIngreso, fechaIngresoFin, 1);
            contextReportes.RunvisualizeCompraVenta(modeloTablaVentas, fechaIngreso, fechaIngresoFin, 2);
        }
        
        if (this.vreportes.getBtnActualizarCompra()== ae.getSource()) {
            cleanTableCompra1();
            limpiarCampos();
            vreportes.getTxtBuscarProducto().requestFocus();
            contextReportes.RunVisualizar(modeloTablaCompras1, 0);
            
        }
        
  

        if (this.vreportes.getBtnActualizar1()== ae.getSource()) {
            cleanTableVenta1();
            vreportes.getTxtCaja().setText("");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaIngreso = sdf.format(vreportes.getDcInicial1().getDate());
            String fechaIngresoFin = sdf.format(vreportes.getDcFinal1().getDate());
            contextReportes.RunvisualizeCompraVenta(modeloTablaVentas1, fechaIngreso, fechaIngresoFin, 3);
            cajaNum();
        }

    }
}
