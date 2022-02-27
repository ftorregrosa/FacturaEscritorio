/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controlador;

import facturacion.components.ComboItem;
import facturacion.datos.Producto;
import facturacion.datos.TipoIva;
import facturacion.datos.TipoProducto;
import facturacion.datos.TipoVenta;
import facturacion.servicio.Context;
import facturacion.servicio.ExportarProductos;
import facturacion.modelo.ProductoDAO;
import facturacion.servicio.Validaciones;
import facturacion.vista.VPrincipal;
import facturacion.vista.VProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JORDY
 */
public class CProducto implements KeyListener, MouseListener, ActionListener {

    VProducto vproducto;
    VPrincipal vprincipal;
    ProductoDAO productoDAO;
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

    public CProducto(Context contexto,VProducto vproducto, VPrincipal vmain) {
        productoDAO = contexto.getProductoServicio().getProductoDAO();
        this.vproducto = vproducto;
        this.vprincipal = vmain;

        this.vproducto.getBtnGuardar().addActionListener(this);
        this.vproducto.getBtnExportarTodo().addActionListener(this);
        this.vproducto.getBtnActualizar().addActionListener(this);
        this.vproducto.getBtnFiltrarBuscar().addActionListener(this);
        this.vproducto.getBtnModificar().addActionListener(this);
        this.vproducto.getTxtBuscarPoductos().addKeyListener(this);
        this.vproducto.getTablaProductosTodos().addMouseListener(this);

        modeloTablaProducto.addColumn("Codigo");
        modeloTablaProducto.addColumn("Nombre");
        modeloTablaProducto.addColumn("Tipo Producto");
        modeloTablaProducto.addColumn("Peso / Unidad");
        modeloTablaProducto.addColumn("Precio");
        modeloTablaProducto.addColumn("% IVA");
        vproducto.getTablaProductosTodos().setModel(modeloTablaProducto);
    }

    public void validarCampos() {
        validar.validarNumeros(vproducto.getTxtPvpProducto());
    }

    public void iniciarVentana() {
        vproducto.show();
        validarCampos();
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(0).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(1).setPreferredWidth(300);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(2).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(3).setPreferredWidth(80);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(4).setPreferredWidth(50);
        vproducto.getTablaProductosTodos().getColumnModel().getColumn(5).setPreferredWidth(80);

        productoDAO.visualizeProducto(modeloTablaProducto);

        vproducto.getTxtNombreProducto().setDocument(new Validaciones());

        vproducto.getBtnModificar().setEnabled(false);

        vproducto.getTablaProductosTodos().setDefaultRenderer(vproducto.getTablaProductosTodos().getColumnClass(0), colorfilas);

        TipoProducto.llenarComboCategoria(vproducto.getCbTipos());
        TipoProducto.llenarComboCategoria(vproducto.getCbTipoProducto());
        TipoVenta.llenarComboTipoVenta(vproducto.getCbTipoVenta());
        TipoIva.llenarComboTipoIva(vproducto.getCbTipoIva());

    }

    public void cleanTable() {
        for (int i = 0; i < vproducto.getTablaProductosTodos().getRowCount(); i++) {
            modeloTablaProducto.removeRow(i);
            i -= 1;
        }
    }
//finalizado

    public void limpiarCampos() {
        vproducto.getTxtNombreProducto().setText("");
        vproducto.getTxtPvpProducto().setText("");
        vproducto.getCbTipoVenta().setSelectedIndex(0);
        vproducto.getCbTipoProducto().setSelectedIndex(0);
        vproducto.getCbTipoIva().setSelectedIndex(0);
    }
//finalizado

    @Override
    public void keyReleased(KeyEvent ae) {
        if (vproducto.getTxtBuscarPoductos() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vproducto.getTxtBuscarPoductos().getText()));
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
                    Producto producto = productoDAO.findProductoById(Integer.valueOf((String)vproducto.getTablaProductosTodos().getValueAt(fila, 0)));
                    if (producto != null) {
                        vproducto.setCodigo(Integer.valueOf((String)vproducto.getTablaProductosTodos().getValueAt(fila, 0)));
                        vproducto.getTxtNombreProducto().setText(producto.getNombre());
                        vproducto.getCbTipoProducto().setSelectedItem(producto.getTipoProducto());
                        vproducto.getCbTipoVenta().setSelectedItem(producto.getTipoVenta());
                        vproducto.getTxtPvpProducto().setText(String.valueOf(producto.getPrecio()));
                        vproducto.getCbTipoIva().setSelectedItem(producto.getIva());
                    }
                    vproducto.getBtnGuardar().setEnabled(false);
                    vproducto.getBtnModificar().setEnabled(true);
                    //vproducto.getTablaProductosTodos().setEnabled(false);
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
                ExportarProductos.exportarExcel(vproducto.getTablaProductosTodos());
            } catch (IOException ex) {
                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (this.vproducto.getBtnGuardar() == ae.getSource()) {
            try {
                Producto producto = new Producto();
                if (!validaCampos(vproducto)) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
                    return;
                }

                producto.setNombre(vproducto.getTxtNombreProducto().getText());
                producto.setPrecio(Double.parseDouble(vproducto.getTxtPvpProducto().getText()));
                producto.setTipoVenta(Integer.valueOf(((ComboItem) vproducto.getCbTipoVenta().getSelectedItem()).getValue()));
                producto.setTipoProducto(((ComboItem) vproducto.getCbTipoProducto().getSelectedItem()).getValue());
                producto.setIva(Integer.valueOf(((ComboItem) vproducto.getCbTipoIva().getSelectedItem()).getValue()));
                productoDAO.addProducto(producto);
                limpiarCampos();
                cleanTable();
                productoDAO.visualizeProducto(modeloTablaProducto);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } else if (vproducto.getBtnActualizar() == ae.getSource()) {

            cleanTable();

            productoDAO.visualizeProducto(modeloTablaProducto);

        } else if (vproducto.getBtnFiltrarBuscar() == ae.getSource()) {

            cleanTable();
            try {
                productoDAO.visualizeProducto(modeloTablaProducto, ((ComboItem) vproducto.getCbTipos().getSelectedItem()).getValue());
            } catch (NullPointerException e) {

            }

        } else if (vproducto.getBtnModificar() == ae.getSource()) {
            Producto producto = new Producto();
            if (!validaCampos(vproducto)) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
                return;
            }
            producto.setCodigo(vproducto.getCodigo());
            producto.setNombre(vproducto.getTxtNombreProducto().getText());
            producto.setPrecio(Double.parseDouble(vproducto.getTxtPvpProducto().getText()));
            producto.setTipoVenta(Integer.valueOf(((ComboItem) vproducto.getCbTipoVenta().getSelectedItem()).getValue()));
            producto.setIva(Integer.valueOf(((ComboItem) vproducto.getCbTipoIva().getSelectedItem()).getValue()));
            producto.setTipoProducto(((ComboItem) vproducto.getCbTipoProducto().getSelectedItem()).getValue());

            productoDAO.modificarProducto(producto);
            vproducto.getTablaProductosTodos().setEnabled(true);
            limpiarCampos();
            cleanTable();
            productoDAO.visualizeProducto(modeloTablaProducto);
            vproducto.getBtnGuardar().setEnabled(true);
            vproducto.getBtnModificar().setEnabled(false);

        }

    }

    private Boolean validaCampos(VProducto producto) {
        if (producto.getTxtNombreProducto().getText().equals("")
                || producto.getTxtPvpProducto().getText().equals("")) {
            return false;
        }
        return true;
    }

  
}
