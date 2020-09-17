/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Producto;
import facturacioncarniceria.vista.VPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JORDY
 */
public class ProductoDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public ProductoDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

    public void addProducto(Producto producto) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO producto (codigoproducto, medidaproducto, nombreproducto, precioproducto, stockproducto, ivaproducto, tipoproducto) values (?,?,?,?,?,?,?)");
            sentencia.setString(1, producto.getCodigo());       
            sentencia.setString(2, producto.getMedida());
            sentencia.setString(3, producto.getNomProducto());
            sentencia.setFloat(4, producto.getPrecio());
            sentencia.setFloat(5, producto.getExistente());
            sentencia.setString(6, producto.getTieneIva());
            sentencia.setString(7, producto.getTipoproducto());
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
       
    public void visualizeProducto(DefaultTableModel tableProvider) {
        String data[] = new String[7];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT *FROM producto order by codigoproducto asc;");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void visualizeFiltro(DefaultTableModel tableProvider, String filtroTipo) {
        String data[] = new String[7];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT * FROM producto WHERE tipoproducto = '"+filtroTipo+"'order by codigoproducto asc;");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
      
//     public void visualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro,JTextField txtcinco, String cedula) {
//        String dato1,dato2,dato3,dato4,dato5;
//        int numCodigoUsuario = Integer.parseInt(cedula);
//        Connection connect = null;
//        try {
//            connect = connectionBD.getConexion();
//            Statement st = connect.createStatement();
//            ResultSet sentencia2 = st.executeQuery("SELECT cedulausuario, contrausuario, nombreusuario, cargousuario, correousuario FROM usuario WHERE numusuario = '"+numCodigoUsuario+"'");
//            while (sentencia2.next()) {
//                dato1 = sentencia2.getString(1);
//                dato2 = sentencia2.getString(2);
//                dato3 = sentencia2.getString(3);
//                dato4 = sentencia2.getString(4);
//                dato5 = sentencia2.getString(5);
//             
//                txtuno.setText(dato1);
//                txtdos.setText(dato2);
//                txttres.setText(dato3);
//                txtcuatro.setText(dato4);
//                txtcinco.setText(dato5);
//            }
//            sentencia2.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
//        }
//    }
     public void modificarProducto(Producto producto, String numUsuario) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE producto SET medidaproducto=?, nombreproducto=?, precioproducto=?, stockproducto=?, ivaproducto=?, tipoproducto=? WHERE codigoproducto='"+numUsuario+"'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, producto.getMedida());
            estatus.setString(2, producto.getNomProducto());
            estatus.setFloat(3, producto.getPrecio());
            estatus.setFloat(4, producto.getExistente());
            estatus.setString(5, producto.getTieneIva());
            estatus.setString(6, producto.getTipoproducto());
            estatus.executeUpdate();
            estatus.close();
           
            JOptionPane.showMessageDialog(vMain, "Se ha modificado");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }
         
    }
     
    public void llenarCombo(JComboBox comboCorreo) {
        String data[] = new String[2];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT DISTINCT tipoproducto FROM producto");
            //comboCorreo.addItem("tecnico2@mastercontrol.com.ec");
            while (sentencia2.next()) {
               // data[0] = sentencia2.getString(1);
                comboCorreo.addItem(sentencia2.getString("tipoproducto"));
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
     
}
