/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controlador;

import facturacion.datos.Cliente;
import facturacion.servicio.Context;
import facturacion.modelo.Conexion;
import facturacion.modelo.ClienteDAO;
import facturacion.servicio.Validaciones;
import facturacion.servicio.ClienteServicio;
import facturacion.vista.VPrincipal;
import facturacion.vista.VClientes;
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
    VPrincipal vprincipal;
    Validaciones validar = new Validaciones();
    ClienteServicio clienteServicio;

    DefaultTableModel modeloTablaCliente = new DefaultTableModel();

    TableRowSorter trs;

    public CCliente(Context context, VClientes vcliente, VPrincipal vmain) {
        clienteServicio=context.getClienteServicio();
        this.vcliente = vcliente;
        this.vprincipal = vmain;
        this.vcliente.getBtnGuardarUsuario().addActionListener(this);
        this.vcliente.getBtnActualizarUsuario().addActionListener(this);
        this.vcliente.getTxtBuscarClientes().addKeyListener(this);
        this.vcliente.getTablaClientes().addMouseListener(this);
        modeloTablaCliente.addColumn("Código");
        modeloTablaCliente.addColumn("CIF");
        modeloTablaCliente.addColumn("Nombre / Razón Social");
        modeloTablaCliente.addColumn("Dirección");
        modeloTablaCliente.addColumn("Teléfono");
        modeloTablaCliente.addColumn("C.P.");
        modeloTablaCliente.addColumn("Población");
        modeloTablaCliente.addColumn("Provincia");
        vcliente.getTablaClientes().setModel(modeloTablaCliente);
    }

    public void validarCampos() {
        validar.validarLetras(vcliente.getTxtNombreCliente());
        validar.validarNumeros(vcliente.getTxtTelefonoCliente());
        validar.validarLetras(vcliente.getTxtBuscarClientes());

        validar.limitarCaracteres(vcliente.getTxtTelefonoCliente(), 10);
    }

    public void iniciarVentana() {
        vcliente.show();
        validarCampos();
        
        vcliente.getTablaClientes().getColumnModel().getColumn(0).setMaxWidth(50);
        vcliente.getTablaClientes().getColumnModel().getColumn(1).setMinWidth(100);
        vcliente.getTablaClientes().getColumnModel().getColumn(2).setPreferredWidth(300);
        vcliente.getTablaClientes().getColumnModel().getColumn(3).setPreferredWidth(350);
        vcliente.getTablaClientes().getColumnModel().getColumn(4).setMaxWidth(100);
        vcliente.getTablaClientes().getColumnModel().getColumn(5).setMaxWidth(50);
        vcliente.getTablaClientes().getColumnModel().getColumn(6).setPreferredWidth(200);
        vcliente.getTablaClientes().getColumnModel().getColumn(7).setPreferredWidth(100);
        clienteServicio.getClienteDAO().visualize(modeloTablaCliente);
        vcliente.getTxtCifCliente().setDocument(new Validaciones());
        vcliente.getTxtNombreCliente().setDocument(new Validaciones());
        vcliente.getTxtDireccionCliente().setDocument(new Validaciones());
        vcliente.getTxtTelefonoCliente().setDocument(new Validaciones());
        vcliente.getTxtPoblacionCliente().setDocument(new Validaciones());
        vcliente.getTxtProvinciaCliente().setDocument(new Validaciones());
        vcliente.getTxtCPCliente().setDocument(new Validaciones());
        vcliente.getTxtBuscarClientes().setDocument(new Validaciones());

        vprincipal.getLblCedula().getText();
    }

    public void cleanTable() {
        for (int i = 0; i < vcliente.getTablaClientes().getRowCount(); i++) {
            modeloTablaCliente.removeRow(i);
            i -= 1;
        }
    }

    public void limpiarCampos() {
        vcliente.getTxtCifCliente().setText("");
        vcliente.getTxtNombreCliente().setText("");
        vcliente.getTxtDireccionCliente().setText("");
        vcliente.getTxtTelefonoCliente().setText("");
        vcliente.getTxtPoblacionCliente().setText("");
        vcliente.getTxtProvinciaCliente().setText("");
        vcliente.getTxtCPCliente().setText("");

    }

    @Override
    public void keyReleased(KeyEvent ae) {
        if (vcliente.getTxtBuscarClientes() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vcliente.getTxtBuscarClientes().getText()));
        }
    }

    @Override
    public void keyTyped(KeyEvent ae) {
        if (vcliente.getTxtBuscarClientes() == ae.getSource()) {
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
                if (vcliente.getTxtCifCliente().getText().equals("")
                        || vcliente.getTxtNombreCliente().getText().equals("")
                        || vcliente.getTxtDireccionCliente().getText().equals("")
                        || vcliente.getTxtPoblacionCliente().getText().equals("")
                        || vcliente.getTxtProvinciaCliente().getText().equals("")
                        || vcliente.getTxtCPCliente().getText().equals("")) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");

                } else {
                    cliente.setCif(vcliente.getTxtCifCliente().getText());
                    cliente.setRazonSocial(vcliente.getTxtNombreCliente().getText());
                    cliente.setDireccion(vcliente.getTxtDireccionCliente().getText());
                    cliente.setTelefono(vcliente.getTxtTelefonoCliente().getText());
                    cliente.setPoblacion(vcliente.getTxtPoblacionCliente().getText());
                    cliente.setProvincia(vcliente.getTxtProvinciaCliente().getText());
                    cliente.setCodigoPostal(vcliente.getTxtCPCliente().getText());
                    clienteServicio.getClienteDAO().addCliente(cliente);
                    limpiarCampos();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } else if (vcliente.getBtnActualizarUsuario() == ae.getSource()) {
            cleanTable();
            clienteServicio.getClienteDAO().visualize(modeloTablaCliente);
        }

    }
}
