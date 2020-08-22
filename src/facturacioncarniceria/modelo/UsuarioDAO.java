/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Usuario;
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
public class UsuarioDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public UsuarioDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

    public void addUsuario(Usuario provider) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO usuario (cedulausuario, nombreusuario, apellidousuario, contrausuario) values (?,?,?,?)");
            sentencia.setString(1, provider.getCedulaUsuario());       
            sentencia.setString(2, provider.getNombreUsuario());
            sentencia.setString(3, provider.getApellidoUsuario());
            sentencia.setString(4, provider.getContrasenaUsuario());
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
    
    
    public void visualizeUsuario(DefaultTableModel tableProvider) {
        String data[] = new String[6];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT cedulausuario, nombreusuario, apellidousuario FROM usuario");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
      
     public void visualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, String cedula) {
        String dato1,dato2,dato3;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT cedulausuario, nombreusuario || ' ' || apellidousuario, contrausuario FROM usuario WHERE cedulausuario = '"+cedula+"'");
            while (sentencia2.next()) {
                dato1 = sentencia2.getString(1);
                dato2 = sentencia2.getString(2);
                dato3 = sentencia2.getString(3);
             
                txtuno.setText(dato1);
                txtdos.setText(dato2);
                txttres.setText(dato3);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
//     
//     public void visualizeCorreoElectronico(JTextField txtuno, String cedula) {
//        int numCodigoUsuario = Integer.parseInt(cedula);
//        String dato1;
//        Connection connect = null;
//        try {
//            connect = connectionBD.getConexion();
//            Statement st = connect.createStatement();
//            ResultSet sentencia2 = st.executeQuery("SELECT correousuario FROM usuario WHERE numusuario = '"+numCodigoUsuario+"'");
//            while (sentencia2.next()) {
//                dato1 = sentencia2.getString(1);
//                txtuno.setText(dato1);
//            }
//            sentencia2.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
//        }
//    } 
    
//    
//    public void removeUsuario(String idUsuario)
//    {
//        Connection connect=null;
//        try
//        {
//            connect = connectionBD.getConexion();
//            Statement estatus = connect.createStatement();
//            estatus.executeUpdate("DELETE FROM usuario WHERE cedulausuario='"+idUsuario+"'");
//            JOptionPane.showMessageDialog(vMain, "Dato Eliminado");
//            estatus.close();            
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(vMain, "Error al eliminar.");
//        }
//    }
    
    
     public void modifyUsuario(Usuario usuario, String numUsuario) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE usuario SET nombreusuario=?, apellidousuario=?, contrausuario=? WHERE cedulausuario='"+numUsuario+"'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, usuario.getNombreUsuario());
            estatus.setString(2, usuario.getApellidoUsuario());
            estatus.setString(3, usuario.getContrasenaUsuario());
            estatus.executeUpdate();
            estatus.close();
           
            JOptionPane.showMessageDialog(vMain, "Se ha modificado");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }

    }

    public void modificarContrasena(String nuevaContrasena, String numUsuario) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE usuario SET contrausuario=? WHERE cedulausuario='" + numUsuario + "'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, nuevaContrasena);
            estatus.executeUpdate();
            estatus.close();
           
            JOptionPane.showMessageDialog(vMain, "Usted a modificado su contraseña");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }
         
    }
     
     
     
     public String searchContraCorreo(String idProveedor) {
        String data=" ";
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("select contracorreo from usuario where numusuario ='"+idProveedor+"'");
            while (sentencia2.next()) {
                data = sentencia2.getString(1);
                return data;
                
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
