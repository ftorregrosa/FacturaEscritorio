/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

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
public class ReportesDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public ReportesDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

//    public void addUsuario(Usuario provider) {
//        Connection con = null;
//        try {
//            con = connectionBD.getConexion();
//            PreparedStatement sentencia = con.prepareStatement("INSERT INTO usuario (cedulausuario, nombreusuario, apellidousuario, contrausuario) values (?,?,?,?)");
//            sentencia.setString(1, provider.getCedulaUsuario());       
//            sentencia.setString(2, provider.getNombreUsuario());
//            sentencia.setString(3, provider.getApellidoUsuario());
//            sentencia.setString(4, provider.getContrasenaUsuario());
//            int i = sentencia.executeUpdate();
//            if (i > 0) {
//                JOptionPane.showMessageDialog(vMain, "Se Almacenó Correctamente");
//            } else {
//                JOptionPane.showMessageDialog(vMain, "No se pudo almacenar los Datos");
//            }
//        } catch (Exception ex) {
//           JOptionPane.showMessageDialog(vMain, "Error no se guardo"+ex);
//        }
//    }
    
    
public void visualizeVenta(DefaultTableModel tableProvider, String fechaIncio, String fechaFin) {
        String data[] = new String[13];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT A.idcompraventa, A.tipocompraventa, A.fechacompraventa, \n" +
"A.identificacioncompraventa, A.nombrecompraventa, A.numerocompraventa, A.subtotal12compraventa, \n" +
"A.base0compraventa, A.iva12compraventa, A.totalcompraventa, \n" +
"B.nombrepago, C.nombreusuario ||' '|| C.apellidousuario, D.anuladafactura\n" +
"FROM compraventa A, metodopago B, usuario C, factura D\n" +
"WHERE D.numerofactura=A.numerofactura AND A.idmetodopago = B.idmetodopago AND A.cedulausuario=C.cedulausuario\n" +
"AND A.fechacompraventa >=  '" + fechaIncio + "' and A.fechacompraventa <=  '" + fechaFin + "' \n" +
"AND A.tipocompraventa='VENTA' ORDER BY A.idcompraventa ASC;");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                data[7] = sentencia2.getString(8);
                data[8] = sentencia2.getString(9);
                data[9] = sentencia2.getString(10);
                data[10] = sentencia2.getString(11);
                data[11] = sentencia2.getString(12);
                data[12] = sentencia2.getString(13);

                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
public void visualizeCompraAnulada(DefaultTableModel tableProvider, String fechaIncio, String fechaFin) {
        String data[] = new String[12];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT A.idcompraventa, A.tipocompraventa, A.fechacompraventa, \n" +
"A.identificacioncompraventa, A.nombrecompraventa, A.numerocompraventa, A.subtotal12compraventa, \n" +
"A.base0compraventa, A.iva12compraventa, A.totalcompraventa, \n" +
"B.nombrepago, C.nombreusuario ||' '|| C.apellidousuario\n" +
"FROM compraventa A, metodopago B, usuario C, factura D\n" +
"WHERE D.numerofactura=A.numerofactura AND A.idmetodopago = B.idmetodopago AND A.cedulausuario=C.cedulausuario\n" +
"AND A.fechacompraventa >=  '" + fechaIncio + "'and A.fechacompraventa <=  '" + fechaFin + "' AND D.anuladafactura = 'NO'\n" +
"AND A.tipocompraventa='VENTA' ORDER BY A.idcompraventa ASC;");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                data[7] = sentencia2.getString(8);
                data[8] = sentencia2.getString(9);
                data[9] = sentencia2.getString(10);
                data[10] = sentencia2.getString(11);
                data[11] = sentencia2.getString(12);

                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void visualizeCompra(DefaultTableModel tableProvider, String fechaIncio, String fechaFin) {
        String data[] = new String[12];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT A.idcompraventa, A.tipocompraventa, A.fechacompraventa, \n"
                    + "A.identificacioncompraventa, A.nombrecompraventa, A.numerocompraventa, A.subtotal12compraventa, \n"
                    + "A.base0compraventa, A.iva12compraventa, A.totalcompraventa, B.nombrepago, C.nombreusuario ||' '|| C.apellidousuario\n"
                    + "FROM compraventa A, metodopago B, usuario C\n"
                    + "WHERE A.idmetodopago = B.idmetodopago AND A.cedulausuario=C.cedulausuario \n"
                    + "AND A.fechacompraventa >=  '" + fechaIncio + "' and A.fechacompraventa <=  '" + fechaFin + "' \n"
                    + "AND A.tipocompraventa='COMPRA' ORDER BY A.idcompraventa ASC;");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                data[7] = sentencia2.getString(8);
                data[8] = sentencia2.getString(9);
                data[9] = sentencia2.getString(10);
                data[10] = sentencia2.getString(11);
                data[11] = sentencia2.getString(12);

                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    public void visualizeCompraProveedor(DefaultTableModel tableProvider) {
        String data[] = new String[12];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT A.numerocompra, A.facturacompra, A.codigoproducto, B.nombreproducto, A.cantidadcompra, A.preciocompra,\n" +
"ROUND((A.cantidadcompra*A.preciocompra),2), A.fechacompra, C.nombreproveedor, C.direccionproveedor, C.telefonoproveedor\n" +
"FROM compra A, producto B, proveedor C\n" +
"WHERE A.codigoproducto=B.codigoproducto AND A.rucproveedor=C.rucproveedor;");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                data[7] = sentencia2.getString(8);
                data[8] = sentencia2.getString(9);
                data[9] = sentencia2.getString(10);
                data[10] = sentencia2.getString(11);

                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
      
//     public void visualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, String cedula) {
//        String dato1,dato2,dato3;
//        Connection connect = null;
//        try {
//            connect = connectionBD.getConexion();
//            Statement st = connect.createStatement();
//            ResultSet sentencia2 = st.executeQuery("SELECT cedulausuario, nombreusuario || ' ' || apellidousuario, contrausuario FROM usuario WHERE cedulausuario = '"+cedula+"'");
//            while (sentencia2.next()) {
//                dato1 = sentencia2.getString(1);
//                dato2 = sentencia2.getString(2);
//                dato3 = sentencia2.getString(3);
//             
//                txtuno.setText(dato1);
//                txtdos.setText(dato2);
//                txttres.setText(dato3);
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
//    public void modificarContrasena(String nuevaContrasena, String numUsuario) {
//        Connection con = null;
//        con = connectionBD.getConexion();
//        try {
//            String sentencia = "UPDATE usuario SET contrausuario=? WHERE cedulausuario='" + numUsuario + "'";
//            PreparedStatement estatus = con.prepareStatement(sentencia);
//            estatus.setString(1, nuevaContrasena);
//            estatus.executeUpdate();
//            estatus.close();
//           
//            JOptionPane.showMessageDialog(vMain, "Usted a modificado su contraseña");
//            
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(vMain, "No se Modifico");
//        }
//         
//    }
//     
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
