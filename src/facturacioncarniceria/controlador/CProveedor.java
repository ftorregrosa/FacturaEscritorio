/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;


import facturacioncarniceria.datos.Proveedor;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.ProveedorEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.ProveedorDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VProveedores;
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
public class CProveedor implements KeyListener, MouseListener, ActionListener {

    VProveedores vproveedor;
    Context contextProveedor;
    VPrincipal vprincipal;
    ProveedorDAO proveedorDAO;
    ProveedorEstrategia strategyProveedor = new ProveedorEstrategia();
    Conexion connectionBD;
    Validaciones validar =  new Validaciones();
    
    DefaultTableModel  modeloTablaProveedor = new DefaultTableModel() {
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

    public CProveedor(Context context, VProveedores vproveedor, VPrincipal vmain) {
        proveedorDAO = new ProveedorDAO();
        this.vproveedor = vproveedor;
        this.contextProveedor = context;
        this.vprincipal = vmain;
        this.contextProveedor = new Context(strategyProveedor);
        
        this.vproveedor.getBtnGuardarProveedor().addActionListener(this);
        this.vproveedor.getBtnActualizarProveedor().addActionListener(this);
       // this.vproveedor.getJmiModificar().addActionListener(this);
        this.vproveedor.getBtnModificarProveedor().addActionListener(this);
        this.vproveedor.getTxtBuscarProveedor().addKeyListener(this);
        this.vproveedor.getTablaProveedor().addMouseListener(this);
  

        this.connectionBD = new Conexion();

        modeloTablaProveedor.addColumn("RUC");
        modeloTablaProveedor.addColumn("RAZON SOCIAL");
        modeloTablaProveedor.addColumn("DIRECCION");
        modeloTablaProveedor.addColumn("TELEFONO");
        vproveedor.getTablaProveedor().setModel(modeloTablaProveedor);        
    }
    
    public void validarCampos()
    {
        validar.validarLetras(vproveedor.getTxtNombreProveedor());
        validar.validarNumeros(vproveedor.getTxtRuc());  
        validar.validarNumeros(vproveedor.getTxtTelefonoProveedor());
        validar.limitarCaracteres(vproveedor.getTxtRuc(), 13);
    }
    
    public void iniciarVentana() {
        vproveedor.show();
        validarCampos();
        vproveedor.getTablaProveedor().getColumnModel().getColumn(0).setPreferredWidth(50);
        vproveedor.getTablaProveedor().getColumnModel().getColumn(1).setPreferredWidth(200);
        vproveedor.getTablaProveedor().getColumnModel().getColumn(2).setPreferredWidth(300);
        vproveedor.getTablaProveedor().getColumnModel().getColumn(3).setPreferredWidth(100);


        contextProveedor.RunVisualizar(modeloTablaProveedor, 1);
        

        vproveedor.getTxtNombreProveedor().setDocument(new Validaciones());
        vproveedor.getTxtBuscarProveedor().setDocument(new Validaciones());
        

        
        vproveedor.getBtnModificarProveedor().setEnabled(false);
        
        vprincipal.getLblCedula().getText();
       // contextProveedor.RunVisualizeContra(vmodificarcontra.getTxtCedula(),vmodificarcontra.getTxtContra(),vmodificarcontra.getTxtNombres(),vmodificarcontra.getTxtCargo(),vmain.getLblcedula().getText());
    }

    public void cleanTable() {
        for (int i = 0; i < vproveedor.getTablaProveedor().getRowCount(); i++) {
            modeloTablaProveedor.removeRow(i);
            i -= 1;
        }
    }
    
    public void limpiarCampos()
    {
        vproveedor.getTxtRuc().setText("");
        vproveedor.getTxtNombreProveedor().setText("");
        vproveedor.getTxtDireccionProveedor().setText("");
        vproveedor.getTxtTelefonoProveedor().setText("");
    }
    
    @Override
    public void keyReleased(KeyEvent ae) {
        if (vproveedor.getTxtBuscarProveedor()== ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)"+vproveedor.getTxtBuscarProveedor().getText(), 1));
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ae) {    
        
        if (vproveedor.getTxtBuscarProveedor()== ae.getSource()) {
           trs = new TableRowSorter(modeloTablaProveedor);
           vproveedor.getTablaProveedor().setRowSorter(trs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed() && vproveedor.getTablaProveedor()== e.getSource()) {
            try {
                int fila = vproveedor.getTablaProveedor().getSelectedRow();
                if (fila >= 0) {
                    vproveedor.getTxtRuc().setText(vproveedor.getTablaProveedor().getValueAt(fila,0).toString());
                    vproveedor.getTxtNombreProveedor().setText(vproveedor.getTablaProveedor().getValueAt(fila,1).toString());
                    vproveedor.getTxtDireccionProveedor().setText(vproveedor.getTablaProveedor().getValueAt(fila,2).toString());
                    vproveedor.getTxtTelefonoProveedor().setText(vproveedor.getTablaProveedor().getValueAt(fila,3).toString());
                    vproveedor.getBtnGuardarProveedor().setEnabled(false);
                    vproveedor.getBtnModificarProveedor().setEnabled(true);
                    vproveedor.getTablaProveedor().setEnabled(false);
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
        if (this.vproveedor.getBtnGuardarProveedor()== ae.getSource()) {
            
            try {
                Proveedor proveedor = new Proveedor();
                if (vproveedor.getTxtNombreProveedor().getText().equals("") || vproveedor.getTxtRuc().getText().equals("") || vproveedor.getTxtDireccionProveedor().getText().equals("") || vproveedor.getTxtTelefonoProveedor().getText().equals("") ) {
                    JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
                
                }else{
                    proveedor.setRucProveedor(vproveedor.getTxtRuc().getText());
                    proveedor.setNombreProveedor(vproveedor.getTxtNombreProveedor().getText());
                    proveedor.setDireccionProveedor(vproveedor.getTxtDireccionProveedor().getText());
                    proveedor.setTelefonoProveedor(vproveedor.getTxtTelefonoProveedor().getText());
                    contextProveedor.RunAnadir(proveedor, 1);
                    limpiarCampos();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "Llene todos lo campos antes de guardar");
            }

        } else if (vproveedor.getBtnActualizarProveedor()== ae.getSource()) {
            cleanTable();
            contextProveedor.RunVisualizar(modeloTablaProveedor, 1);

        } else if (vproveedor.getBtnModificarProveedor()== ae.getSource()) {
            Proveedor proveedor = new Proveedor();
            if(vproveedor.getTxtNombreProveedor().getText().equals("")){               
                JOptionPane.showMessageDialog(vprincipal,"Ingrese un nombre");
            }else{
                proveedor.setRucProveedor(vproveedor.getTxtRuc().getText());
                proveedor.setNombreProveedor(vproveedor.getTxtNombreProveedor().getText());
                proveedor.setDireccionProveedor(vproveedor.getTxtDireccionProveedor().getText());
                proveedor.setTelefonoProveedor(vproveedor.getTxtTelefonoProveedor().getText());
                int fila = vproveedor.getTablaProveedor().getSelectedRow();
                if (fila >= 0) {
                    contextProveedor.RunModificar(proveedor, vproveedor.getTablaProveedor().getValueAt(fila, 0).toString());
                    vproveedor.getTablaProveedor().setEnabled(true);
                    limpiarCampos();
                    vproveedor.getBtnGuardarProveedor().setEnabled(true);
                    vproveedor.getBtnModificarProveedor().setEnabled(false);
                }
            }
        }

    }
}
