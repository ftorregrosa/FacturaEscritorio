package facturacion.controlador;

import java.awt.Dimension;
import facturacion.modelo.Conexion;
import facturacion.servicio.Context;
import facturacion.vista.VLoginMain;
import facturacion.vista.VPrincipal;
import facturacion.vista.VFacturaIngreso;
import facturacion.vista.VClientes;
import facturacion.vista.VProducto;
import facturacion.vista.VReportes;
import facturacion.servicio.Validaciones;
import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rojeru_san.RSPanelsSlider;

public class CMain implements MouseListener, ActionListener {

    private Context contexto;
    private VLoginMain vlogin;
    private VPrincipal vprincipal;

    public static boolean controlVentanaUsuario = false;

    Conexion conexionBD;

    Validaciones validar = new Validaciones();

    public CMain(Context contexto, VLoginMain vlogin) {
        this.contexto = contexto;
        vprincipal = new VPrincipal();
        this.vlogin = vlogin;
        this.conexionBD = new Conexion();
        //Validaciones
        validar.validarNumeros(this.vlogin.getTxtCedulaUsuario());
        validar.limitarCaracteres(this.vlogin.getTxtCedulaUsuario(), 10);

        //botones del login        
        vlogin.getBtnAccept().addActionListener(this);
        vlogin.getRootPane().setDefaultButton(vlogin.getBtnAccept());

        //labls de principal
        this.vprincipal.getCliente().addMouseListener(this);
        this.vprincipal.getFactura().addMouseListener(this);
        this.vprincipal.getInventariio().addMouseListener(this);
        this.vprincipal.getInformes().addMouseListener(this);

        //vprincipal.setExtendedState(MAXIMIZED_BOTH);
    }

    public void iniciar() {
        vlogin.setVisible(true);
        vlogin.getrSPanelsSlider1().setPanelSlider(1, vlogin.getPnlCargando(), RSPanelsSlider.DIRECT.RIGHT);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(2000);
                    vlogin.dispose();
                    iniciarVPrincipal();
                } catch (InterruptedException ex) {
                    Logger.getLogger(VLoginMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    public void iniciarVLogin() {
        //modificar aki INGRESOOOOOOOOOO
        //iniciarVPrincipal();
        vlogin.setVisible(true);
    }

    public void cerrarVLogin() {
        vlogin.dispose();

    }

    public void iniciarVPrincipal() {
//        int width = vprincipal.getWidth();
//        int Height = vprincipal.getHeight();
//        vprincipal.setSize(width, Height);
        vprincipal.setExtendedState((int) Frame.CENTER_ALIGNMENT);

        vprincipal.setVisible(true);
//        this.vprincipal.setMinimumSize(new Dimension(600, 600));
//        this.vprincipal.setMaximumSize(new Dimension(600, 600));

    }

    public void cerrarVPrincipal() {
        vprincipal.dispose();
    }

    public int validarIngresoPersonal() {
        String datos[] = new String[4];
        String usuario = vlogin.getTxtCedulaUsuario().getText();
        String passw = String.valueOf(vlogin.getTxtContraseña().getPassword());
        Connection con = null;
        int resultado = 0;
        try {

            con = conexionBD.getConexion();
            Statement st = con.createStatement();
            ResultSet per = st.executeQuery("SELECT cedulausuario, nombreusuario || ' ' ||apellidousuario  FROM usuario WHERE cedulausuario='" + usuario + "' AND contrausuario=('" + passw + "')");
            if (per.next()) {
                datos[0] = per.getString(1);
                datos[1] = per.getString(2);
                vprincipal.getLblCedula().setText(datos[0]);
                vprincipal.getLblNombreUsuario().setText(datos[1]);
                resultado = 1;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);
            }
        }
        vprincipal.getLblCedula().setText("fran");
        vprincipal.getLblNombreUsuario().setText("Fran");
        resultado = 1;
        return resultado;
    }

    //Métodos de administrador (ingreso de usuarios) 
    @Override
    public void mouseClicked(MouseEvent me) {

        if (this.vprincipal.getInformes().isEnabled()) {
            if (this.vprincipal.getInformes() == me.getSource()) {
//                this.vprincipal.getLblReportes().setSelected(true);
//                this.vprincipal.getLblUsuario().setSelected(false);
//                this.vprincipal.getLblModificarUsuario().setSelected(false);
//                this.vprincipal.getLblProyecto().setSelected(false);
//                this.vprincipal.getLblCotizaciones().setSelected(false);
//                this.vprincipal.getLblPedidos().setSelected(false);
//                this.vprincipal.getLblInventario().setSelected(false);
//                this.vprincipal.getLblCompras().setSelected(false);
//                this.vprincipal.getLblDevoluciones().setSelected(false);
//                this.vprincipal.getLblProveedor().setSelected(false);
//                this.vprincipal.getLblCostos().setSelected(false);
//                this.vprincipal.getLblKardex().setSelected(false);
//
                VReportes vreportes = new VReportes();
                vreportes.setVisible(true);
                this.vprincipal.getPnlPrincipal().add(vreportes);
                vreportes.toFront();
//
                Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
                Dimension FrameSize = vreportes.getSize();
                vreportes.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

                try {
                    vreportes.setMaximum(false);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
                }

                CReportes controladorReportes = new CReportes(contexto, vreportes, vprincipal);
                if (controlVentanaUsuario == false) {
                    controladorReportes.iniciarVentana();
                }
            }
        }

        if (this.vprincipal.getCliente().isEnabled()) {
            if (this.vprincipal.getCliente() == me.getSource()) {
//                this.vprincipal.getLblReportes().setSelected(true);
//                this.vprincipal.getLblUsuario().setSelected(false);
//                this.vprincipal.getLblModificarUsuario().setSelected(false);
//                this.vprincipal.getLblProyecto().setSelected(false);
//                this.vprincipal.getLblCotizaciones().setSelected(false);
//                this.vprincipal.getLblPedidos().setSelected(false);
//                this.vprincipal.getLblInventario().setSelected(false);
//                this.vprincipal.getLblCompras().setSelected(false);
//                this.vprincipal.getLblDevoluciones().setSelected(false);
//                this.vprincipal.getLblProveedor().setSelected(false);
//                this.vprincipal.getLblCostos().setSelected(false);
//                this.vprincipal.getLblKardex().setSelected(false);
//
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
                    controladorCliente.iniciarVentana();
                }
            }
        }

        if (this.vprincipal.getInventariio().isEnabled()) {
            if (this.vprincipal.getInventariio() == me.getSource()) {
                VProducto vproducto = new VProducto();
                vproducto.setVisible(true);
                this.vprincipal.getPnlPrincipal().add(vproducto);
                vproducto.toFront();
//
                Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
                Dimension FrameSize = vproducto.getSize();
                vproducto.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

                try {
                    vproducto.setMaximum(false);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
                }

                CProducto controladorProducto = new CProducto(contexto,vproducto, vprincipal);
                if (controlVentanaUsuario == false) {
                    controladorProducto.iniciarVentana();
                }
            }
        }
        if (this.vprincipal.getFactura().isEnabled()) {
            if (this.vprincipal.getFactura() == me.getSource()) {
//                this.vprincipal.getLblReportes().setSelected(true);
//                this.vprincipal.getLblUsuario().setSelected(false);
//                this.vprincipal.getLblModificarUsuario().setSelected(false);
//                this.vprincipal.getLblProyecto().setSelected(false);
//                this.vprincipal.getLblCotizaciones().setSelected(false);
//                this.vprincipal.getLblPedidos().setSelected(false);
//                this.vprincipal.getLblInventario().setSelected(false);
//                this.vprincipal.getLblCompras().setSelected(false);
//                this.vprincipal.getLblDevoluciones().setSelected(false);
//                this.vprincipal.getLblProveedor().setSelected(false);
//                this.vprincipal.getLblCostos().setSelected(false);
//                this.vprincipal.getLblKardex().setSelected(false);
//
                VFacturaIngreso vfacturaIngreso = new VFacturaIngreso();
                vfacturaIngreso.setVisible(true);
                this.vprincipal.getPnlPrincipal().add(vfacturaIngreso);
                vfacturaIngreso.toFront();
//
                Dimension desktopSize = vprincipal.getPnlPrincipal().getSize();
                Dimension FrameSize = vfacturaIngreso.getSize();
                vfacturaIngreso.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

                try {
                    vfacturaIngreso.setMaximum(false);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
                }

                CFacturaIngreso controladorCliente = new CFacturaIngreso(contexto, vfacturaIngreso, vprincipal);
                if (controlVentanaUsuario == false) {
                    controladorCliente.iniciarVentana();
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (vlogin.getBtnAccept() == ae.getSource()) {
            if (validarIngresoPersonal() == 1) {
                vlogin.getrSPanelsSlider1().setPanelSlider(1, vlogin.getPnlCargando(), RSPanelsSlider.DIRECT.RIGHT);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Thread.sleep(2000);
                            vlogin.dispose();
                            iniciarVPrincipal();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VLoginMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();

            } else {
                vlogin.getLblMensaje().setText("¡Usuario y/o contraseña incorrectos!");
            }

        }
//        if (this.vprincipal.getLblReportes().isSelected()) {
//            this.vprincipal.getLblReportes().setColorNormal(new Color(204, 204, 204));
//            this.vprincipal.getLblReportes().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblReportes().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblUsuario().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblUsuario().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblUsuario().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblModificarUsuario().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblModificarUsuario().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblModificarUsuario().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblProyecto().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblProyecto().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblProyecto().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblCotizaciones().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblCotizaciones().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblCotizaciones().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblPedidos().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblPedidos().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblPedidos().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblInventario().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblInventario().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblInventario().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblCompras().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblCompras().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblCompras().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblDevoluciones().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblDevoluciones().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblDevoluciones().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblProveedor().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblProveedor().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblProveedor().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblProveedor().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblProveedor().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblProveedor().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblCostos().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblCostos().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblCostos().setColorPressed(new Color(204, 204, 204));
//
//            this.vprincipal.getLblKardex().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblKardex().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblKardex().setColorPressed(new Color(204, 204, 204));
//
//        } else {
//            this.vprincipal.getLblReportes().setColorNormal(new Color(239, 238, 244));
//            this.vprincipal.getLblReportes().setColorHover(new Color(204, 204, 204));
//            this.vprincipal.getLblReportes().setColorPressed(new Color(204, 204, 204));
//        }

    }
}
