/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controlador;

import facturacion.components.ComboItem;
import static facturacion.controlador.CMain.controlVentanaUsuario;
import facturacion.datos.Cliente;
import facturacion.datos.Factura;
import facturacion.datos.Factura_;
import facturacion.servicio.Context;
import facturacion.modelo.Conexion;
import facturacion.modelo.FacturaDAO;
import facturacion.servicio.Validaciones;
import facturacion.servicio.ClienteServicio;
import facturacion.vista.VClientes;
import facturacion.vista.VFactura;
import facturacion.vista.VPrincipal;
import facturacion.vista.VFacturaIngreso;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.util.Date;
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
public class CFacturaIngreso implements KeyListener, MouseListener, ActionListener {

    Context contexto;
    VFacturaIngreso vfacturaInicio;
    VPrincipal vprincipal;
    FacturaDAO facturaDAO;
    ClienteServicio clienteServicio;
    Conexion connectionBD;
    Validaciones validar = new Validaciones();

    public static boolean controlVentana = false;

    DefaultTableModel modeloTablaFacturaIngreso = new DefaultTableModel() {
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

    public CFacturaIngreso(Context context, VFacturaIngreso vfacturaIngreso, VPrincipal vmain) {
        facturaDAO = new FacturaDAO();
        this.contexto=context;
        this.vfacturaInicio = vfacturaIngreso;
        
        this.vprincipal = vmain;
        this.clienteServicio = context.getClienteServicio();

        this.vfacturaInicio.getTxtBuscarFactura().addKeyListener(this);
        this.vfacturaInicio.getTbBuscarFactura().addMouseListener(this);
        this.vfacturaInicio.getBtnGenerar().addActionListener(this);
        this.vfacturaInicio.getBtnNuevoCliente().addActionListener(this);
        this.vfacturaInicio.getJmiAnular().addActionListener(this);
        this.vfacturaInicio.getCbCifClienteIngreso().addActionListener(this);
        

        this.connectionBD = new Conexion();

        modeloTablaFacturaIngreso.addColumn("NUM. FACTURA");
        modeloTablaFacturaIngreso.addColumn("CIF");
        modeloTablaFacturaIngreso.addColumn("CLIENTE");
        modeloTablaFacturaIngreso.addColumn("FECHA");
        modeloTablaFacturaIngreso.addColumn("AUTORIZADO");
        vfacturaIngreso.getTbBuscarFactura().setModel(modeloTablaFacturaIngreso);
    }

    public void validarCampos() {
       
    }

    public void iniciarVentana() {
        vfacturaInicio.show();
        validarCampos();
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(0).setPreferredWidth(50);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(1).setPreferredWidth(50);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(2).setPreferredWidth(100);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(3).setPreferredWidth(50);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(4).setPreferredWidth(100);
        
//ftorregrosa comenta
        //contextFacturaInicio.RunVisualizar(modeloTablaFacturaIngreso, 1);
        clienteServicio.llenarComboCLientes(vfacturaInicio.getCbCifClienteIngreso());
        vfacturaInicio.getTxtBuscarFactura().setDocument(new Validaciones());

        Date fechaActual = new Date();
        vfacturaInicio.getDcFechaFacturaIngreso().setDate(fechaActual);

    }

    public void cleanTable() {
        for (int i = 0; i < vfacturaInicio.getTbBuscarFactura().getRowCount(); i++) {
            modeloTablaFacturaIngreso.removeRow(i);
            i -= 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent ae) {
        if (vfacturaInicio.getTxtBuscarFactura() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vfacturaInicio.getTxtBuscarFactura().getText(), 2));
        }

    }

    @Override
    public void keyTyped(KeyEvent ae) {

        if (vfacturaInicio.getTxtBuscarFactura() == ae.getSource()) {
            trs = new TableRowSorter(modeloTablaFacturaIngreso);
            vfacturaInicio.getTbBuscarFactura().setRowSorter(trs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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

        if (this.vfacturaInicio.getCbCifClienteIngreso() == ae.getSource()) {

            ComboItem cb = (ComboItem) this.vfacturaInicio.getCbCifClienteIngreso().getSelectedItem();
            if (!"".equals(cb.getValue())) {
                Cliente cliente = clienteServicio.getCliente(Integer.valueOf(cb.getValue()));
                rellenaCliente(cliente);
                this.vfacturaInicio.getBtnGenerar().setEnabled(true);
            } else {
                limpiarCampos();
                this.vfacturaInicio.getBtnGenerar().setEnabled(false);
            }
        }
        if (this.vfacturaInicio.getBtnNuevoCliente() == ae.getSource()) {
            VClientes vcliente = new VClientes();
            vcliente.setVisible(true);
            this.vprincipal.getPnlPrincipal().add(vcliente);
            vcliente.toFront();
//
            Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
            Dimension FrameSize = vcliente.getSize();
            vcliente.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

            try {
                vcliente.setMaximum(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            CCliente controladorCliente = new CCliente(contexto,vcliente, vprincipal);
            if (controlVentanaUsuario == false) {
                this.vfacturaInicio.dispose();
                controladorCliente.iniciarVentana();
            }
        }
            if (this.vfacturaInicio.getBtnGenerar() == ae.getSource()) {

            Cliente cliente = contexto.getClienteServicio().getCliente(
                        Integer.valueOf(
                                ((ComboItem)this.vfacturaInicio.getCbCifClienteIngreso().getSelectedItem()).getValue()));


            Factura_ factura = new Factura_();
            factura.setFecha(new Date(System.currentTimeMillis()));
            factura.setCif(cliente.getCif());

            vfacturaInicio.dispose();

            VFactura vfactura = new VFactura();
            vfactura.setVisible(true);

            vprincipal.getPnlPrincipal().add(vfactura);
            vfactura.toFront();
            Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
            Dimension FrameSize = vfactura.getSize();
            vfactura.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

            try {
                vfactura.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            CFacturaDetalle controladorPedidos = new CFacturaDetalle(contexto, vfactura, vprincipal, factura, cliente);
            if (controlVentana == false) {                
                controladorPedidos.iniciarVentana();
            }
        }

    }

    private void rellenaCliente(Cliente cliente) {
        vfacturaInicio.getTxtNombreClieneIngreso().setText(cliente.getRazonSocial());
        vfacturaInicio.getTxtDireccionClienteIngreso().setText(cliente.getDireccion());
        vfacturaInicio.getTxtTelefonoClienteIngreso().setText(cliente.getTelefono());
        vfacturaInicio.getTxtPoblacionClienteIngreso().setText(cliente.getPoblacion());
    }
    private void limpiarCampos() {
        vfacturaInicio.getTxtNombreClieneIngreso().setText("");
        vfacturaInicio.getTxtDireccionClienteIngreso().setText("");
        vfacturaInicio.getTxtTelefonoClienteIngreso().setText("");
        vfacturaInicio.getTxtPoblacionClienteIngreso().setText("");
    }
}
