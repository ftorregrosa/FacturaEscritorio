/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Cliente;
import facturacioncarniceria.vista.VPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JORDY
 */
public class ClienteDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public ClienteDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

    public void addUsuario(Cliente cliene) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO cliente (cedulacliente, nombrecliente, apellidocliene, direccioncliente, telefonocliente, emailcliente) values (?,?,?,?,?,?)");
            sentencia.setString(1, cliene.getCedulaCliente());       
            sentencia.setString(2, cliene.getNombreCliente());
            sentencia.setString(3, cliene.getApellidoCliente());
            sentencia.setString(4, cliene.getDireccionCliente());
            sentencia.setString(5, cliene.getTelefonoCliente());
            sentencia.setString(6, cliene.getEmailCliente());
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
        String data[] = new String[7];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT *FROM cliente");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
//      
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
    
    
//     public void modifyUsuario(Usuario usuario, String numUsuario) {
//        Connection con = null;
//        con = connectionBD.getConexion();
//        try {
//            String sentencia = "UPDATE usuario SET nombreusuario=?, apellidousuario=?, contrausuario=? WHERE cedulausuario='"+numUsuario+"'";
//            PreparedStatement estatus = con.prepareStatement(sentencia);
//            estatus.setString(1, usuario.getNombreUsuario());
//            estatus.setString(2, usuario.getApellidoUsuario());
//            estatus.setString(3, usuario.getContrasenaUsuario());
//            estatus.executeUpdate();
//            estatus.close();
//           
//            JOptionPane.showMessageDialog(vMain, "Se ha modificado");
//            
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(vMain, "No se Modifico");
//        }
//         
//    }
//     
//     
//     public String searchContraCorreo(String idProveedor) {
//        String data=" ";
//        Connection connect = null;
//        try {
//            connect = connectionBD.getConexion();
//            Statement st = connect.createStatement();
//            ResultSet sentencia2 = st.executeQuery("select contracorreo from usuario where numusuario ='"+idProveedor+"'");
//            while (sentencia2.next()) {
//                data = sentencia2.getString(1);
//                return data;
//                
//            }
//            sentencia2.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
//        }
//        return data;
//    }
}
