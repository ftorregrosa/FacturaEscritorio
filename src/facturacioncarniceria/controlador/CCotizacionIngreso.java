/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import static facturacioncarniceria.controlador.CMain.controlVentanaUsuario;
import facturacioncarniceria.datos.Cotizacion;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.CotizacionEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.CotizacionDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VClientes;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VCotizacionesIngreso;
import facturacioncarniceria.vista.VCotizacion;
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
public class CCotizacionIngreso implements KeyListener, MouseListener, ActionListener {

    VCotizacionesIngreso vcotizacionInicio;
    Context contextCotizacionIngreso;
    VPrincipal vprincipal;
    CotizacionDAO cotizacionDAO;
    CotizacionEstrategia strategyCotizacionIngreso = new CotizacionEstrategia();
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

    public CCotizacionIngreso(Context context, VCotizacionesIngreso vcotizacionIngreso, VPrincipal vmain) {
        cotizacionDAO = new CotizacionDAO();
        this.vcotizacionInicio = vcotizacionIngreso;
        this.contextCotizacionIngreso = context;
        this.vprincipal = vmain;
        this.contextCotizacionIngreso = new Context(strategyCotizacionIngreso);

        this.vcotizacionInicio.getBtnGuardaCotizacion().addActionListener(this);
        this.vcotizacionInicio.getBtnModificarCotizacion().addActionListener(this);

        this.connectionBD = new Conexion();
//
//        modeloTablaFacturaIngreso.addColumn("NUM. FACTURA");
//        modeloTablaFacturaIngreso.addColumn("CEDULA");
//        modeloTablaFacturaIngreso.addColumn("CLIENTE");
//        modeloTablaFacturaIngreso.addColumn("FECHA");
//        modeloTablaFacturaIngreso.addColumn("AUTORIZADO");
//        vcotizacionIngreso.getTbBuscarFactura().setModel(modeloTablaFacturaIngreso);
    }

    public void validarCampos() {
        validar.validarLetras(vcotizacionInicio.getTxtNombreCotizacion());
    }

    public void iniciarVentana() {
        vcotizacionInicio.show();
        validarCampos();
//        vcotizacionInicio.getTbBuscarFactura().getColumnModel().getColumn(0).setPreferredWidth(50);
//        vcotizacionInicio.getTbBuscarFactura().getColumnModel().getColumn(1).setPreferredWidth(50);
//        vcotizacionInicio.getTbBuscarFactura().getColumnModel().getColumn(2).setPreferredWidth(100);
//        vcotizacionInicio.getTbBuscarFactura().getColumnModel().getColumn(3).setPreferredWidth(50);
//        vcotizacionInicio.getTbBuscarFactura().getColumnModel().getColumn(4).setPreferredWidth(100);
        
        contextCotizacionIngreso.RunVisualizar(modeloTablaFacturaIngreso, 1);
        vcotizacionInicio.getTxtNombreCotizacion().setDocument(new Validaciones());
        vprincipal.getLblCedula().getText();

        DateFormat df = DateFormat.getDateInstance();
        Date fechaActual = new Date();
        vcotizacionInicio.getJdCotizacionIncio().setDate(fechaActual);
        vcotizacionInicio.getJdCotizacionFin().setDate(fechaActual);
       

        // contextCotizacionIngreso.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
    }

    public void cleanTable() {
//        for (int i = 0; i < vcotizacionInicio.getTbBuscarFactura().getRowCount(); i++) {
//            modeloTablaFacturaIngreso.removeRow(i);
//            i -= 1;
////        }
    }

    public void limpiarCampos() {
//        vcotizacionInicio.getTxtCedulaClienteIngreso().setText("");
    }

    @Override
    public void keyReleased(KeyEvent ae) {
//        if (vcotizacionInicio.getTxtBuscarFactura() == ae.getSource()) {
//            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vcotizacionInicio.getTxtBuscarFactura().getText(), 2));
//        }

    }

    @Override
    public void keyTyped(KeyEvent ae) {

//        if (vcotizacionInicio.getTxtBuscarFactura() == ae.getSource()) {
//            trs = new TableRowSorter(modeloTablaFacturaIngreso);
//            vcotizacionInicio.getTbBuscarFactura().setRowSorter(trs);
//        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getClickCount() == 2 && !e.isConsumed() && vcotizacionInicio.getTablaUsuario()== e.getSource()) {
//            try {
//                int fila = vcotizacionInicio.getTablaUsuario().getSelectedRow();
//                if (fila >= 0) {
//                    vcotizacionInicio.getTxtCedula().setText(vcotizacionInicio.getTablaUsuario().getValueAt(fila,0).toString());
//                    vcotizacionInicio.getTxtNombres().setText(vcotizacionInicio.getTablaUsuario().getValueAt(fila,1).toString());
//                    vcotizacionInicio.getTxtApellido().setText(vcotizacionInicio.getTablaUsuario().getValueAt(fila,2).toString());
//                    vcotizacionInicio.getBtnGuardarUsuario().setEnabled(false);
//                    vcotizacionInicio.getBtnModificarUsuario().setEnabled(true);
//                    vcotizacionInicio.getTablaUsuario().setEnabled(false);
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
        if (this.vcotizacionInicio.getBtnGuardaCotizacion()== ae.getSource()) {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaIngreso = sdf.format(vcotizacionInicio.getJdCotizacionIncio().getDate());
            String fechaIngresoFin = sdf.format(vcotizacionInicio.getJdCotizacionFin().getDate());
            
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setFechaInicioCotizacion(fechaIngreso);
            cotizacion.setFechaFinCotizacion(fechaIngresoFin);
            contextCotizacionIngreso.RunAnadir(cotizacion, 1);

            vcotizacionInicio.dispose();

            VCotizacion vcotizacion = new VCotizacion();
            vcotizacion.setVisible(true);

            vprincipal.getPnlPrincipal().add(vcotizacion);
            vcotizacion.toFront();
            Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
            Dimension FrameSize = vcotizacion.getSize();
            vcotizacion.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

            try {
                vcotizacion.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            CCotizacionDetalle controladorCotizacion = new CCotizacionDetalle(contextCotizacionIngreso, vcotizacion, vprincipal, 1);
            if (controlVentana == false) {
                vcotizacion.getTxtClienteCotizacion().setText(vcotizacionInicio.getTxtNombreCotizacion().getText());
                vcotizacion.getTxtFechaFinCo().setText(fechaIngresoFin);
                vcotizacion.getTxtFechaInicioCo().setText(fechaIngreso);
                controladorCotizacion.iniciarVentana();
            }
        }
        if (this.vcotizacionInicio.getBtnModificarCotizacion()== ae.getSource()) {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaIngreso = sdf.format(vcotizacionInicio.getJdCotizacionIncio().getDate());
            String fechaIngresoFin = sdf.format(vcotizacionInicio.getJdCotizacionFin().getDate());
            
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setFechaInicioCotizacion(fechaIngreso);
            cotizacion.setFechaFinCotizacion(fechaIngresoFin);
            
            //contextCotizacionIngreso.RunAnadir(cotizacion, 1);
            String name = JOptionPane.showInputDialog("Nombre para la Cotización");
		
            vcotizacionInicio.dispose();

            VCotizacion vcotizacion = new VCotizacion();
            vcotizacion.setVisible(true);

            vprincipal.getPnlPrincipal().add(vcotizacion);
            vcotizacion.toFront();
            Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
            Dimension FrameSize = vcotizacion.getSize();
            vcotizacion.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

            try {
                vcotizacion.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            CCotizacionDetalle controladorCotizacion = new CCotizacionDetalle(contextCotizacionIngreso, vcotizacion, vprincipal, 2);
            if (controlVentana == false) {
                vcotizacion.getTxtClienteCotizacion().setText(name);
                vcotizacion.getTxtNumeroCotizacion().setText(vcotizacionInicio.getTxtCodigoCotizacionModificar().getText());
                
                controladorCotizacion.iniciarVentana();
            }
        }
        
    }
}
