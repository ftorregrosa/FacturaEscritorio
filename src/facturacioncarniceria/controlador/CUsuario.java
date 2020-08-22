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
import facturacioncarniceria.vista.VUsuario;
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
public class CUsuario implements KeyListener, MouseListener, ActionListener {

    VUsuario vusuario;
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

    public CUsuario(Context context, VUsuario vusuario, VPrincipal vmain) {
        usuarioDAO = new UsuarioDAO();
        this.vusuario = vusuario;
        this.contextUsuario = context;
        this.vprincipal = vmain;
        this.contextUsuario = new Context(strategy);
        
        this.vusuario.getBtnGuardarUsuario().addActionListener(this);
        this.vusuario.getBtnActualizarUsuario().addActionListener(this);
       // this.vusuario.getJmiModificar().addActionListener(this);
        this.vusuario.getBtnModificarUsuario().addActionListener(this);
        this.vusuario.getTxtBuscar().addKeyListener(this);
        this.vusuario.getTablaUsuario().addMouseListener(this);
  

        this.connectionBD = new Conexion();

        modeloTablaUsuario.addColumn("Cedula");
        modeloTablaUsuario.addColumn("Nombre");
        modeloTablaUsuario.addColumn("Apellido");
        vusuario.getTablaUsuario().setModel(modeloTablaUsuario);        
    }
    
    public void validarCampos()
    {
        validar.validarLetras(vusuario.getTxtNombres());
        validar.validarNumeros(vusuario.getTxtCedula());
        validar.validarLetras(vusuario.getTxtBuscar());
        
        validar.limitarCaracteres(vusuario.getTxtCedula(), 10);
    }
    
    public void iniciarVentana() {
        vusuario.show();
        validarCampos();
        vusuario.getTablaUsuario().getColumnModel().getColumn(0).setPreferredWidth(100);
        vusuario.getTablaUsuario().getColumnModel().getColumn(1).setPreferredWidth(200);
        vusuario.getTablaUsuario().getColumnModel().getColumn(2).setPreferredWidth(200);

        contextUsuario.RunVisualizar(modeloTablaUsuario, 1);
        

        vusuario.getTxtApellido().setDocument(new Validaciones());
        vusuario.getTxtNombres().setDocument(new Validaciones());
        vusuario.getTxtBuscar().setDocument(new Validaciones());

        
        vusuario.getBtnModificarUsuario().setEnabled(false);
        
        vprincipal.getLblCedula().getText();
       // contextUsuario.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
    }

    public void cleanTable() {
        for (int i = 0; i < vusuario.getTablaUsuario().getRowCount(); i++) {
            modeloTablaUsuario.removeRow(i);
            i -= 1;
        }
    }
    
    public void limpiarCampos()
    {
        vusuario.getTxtContra().setText("");
        vusuario.getTxtCedula().setText("");
        vusuario.getTxtNombres().setText("");
        vusuario.getTxtApellido().setText("");
    }
    
    @Override
    public void keyReleased(KeyEvent ae) {
        if (vusuario.getTxtBuscar() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)"+vusuario.getTxtBuscar().getText(), 2));
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ae) {    
        
        if (vusuario.getTxtBuscar() == ae.getSource()) {
           trs = new TableRowSorter(modeloTablaUsuario);
           vusuario.getTablaUsuario().setRowSorter(trs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed() && vusuario.getTablaUsuario()== e.getSource()) {
            try {
                int fila = vusuario.getTablaUsuario().getSelectedRow();
                if (fila >= 0) {
                    vusuario.getTxtCedula().setText(vusuario.getTablaUsuario().getValueAt(fila,0).toString());
                    vusuario.getTxtNombres().setText(vusuario.getTablaUsuario().getValueAt(fila,1).toString());
                    vusuario.getTxtApellido().setText(vusuario.getTablaUsuario().getValueAt(fila,2).toString());
                    vusuario.getBtnGuardarUsuario().setEnabled(false);
                    vusuario.getBtnModificarUsuario().setEnabled(true);
                    vusuario.getTablaUsuario().setEnabled(false);
                }
            }catch (Exception ex) {
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
        if (this.vusuario.getBtnGuardarUsuario() == ae.getSource()) {
            try {
                Usuario usuario = new Usuario();
                if (vusuario.getTxtCedula().getText().equals("") || vusuario.getTxtContra().getText().equals("") || vusuario.getTxtNombres().getText().equals("")|| vusuario.getTxtApellido().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
                
                }else{
                    usuario.setCedulaUsuario(vusuario.getTxtCedula().getText());
                    usuario.setNombreUsuario(vusuario.getTxtNombres().getText());
                    usuario.setApellidoUsuario(vusuario.getTxtApellido().getText());
                    usuario.setContrasenaUsuario(vusuario.getTxtContra().getText());
                    contextUsuario.RunAnadir(usuario, 1);
                    limpiarCampos();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } else if (vusuario.getBtnActualizarUsuario()== ae.getSource()) {
            cleanTable();
            contextUsuario.RunVisualizar(modeloTablaUsuario, 1);

        } else if (vusuario.getBtnModificarUsuario()== ae.getSource()) {
            Usuario usuario = new Usuario();
            if(vusuario.getTxtContra().getText().equals("")){               
                JOptionPane.showMessageDialog(vprincipal,"Ingrese una contaseña");
            }else{
                usuario.setCedulaUsuario(vusuario.getTxtCedula().getText());
                usuario.setNombreUsuario(vusuario.getTxtNombres().getText());
                usuario.setApellidoUsuario(vusuario.getTxtApellido().getText());
                usuario.setContrasenaUsuario(vusuario.getTxtContra().getText()); 
                int fila = vusuario.getTablaUsuario().getSelectedRow();
                if (fila >= 0) {
                    contextUsuario.RunModificar(usuario, vusuario.getTablaUsuario().getValueAt(fila, 0).toString());
                    vusuario.getTablaUsuario().setEnabled(true);
                    limpiarCampos();
                    vusuario.getBtnGuardarUsuario().setEnabled(true);
                    vusuario.getBtnModificarUsuario().setEnabled(false);
                }
            }
        }

    }
}
