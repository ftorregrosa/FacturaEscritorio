/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Proveedor;
import facturacioncarniceria.vista.VPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JORDY
 */
public class ProveedorDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public ProveedorDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

    public void addProveedor(Proveedor proveedor) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO proveedor(rucproveedor, nombreproveedor, direccionproveedor, telefonoproveedor)VALUES (?, ?, ?, ?);");
            sentencia.setString(1, proveedor.getRucProveedor());       
            sentencia.setString(2, proveedor.getNombreProveedor());
            sentencia.setString(3, proveedor.getDireccionProveedor());
            sentencia.setString(4, proveedor.getTelefonoProveedor());
            int i = sentencia.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(vMain, "Se Almacenó Correctamente");
            } else {
                JOptionPane.showMessageDialog(vMain, "No se pudo almacenar los Datos");
            }
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(vMain, "Error no se guardo"+ex);
        }
    }
    
    
    public void visualizeProveedor(DefaultTableModel tableProvider) {
        String data[] = new String[5];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT * FROM proveedor");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
      
     public void modificarProveedor(Proveedor proveedor, String numUsuario) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE proveedor SET nombreproveedor=?, direccionproveedor=?, telefonoproveedor=? WHERE rucproveedor='"+numUsuario+"'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, proveedor.getNombreProveedor());
            estatus.setString(2, proveedor.getDireccionProveedor());
            estatus.setString(3, proveedor.getTelefonoProveedor());
            estatus.executeUpdate();
            estatus.close();
           
            JOptionPane.showMessageDialog(vMain, "Se ha modificado");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico "+e);
        }
    }
}
