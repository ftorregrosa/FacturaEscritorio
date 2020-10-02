/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VComprasHacer;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.modelo.CompraDAO;
import facturacioncarniceria.datos.Compras;
import facturacioncarniceria.estrategia.ComprasEstrategia;
import facturacioncarniceria.vista.VFactura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JORDY
 */
public class CComprasHacer implements ActionListener,KeyListener, MouseListener  {

    VComprasHacer vcomprasHacer;
    Context contextCompra;
    VPrincipal vmain;
    Validaciones validar = new Validaciones();
    ComprasEstrategia strategyCompra = new ComprasEstrategia();

    TableRowSorter trs;
     
    DefaultTableModel modeloTablaCompraDetalle = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 2) {
                return true;
            } else {
                return false;
            }
        }
    };
     
     DefaultTableModel modeloTablaProductosCompra = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3) {
                return true;
            } else {
                return false;
            }
        }
    };
    
    public CComprasHacer(Context context, VComprasHacer vcomprasHacer, VPrincipal vmain) {
        this.vcomprasHacer = vcomprasHacer;
        this.contextCompra = context;
        this.vmain = vmain;
//        this.vcomprasHacer.getBtnGuardaFactura().addActionListener(this); 
        this.contextCompra = new Context(strategyCompra);
        this.vcomprasHacer.getBtnGuardar().addActionListener(this);
        this.vcomprasHacer.getBtnActualizar().addActionListener(this);
        
        this.vcomprasHacer.getTxtBuscarProductosCompras().addKeyListener(this);
        
        this.vcomprasHacer.getBtnCalcularFactura().addActionListener(this);
        this.vcomprasHacer.getBtnEditarTabla().addActionListener(this);
        
        this.vcomprasHacer.getTablaCompras().addMouseListener(this);
        this.vcomprasHacer.getTablaProductosCompras().addMouseListener(this);
                
        modeloTablaProductosCompra.addColumn("CODIGO");
        modeloTablaProductosCompra.addColumn("MEDIDA");
        modeloTablaProductosCompra.addColumn("NOMBRE PODUCTO");
        this.vcomprasHacer.getTablaProductosCompras().setModel(modeloTablaProductosCompra);
        
        modeloTablaCompraDetalle.addColumn("CODIGO");
        modeloTablaCompraDetalle.addColumn("NOMBRE PRODUCTO");
        modeloTablaCompraDetalle.addColumn("CANTIDAD");
        modeloTablaCompraDetalle.addColumn("PRECIO");
        modeloTablaCompraDetalle.addColumn("TOTAL");
        modeloTablaCompraDetalle.addColumn("IVA");
        this.vcomprasHacer.getTablaCompras().setModel(modeloTablaCompraDetalle);
    }

    public void iniciarVentana() {  
        vcomprasHacer.show();
        
        contextCompra.RunvisualizeRucProveedor(vcomprasHacer.getTxtRucProveedor(), vcomprasHacer.getTxtNombreProveedor().getText());
        contextCompra.RunVisualizar(modeloTablaProductosCompra, 0);
        
        vcomprasHacer.getTablaProductosCompras().getColumnModel().getColumn(0).setPreferredWidth(50);
        vcomprasHacer.getTablaProductosCompras().getColumnModel().getColumn(1).setPreferredWidth(50);
        vcomprasHacer.getTablaProductosCompras().getColumnModel().getColumn(2).setPreferredWidth(550);
        
        vcomprasHacer.getTablaCompras().getColumnModel().getColumn(0).setPreferredWidth(30);
        vcomprasHacer.getTablaCompras().getColumnModel().getColumn(1).setPreferredWidth(250);
        vcomprasHacer.getTablaCompras().getColumnModel().getColumn(2).setPreferredWidth(20);
        vcomprasHacer.getTablaCompras().getColumnModel().getColumn(3).setPreferredWidth(15);
        vcomprasHacer.getTablaCompras().getColumnModel().getColumn(4).setPreferredWidth(15);
        
        DateFormat df = DateFormat.getDateInstance();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        vcomprasHacer.getTxtFechaCompra().setText(sdf.format(fechaActual));
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(vcomprasHacer.getRbEfectivo());
        grupo.add(vcomprasHacer.getRbTarjeta());
        

    }
    
    public void modificarCantidadSumarProducto(String cantidad, String codigoProducto) {
        
        float cantidadProOriginal = contextCompra.RunCantidadProducto(codigoProducto);
        float cantidadProducto = Float.valueOf(cantidad);
        float totalProducto = 0;
        totalProducto = cantidadProOriginal + cantidadProducto;
        contextCompra.RunModificarCantidad(totalProducto, codigoProducto);
    }
    
     public void cleanTableCompras() {
        for (int i = 0; i < vcomprasHacer.getTablaProductosCompras().getRowCount(); i++) {
            modeloTablaProductosCompra.removeRow(i);
            i -= 1;
        }
    }
     
     public void cleanTableComprasDetalle() {
        for (int i = 0; i < vcomprasHacer.getTablaCompras().getRowCount(); i++) {
            modeloTablaCompraDetalle.removeRow(i);
            i -= 1;
        }
    }

    public int aumentarProductos(String codigo) {
        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount(); j++) {
            String codigoEnRenglon = (String) vcomprasHacer.getTablaCompras().getValueAt(j, 0);
            if (codigo.equals(codigoEnRenglon)) {
                return j;
            }
        }
        return -1;

    }
  
    public void aumentarStock() {
        for (int fila = 0; fila < vcomprasHacer.getTablaCompras().getRowCount() - 4; fila++) {
            modificarCantidadSumarProducto(vcomprasHacer.getTablaCompras().getValueAt(fila, 2).toString(), vcomprasHacer.getTablaCompras().getValueAt(fila, 0).toString());
        }
    }

    public void guardarCompraVenta() {
        double subtotalcero = subTotalCeroCompraVenta();
        double subTotal = subTotalCompraVenta();
        double subIvaTotal = subTotal - subtotalcero;
        double ivaTotal = ((0.12 * subIvaTotal));
        ivaTotal = Math.round(ivaTotal * 100) / 100d;
        double superTotal = (ivaTotal + subtotalcero + subIvaTotal);
        superTotal = Math.round(superTotal * 100) / 100d;

        int metodoPago = metodoPago();
        if (metodoPago == 1) {
            double ivaTarjeta = ((0.10 * superTotal));
            double superTotalTarjeta = (superTotal + ivaTarjeta);

            superTotalTarjeta = Math.round(superTotalTarjeta * 100) / 100d;
            String metodo = Integer.toString(metodoPago);
            contextCompra.RunAnadirCompraVenta(metodo, vmain.getLblCedula().getText(), vcomprasHacer.getTxtFechaCompra().getText(), vcomprasHacer.getTxtRucProveedor().getText(), vcomprasHacer.getTxtNombreProveedor().getText(), vcomprasHacer.getTxtNumeroFactura().getText(), subIvaTotal, subtotalcero, ivaTotal, superTotalTarjeta);
        }
        if (metodoPago == 2) {
            String metodo = Integer.toString(metodoPago);
            contextCompra.RunAnadirCompraVenta(metodo, vmain.getLblCedula().getText(), vcomprasHacer.getTxtFechaCompra().getText(), vcomprasHacer.getTxtRucProveedor().getText(), vcomprasHacer.getTxtNombreProveedor().getText(), vcomprasHacer.getTxtNumeroFactura().getText(), subIvaTotal, subtotalcero, ivaTotal, superTotal);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vcomprasHacer.getBtnGuardar()==ae.getSource())
        {
            int confirmado = JOptionPane.showConfirmDialog(vmain, "¿Lo confirmas?");

            if (JOptionPane.OK_OPTION == confirmado) {                
                contextCompra.RunAnadirCompra(vcomprasHacer.getTxtRucProveedor().getText(), vcomprasHacer.getTxtNumeroFactura().getText(), vcomprasHacer.getTxtFechaCompra().getText(), modeloTablaCompraDetalle);
                aumentarStock();
                vcomprasHacer.getBtnGuardar().setEnabled(true);
                guardarCompraVenta();
                vcomprasHacer.dispose();
            }
            
        }
        
        if(vcomprasHacer.getBtnActualizar()==ae.getSource())
        {
            cleanTableCompras();
            JOptionPane.showMessageDialog(vmain, "Actualizando....");
            contextCompra.RunVisualizar(modeloTablaProductosCompra, 1);
        }
        
        if (this.vcomprasHacer.getBtnEditarTabla()== ae.getSource()) {  
            vcomprasHacer.getBtnGuardar().setEnabled(false);
            quitarUltimasFilas();
            vcomprasHacer.getBtnGuardar().setEnabled(false);
            vcomprasHacer.getTablaCompras().setEnabled(true);
        }
        if (this.vcomprasHacer.getBtnCalcularFactura() == ae.getSource()) {  
            
            calcularTotal();
        }
    }
      
    @Override
    public void keyReleased(KeyEvent ae) {
        if (vcomprasHacer.getTxtBuscarProductosCompras()== ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vcomprasHacer.getTxtBuscarProductosCompras().getText(), 2));
        }
    }

    @Override
    public void keyTyped(KeyEvent ae) {
        if (vcomprasHacer.getTxtBuscarProductosCompras()== ae.getSource()) {
            trs = new TableRowSorter(modeloTablaProductosCompra);
            vcomprasHacer.getTablaProductosCompras().setRowSorter(trs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void totalProductos() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + Float.parseFloat(vcomprasHacer.getTablaCompras().getValueAt(j, 4).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            vcomprasHacer.getTxtTotalProductosComprados().setText(Double.toString(totalProductos1));
        }

    }

    public double subTotalCero() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount(); j++) {
            if (vcomprasHacer.getTablaCompras().getValueAt(j, 5).toString().equals("NO")) {
                totalProductos1 = totalProductos1 + Float.parseFloat(vcomprasHacer.getTablaCompras().getValueAt(j, 4).toString());
                totalProductos1 = Math.round(totalProductos1 * 100) / 100d;

            
            }
        }
        return totalProductos1;
    }

//    public double sumarPrecioProductosTabla() {
//        double totalProductos1 = 0;
//        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount(); j++) {
//            totalProductos1 = totalProductos1 + Float.parseFloat(vcomprasHacer.getTablaCompras().getValueAt(j, 4).toString());
//            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
//            JOptionPane.showMessageDialog(vmain, totalProductos1);
//            return totalProductos1;
//            
//        }
//        return 0;
//    }

    public void calcularTotal() {
        if (vcomprasHacer.getRbTarjeta().isSelected() == false && vcomprasHacer.getRbEfectivo().isSelected() == false) {

            vcomprasHacer.getBtnCalcularFactura().setEnabled(true);

            JOptionPane.showMessageDialog(vmain, "Seleccione un metodo de Pago");
        } else if (vcomprasHacer.getRbEfectivo().isSelected() == true) {

            double subtotalcero = subTotalCero();
            double subTotal = Double.parseDouble(vcomprasHacer.getTxtTotalProductosComprados().getText());
            double subIvaTotal = subTotal - subtotalcero;
            
            double subIvaTotaldosDecimales;
            subIvaTotaldosDecimales = Math.round(subIvaTotal * 100) / 100d;
            
            Object[] objectoSubtotal = new Object[6];
            objectoSubtotal[0] = " ";
            objectoSubtotal[1] = " "; 
            objectoSubtotal[2] = " ";
            objectoSubtotal[3] = "SUB 12%";
            objectoSubtotal[4] = subIvaTotaldosDecimales;
            modeloTablaCompraDetalle.addRow(objectoSubtotal);

            Object[] objectoIvaCero = new Object[6];
            objectoIvaCero[0] = " ";
            objectoIvaCero[1] = " ";
            objectoIvaCero[2] = " ";
            objectoIvaCero[3] = "SUB 0%";
            objectoIvaCero[4] = subtotalcero;
            modeloTablaCompraDetalle.addRow(objectoIvaCero);

            double ivaTotal = ((0.12 * subIvaTotal));
            ivaTotal = Math.round(ivaTotal * 100) / 100d;
            Object[] objectoIva = new Object[6];
            objectoIva[0] = " ";
            objectoIva[1] = " ";
            objectoIva[2] = " ";
            objectoIva[3] = "I.V.A 12%";
            objectoIva[4] = ivaTotal;
            modeloTablaCompraDetalle.addRow(objectoIva);

            double superTotal = (ivaTotal + subtotalcero + subIvaTotal);
            superTotal = Math.round(superTotal * 100) / 100d;
            Object[] objectoTotal = new Object[6];
            objectoTotal[0] = " ";
            objectoTotal[1] = " ";
            objectoTotal[2] = " ";
            objectoTotal[3] = "TOTAL";
            objectoTotal[4] = superTotal;
            modeloTablaCompraDetalle.addRow(objectoTotal);

            vcomprasHacer.getTxtTotalProductosComprados().setText(Double.toString(superTotal));

            vcomprasHacer.getBtnCalcularFactura().setEnabled(false);
            vcomprasHacer.getBtnGuardar().setEnabled(true);
            vcomprasHacer.getTablaProductosCompras().setEnabled(false);
            vcomprasHacer.getTablaCompras().setEnabled(false);
            vcomprasHacer.getBtnEditarTabla().setEnabled(true);

        } else if (vcomprasHacer.getRbTarjeta().isSelected() == true) {

            double subtotalcero = subTotalCero();
            double subTotal = Double.parseDouble(vcomprasHacer.getTxtTotalProductosComprados().getText());
            double subIvaTotal = subTotal - subtotalcero;
            
            double subIvaTotaldosDecimales;
            subIvaTotaldosDecimales = Math.round(subIvaTotal * 100) / 100d;
            
            Object[] objectoSubtotal = new Object[6];
            objectoSubtotal[0] = " ";
            objectoSubtotal[1] = " "; 
            objectoSubtotal[2] = " ";
            objectoSubtotal[3] = "SUB 12%";
            objectoSubtotal[4] = subIvaTotaldosDecimales;
            modeloTablaCompraDetalle.addRow(objectoSubtotal);

            Object[] objectoIvaCero = new Object[6];
            objectoIvaCero[0] = " ";
            objectoIvaCero[1] = " ";
            objectoIvaCero[2] = " ";
            objectoIvaCero[3] = "SUB 0%";
            objectoIvaCero[4] = subtotalcero;
            modeloTablaCompraDetalle.addRow(objectoIvaCero);

            double ivaTotal = ((0.12 * subIvaTotal));
            ivaTotal = Math.round(ivaTotal * 100) / 100d;
            Object[] objectoIva = new Object[6];
            objectoIva[0] = " ";
            objectoIva[1] = " ";
            objectoIva[2] = " ";
            objectoIva[3] = "I.V.A 12%";
            objectoIva[4] = ivaTotal;
            modeloTablaCompraDetalle.addRow(objectoIva);

            double superTotal = (ivaTotal + subtotalcero + subIvaTotal);

            double ivaTarjeta = ((0.08 * superTotal));
            double superTotalTarjeta = (superTotal + ivaTarjeta);
            
            superTotalTarjeta = Math.round(superTotalTarjeta * 100) / 100d;

            Object[] objectoTotal = new Object[6];
            objectoTotal[0] = " ";
            objectoTotal[1] = " ";
            objectoTotal[2] = " ";
            objectoTotal[3] = "TOTAL";
            objectoTotal[4] = superTotalTarjeta;
            modeloTablaCompraDetalle.addRow(objectoTotal);

            vcomprasHacer.getTxtTotalProductosComprados().setText(Double.toString(superTotalTarjeta));
            vcomprasHacer.getBtnCalcularFactura().setEnabled(false);
            vcomprasHacer.getBtnGuardar().setEnabled(true);
            vcomprasHacer.getTablaProductosCompras().setEnabled(false);
            vcomprasHacer.getTablaCompras().setEnabled(false);
            vcomprasHacer.getBtnEditarTabla().setEnabled(true);

        }
    }

    public void quitarUltimasFilas() {

        modeloTablaCompraDetalle.removeRow(vcomprasHacer.getTablaCompras().getRowCount() - 1);
        modeloTablaCompraDetalle.removeRow(vcomprasHacer.getTablaCompras().getRowCount() - 1);
        modeloTablaCompraDetalle.removeRow(vcomprasHacer.getTablaCompras().getRowCount() - 1);
        modeloTablaCompraDetalle.removeRow(vcomprasHacer.getTablaCompras().getRowCount() - 1);
        vcomprasHacer.getTablaProductosCompras().setEnabled(true);
        vcomprasHacer.getBtnEditarTabla().setEnabled(false);
        vcomprasHacer.getBtnCalcularFactura().setEnabled(true);

        sumarPrecioProductos();

    }
    
    public double subTotalCeroCompraVenta() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount()-4; j++) {
            if (vcomprasHacer.getTablaCompras().getValueAt(j, 5).toString().equals("NO")) {
                totalProductos1 = totalProductos1 + Float.parseFloat(vcomprasHacer.getTablaCompras().getValueAt(j, 4).toString());
                totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            }
        }
        return totalProductos1;
    }

    public double subTotalCompraVenta() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount() - 4; j++) {

            totalProductos1 = totalProductos1 + Float.parseFloat(vcomprasHacer.getTablaCompras().getValueAt(j, 4).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;

        }
        return totalProductos1;
    }
    
    public int metodoPago()
    {
        int metodo =1;
        if(vcomprasHacer.getRbEfectivo().isSelected())
        {
            metodo=2;
            return metodo;
        }
        return metodo;
    }
    


    public void sumarPrecioProductos() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcomprasHacer.getTablaCompras().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + Float.parseFloat(vcomprasHacer.getTablaCompras().getValueAt(j, 4).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            vcomprasHacer.getTxtTotalProductosComprados().setText(Double.toString(totalProductos1));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed() && vcomprasHacer.getTablaProductosCompras() == e.getSource()) {
            String[] botones = {"SI", "NO"};
            String ivaSiNo = "SI";
            try {
                int fila = vcomprasHacer.getTablaProductosCompras().getSelectedRow();
                if (fila >= 0) {
//                    
                    String cantidad = JOptionPane.showInputDialog(vmain, "Intruduzca la Cantidad");
                    String precioProducto = JOptionPane.showInputDialog(vmain, "Introduzca el Precio");
                    int variable = JOptionPane.showOptionDialog(null, "Tiene I.V.A", "I.V.A", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null/*icono*/, botones, botones[0]);
                    float cantidadProducto = Float.parseFloat(cantidad);
                    int filaDuplicada = aumentarProductos(vcomprasHacer.getTablaProductosCompras().getValueAt(fila, 0).toString());
                     if (variable == 0) {
                            ivaSiNo = "SI";
                        } else if (variable == 1) {
                            ivaSiNo = "NO";
                        }

                    if (filaDuplicada >= 0) {
                        String totalCantidad = vcomprasHacer.getTablaCompras().getValueAt(filaDuplicada, 2).toString();
                        float tc = Float.parseFloat(totalCantidad);
                        tc = tc + cantidadProducto;

                        float precio = Float.parseFloat(precioProducto);

                        double totalProducto = tc * precio;
                        totalProducto = Math.round(totalProducto * 100) / 100d;
                        String to = Double.toString(totalProducto);

                        modeloTablaCompraDetalle.setValueAt(Float.toString(tc), filaDuplicada, 2);
                        modeloTablaCompraDetalle.setValueAt(to, filaDuplicada, 4);
                        modeloTablaCompraDetalle.setValueAt(precio, filaDuplicada, 3);
                        modeloTablaCompraDetalle.setValueAt(ivaSiNo, filaDuplicada, 5);
                        modeloTablaCompraDetalle.fireTableRowsUpdated(filaDuplicada, filaDuplicada);
                    } else {

                        float precio = Float.parseFloat(precioProducto);

                        double totalProducto = cantidadProducto * precio;
                        totalProducto = Math.round(totalProducto * 100) / 100d;

                        String to = Double.toString(totalProducto);
                        String pvpProducto = Float.toString(precio);

                       
                        String datosTabla[] = new String[6];
                        datosTabla[0] = vcomprasHacer.getTablaProductosCompras().getValueAt(fila, 0).toString();
                        datosTabla[1] = vcomprasHacer.getTablaProductosCompras().getValueAt(fila, 2).toString();
                        datosTabla[2] = cantidad;
                        datosTabla[3] = pvpProducto;
                        datosTabla[4] = to;
                        datosTabla[5] = ivaSiNo;
                        modeloTablaCompraDetalle.addRow(datosTabla);

                        vcomprasHacer.getTablaCompras().getSelectionModel().removeSelectionInterval(fila, fila);
                        //vcomprasHacer.getTablaCompras().scrollRectToVisible(vcomprasHacer.getTablaCompras().getCellRect(vcomprasHacer.getTablaCompras().getRowCount() - 1, 0, true));
                    }

                    int totalColumnaCompra = vcomprasHacer.getTablaCompras().getRowCount();
                    vcomprasHacer.getTxtCantidadProductosComprados().setText(Integer.toString(totalColumnaCompra));
                    sumarPrecioProductos();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vmain, "No se Registro la Compra   " + ex);
            }
        }

        if (e.getClickCount() == 2 && !e.isConsumed() && vcomprasHacer.getTablaCompras() == e.getSource()) {
            try {
                int fila = vcomprasHacer.getTablaCompras().getSelectedRow();
                if (fila >= 0) {
                    modeloTablaCompraDetalle.removeRow(fila);
                    int totalColumnaCompra = vcomprasHacer.getTablaCompras().getRowCount();
                    vcomprasHacer.getTxtCantidadProductosComprados().setText(Integer.toString(totalColumnaCompra));
                    sumarPrecioProductos();

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vmain, "No se Guardo");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        // System.out.println("asdaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    public void mouseExited(MouseEvent me) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


