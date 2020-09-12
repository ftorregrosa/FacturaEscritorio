/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import facturacioncarniceria.datos.Cliente;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.ClienteEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.ClienteDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VClientes;
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
public class CCliente implements KeyListener, MouseListener, ActionListener {

    VClientes vcliente;
    Context contextCliente;
    VPrincipal vprincipal;
    ClienteDAO clienteDAO;
    ClienteEstrategia strategyCliente = new ClienteEstrategia();
    Conexion connectionBD;
    Validaciones validar =  new Validaciones();
    
    DefaultTableModel  modeloTablaCliente = new DefaultTableModel();

    
    TableRowSorter trs;

    public CCliente(Context context, VClientes vcliente, VPrincipal vmain) {
        clienteDAO = new ClienteDAO();
        this.vcliente = vcliente;
        this.contextCliente = context;
        this.vprincipal = vmain;
        this.contextCliente = new Context(strategyCliente);
        this.vcliente.getBtnGuardarUsuario().addActionListener(this);
        this.vcliente.getBtnActualizarUsuario().addActionListener(this);
        this.vcliente.getTxtBuscarClientes().addKeyListener(this);
        this.vcliente.getTablaClientes().addMouseListener(this);
        this.connectionBD = new Conexion();
        modeloTablaCliente.addColumn("Cedula");
        modeloTablaCliente.addColumn("Nombre");
        modeloTablaCliente.addColumn("Apellido");
        modeloTablaCliente.addColumn("Dirección");
        modeloTablaCliente.addColumn("Telefono");
        modeloTablaCliente.addColumn("Correo");
        vcliente.getTablaClientes().setModel(modeloTablaCliente);        
    }
    
    public void validarCampos()
    {
        validar.validarLetras(vcliente.getTxtNombreCliente());
        validar.validarNumeros(vcliente.getTxtCedulaCliente());
        validar.validarNumeros(vcliente.getTxtTelefonoCliente());
        validar.validarLetras(vcliente.getTxtBuscarClientes());
        
        validar.limitarCaracteres(vcliente.getTxtCedulaCliente(), 13);
        validar.limitarCaracteres(vcliente.getTxtTelefonoCliente(), 10);
    }
    
    public void iniciarVentana() {
        vcliente.show();
        validarCampos();
        vcliente.getTablaClientes().getColumnModel().getColumn(0).setPreferredWidth(50);
        vcliente.getTablaClientes().getColumnModel().getColumn(1).setPreferredWidth(100);
        vcliente.getTablaClientes().getColumnModel().getColumn(2).setPreferredWidth(100);
        vcliente.getTablaClientes().getColumnModel().getColumn(3).setPreferredWidth(100);
        vcliente.getTablaClientes().getColumnModel().getColumn(4).setPreferredWidth(100);
        vcliente.getTablaClientes().getColumnModel().getColumn(5).setPreferredWidth(100);
        contextCliente.RunVisualizar(modeloTablaCliente, 1);
        vcliente.getTxtApellidoCliente().setDocument(new Validaciones());
        vcliente.getTxtNombreCliente().setDocument(new Validaciones());
        vcliente.getTxtDireccionCliente().setDocument(new Validaciones());
        vcliente.getTxtBuscarClientes().setDocument(new Validaciones());

        vprincipal.getLblCedula().getText();
    }

    public void cleanTable() {
        for (int i = 0; i < vcliente.getTablaClientes().getRowCount(); i++) {
            modeloTablaCliente.removeRow(i);
            i -= 1;
        }
    }
    
    public void limpiarCampos()
    {
        vcliente.getTxtApellidoCliente().setText("");
        vcliente.getTxtCedulaCliente().setText("");
        vcliente.getTxtNombreCliente().setText("");
        vcliente.getTxtTelefonoCliente().setText("");
        vcliente.getTxtDireccionCliente().setText("");
        vcliente.getTxtCorreoCliente().setText("");
    }
    
    @Override
    public void keyReleased(KeyEvent ae) {
        if (vcliente.getTxtBuscarClientes()== ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)"+vcliente.getTxtBuscarClientes().getText(), 2));
        }
    }
    @Override
    public void keyTyped(KeyEvent ae) {     
        if (vcliente.getTxtBuscarClientes()== ae.getSource()) {
           trs = new TableRowSorter(modeloTablaCliente);
           vcliente.getTablaClientes().setRowSorter(trs);
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
        if (this.vcliente.getBtnGuardarUsuario() == ae.getSource()) {
            try {
                Cliente cliente = new Cliente();
                if (vcliente.getTxtCedulaCliente().getText().equals("") || vcliente.getTxtTelefonoCliente().getText().equals("") || vcliente.getTxtNombreCliente().getText().equals("")|| vcliente.getTxtApellidoCliente().getText().equals("")|| vcliente.getTxtDireccionCliente().getText().equals("")|| vcliente.getTxtCorreoCliente().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
                
                }else{
                    cliente.setCedulaCliente(vcliente.getTxtCedulaCliente().getText());
                    cliente.setNombreCliente(vcliente.getTxtNombreCliente().getText());
                    cliente.setApellidoCliente(vcliente.getTxtApellidoCliente().getText());
                    cliente.setDireccionCliente(vcliente.getTxtDireccionCliente().getText());
                    cliente.setTelefonoCliente(vcliente.getTxtTelefonoCliente().getText());
                    cliente.setEmailCliente(vcliente.getTxtCorreoCliente().getText());
                    contextCliente.RunAnadir(cliente, 1);
                    limpiarCampos();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } else if (vcliente.getBtnActualizarUsuario()== ae.getSource()) {
            cleanTable();
            contextCliente.RunVisualizar(modeloTablaCliente, 1);
        }

    }
}
