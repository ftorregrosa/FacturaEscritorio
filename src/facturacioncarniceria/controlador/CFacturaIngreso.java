/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import static facturacioncarniceria.controlador.CMain.controlVentanaUsuario;
import facturacioncarniceria.datos.Factura;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.FacturaEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.FacturaDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VClientes;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VFacturaIngreso;
import facturacioncarniceria.vista.VFactura;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;

/**
 *
 * @author JORDY
 */
public class CFacturaIngreso implements KeyListener, MouseListener, ActionListener {

    VFacturaIngreso vfacturaInicio;
    Context contextFacturaInicio;
    VPrincipal vprincipal;
    FacturaDAO facturaDAO;
    FacturaEstrategia strategyFacturaIngreso = new FacturaEstrategia();
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
        this.vfacturaInicio = vfacturaIngreso;
        this.contextFacturaInicio = context;
        this.vprincipal = vmain;
        this.contextFacturaInicio = new Context(strategyFacturaIngreso);

        this.vfacturaInicio.getBtnBuscarCliente().addActionListener(this);
        this.vfacturaInicio.getTxtBuscarFactura().addKeyListener(this);
        this.vfacturaInicio.getTbBuscarFactura().addMouseListener(this);
        this.vfacturaInicio.getBtnHacerFactura().addActionListener(this);
        this.vfacturaInicio.getJmiAnular().addActionListener(this);
        

        this.connectionBD = new Conexion();

        modeloTablaFacturaIngreso.addColumn("NUM. FACTURA");
        modeloTablaFacturaIngreso.addColumn("CEDULA");
        modeloTablaFacturaIngreso.addColumn("CLIENTE");
        modeloTablaFacturaIngreso.addColumn("FECHA");
        modeloTablaFacturaIngreso.addColumn("AUTORIZADO");
        vfacturaIngreso.getTbBuscarFactura().setModel(modeloTablaFacturaIngreso);
    }

    public void validarCampos() {
        validar.validarNumeros(vfacturaInicio.getTxtCedulaClienteIngreso());
        validar.limitarCaracteres(vfacturaInicio.getTxtCedulaClienteIngreso(), 13);
    }

    public void iniciarVentana() {
        vfacturaInicio.show();
        validarCampos();
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(0).setPreferredWidth(50);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(1).setPreferredWidth(50);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(2).setPreferredWidth(100);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(3).setPreferredWidth(50);
        vfacturaInicio.getTbBuscarFactura().getColumnModel().getColumn(4).setPreferredWidth(100);
        
        contextFacturaInicio.RunVisualizar(modeloTablaFacturaIngreso, 1);
        vfacturaInicio.getTxtCedulaClienteIngreso().setDocument(new Validaciones());
        vfacturaInicio.getTxtBuscarFactura().setDocument(new Validaciones());
        vprincipal.getLblCedula().getText();

        DateFormat df = DateFormat.getDateInstance();
        Date fechaActual = new Date();
        vfacturaInicio.getDcFechaFacturaIngreso().setDate(fechaActual);
       

        // contextFacturaInicio.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
    }

    public void cleanTable() {
        for (int i = 0; i < vfacturaInicio.getTbBuscarFactura().getRowCount(); i++) {
            modeloTablaFacturaIngreso.removeRow(i);
            i -= 1;
        }
    }

    public void limpiarCampos() {
        vfacturaInicio.getTxtCedulaClienteIngreso().setText("");
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
//        if (e.getClickCount() == 2 && !e.isConsumed() && vfacturaInicio.getTablaUsuario()== e.getSource()) {
//            try {
//                int fila = vfacturaInicio.getTablaUsuario().getSelectedRow();
//                if (fila >= 0) {
//                    vfacturaInicio.getTxtCedula().setText(vfacturaInicio.getTablaUsuario().getValueAt(fila,0).toString());
//                    vfacturaInicio.getTxtNombres().setText(vfacturaInicio.getTablaUsuario().getValueAt(fila,1).toString());
//                    vfacturaInicio.getTxtApellido().setText(vfacturaInicio.getTablaUsuario().getValueAt(fila,2).toString());
//                    vfacturaInicio.getBtnGuardarUsuario().setEnabled(false);
//                    vfacturaInicio.getBtnModificarUsuario().setEnabled(true);
//                    vfacturaInicio.getTablaUsuario().setEnabled(false);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        Factura factura = new Factura();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngresoFactura = sdf.format(vfacturaInicio.getDcFechaFacturaIngreso().getDate());
        if (this.vfacturaInicio.getBtnBuscarCliente() == ae.getSource()) {
            contextFacturaInicio.RunVisualizarContra(vfacturaInicio.getTxtNombreClieneIngreso(), vfacturaInicio.getTxtDireccionClienteIngreso(), vfacturaInicio.getTxtTelefonoClienteIngreso(), vfacturaInicio.getTxtCorreoClienteIngreso(), vfacturaInicio.getTxtCedulaClienteIngreso().getText());

            try {
                
                if (vfacturaInicio.getTxtCedulaClienteIngreso().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene los campos");
                } else if (vfacturaInicio.getTxtNombreClieneIngreso().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Cliente no encontrado");

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

                    CCliente controladorCliente = new CCliente(contextFacturaInicio, vcliente, vprincipal);
                    if (controlVentanaUsuario == false) {
                        vcliente.getTxtCedulaCliente().setText(vfacturaInicio.getTxtCedulaClienteIngreso().getText());
                        controladorCliente.iniciarVentana();
                    }

                } else {
                    
                    vfacturaInicio.getBtnHacerFactura().setEnabled(true);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }
        }
        if (this.vfacturaInicio.getBtnHacerFactura() == ae.getSource()) {

            factura.setFechaFactura(fechaIngresoFactura);
            factura.setAnuladaFactura("NO");
            contextFacturaInicio.RunAnadir(factura, 1);

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
            CFacturaDetalle controladorPedidos = new CFacturaDetalle(contextFacturaInicio, vfactura, vprincipal, 1);
            if (controlVentana == false) {
                vfactura.getTxtNombreCliente().setText(vfacturaInicio.getTxtNombreClieneIngreso().getText());
                vfactura.getTxtCedulaCliente().setText(vfacturaInicio.getTxtCedulaClienteIngreso().getText());
                vfactura.getTxtDireccionCliente().setText(vfacturaInicio.getTxtDireccionClienteIngreso().getText());
                vfactura.getTxtTelefonoCliente().setText(vfacturaInicio.getTxtTelefonoClienteIngreso().getText());
                vfactura.getTxtCorreoCliente().setText(vfacturaInicio.getTxtCorreoClienteIngreso().getText());
                vfactura.getTxtFechaFactura().setText(fechaIngresoFactura);
                controladorPedidos.iniciarVentana();
            }
        }
        if (this.vfacturaInicio.getJmiAnular() == ae.getSource()) {
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
            CFacturaDetalle controladorPedidos = new CFacturaDetalle(contextFacturaInicio, vfactura, vprincipal, 2);
            if (controlVentana == false) {
                int fila = vfacturaInicio.getTbBuscarFactura().getSelectedRow();
                if (fila >= 0) {
                    vfactura.getTxtNombreCliente().setText(vfacturaInicio.getTbBuscarFactura().getValueAt(fila, 2).toString());
                    vfactura.getTxtCedulaCliente().setText(vfacturaInicio.getTbBuscarFactura().getValueAt(fila, 1).toString());
                    vfactura.getTxtNumeroFactura().setText(vfacturaInicio.getTbBuscarFactura().getValueAt(fila, 0).toString());
                    vfactura.getTxtFechaFactura().setText(vfacturaInicio.getTbBuscarFactura().getValueAt(fila, 3).toString());
                    controladorPedidos.iniciarVentana();
                }
            }      
        }
    }
}
