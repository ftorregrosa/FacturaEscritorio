/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;


import facturacioncarniceria.datos.Usuario;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.UsuarioEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.UsuarioDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VModificaContrasena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;

/**
 *
 * @author JORDY
 */
public class CUsuarioModificarContraseña implements KeyListener, MouseListener, ActionListener {

    VModificaContrasena vmodificarUsuario;
    Context contextUsuario;
    VPrincipal vprincipal;
    UsuarioDAO usuarioDAO;
    UsuarioEstrategia strategy = new UsuarioEstrategia();
    Conexion connectionBD;
    Validaciones validar =  new Validaciones();
    
    DefaultTableModel  modeloTablaUsuario = new DefaultTableModel() {
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

    public CUsuarioModificarContraseña(Context context, VModificaContrasena vmodificarUsuario, VPrincipal vmain) {
        usuarioDAO = new UsuarioDAO();
        this.vmodificarUsuario = vmodificarUsuario;
        this.contextUsuario = context;
        this.vprincipal = vmain;
        this.contextUsuario = new Context(strategy);
        
        this.vmodificarUsuario.getBtnModificarContrasenaUsuario().addActionListener(this);
  
        this.connectionBD = new Conexion();      
    }
    
    public void iniciarVentana() {
        vmodificarUsuario.show();

        contextUsuario.RunvisualizeUsuarioContra(vmodificarUsuario.getTxtCedulaUsuario(), vmodificarUsuario.getTxtNombresUsuario(), vmodificarUsuario.getTxtContraUsuario(),vprincipal.getLblCedula().getText());
        
        vprincipal.getLblCedula().getText();
       // contextUsuario.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
    }
    
    @Override
    public void keyReleased(KeyEvent ae) {
     
        
    }

    @Override
    public void keyTyped(KeyEvent ae) {    

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
        if (this.vmodificarUsuario.getBtnModificarContrasenaUsuario()== ae.getSource()) {
            try {
                if (vmodificarUsuario.getTxtContraUsuario().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Ingrese una Contrasena");
                
                }else{
                    contextUsuario.RunModificarContrasena(vmodificarUsuario.getTxtContraUsuario().getText(), vmodificarUsuario.getTxtCedulaUsuario().getText());
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } 

    }
}
