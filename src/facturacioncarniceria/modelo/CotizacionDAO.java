/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Cotizacion;
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
public class CotizacionDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public CotizacionDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

    public void addCotizaion(Cotizacion cotizacion) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO cotizacion(fechainiciocotizacion, fechafincotizacion)VALUES (?, ?);");    
            sentencia.setString(1,cotizacion.getFechaInicioCotizacion());
            sentencia.setString(2,cotizacion.getFechaFinCotizacion());
            
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
    
    public void visualizeFacturaAnular(DefaultTableModel tableProvider) {
        String data[] = new String[6];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("Select DISTINCT A.numerofactura, A.cedulacliente, B.nombrecliente, C.fechafactura, D.nombreusuario || ' ' || D.apellidousuario\n" +
"From detallefactura A, cliente B, factura C, usuario D\n" +
"Where A.numerofactura = C.numerofactura AND A.cedulacliente = B.cedulacliente AND A.cedulausuario = D.cedulausuario AND C.anuladafactura = 'NO'");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void visualizarCliente(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, String cedula) {
        String dato1, dato2, dato3, dato4;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT nombrecliente || ' ' || apellidocliene, direccioncliente, telefonocliente, emailcliente FROM cliente WHERE cedulacliente = '" + cedula + "'");
            while (sentencia2.next()) {
                dato1 = sentencia2.getString(1);
                dato2 = sentencia2.getString(2);
                dato3 = sentencia2.getString(3);
                dato4 = sentencia2.getString(4);

                txtuno.setText(dato1);
                txtdos.setText(dato2);
                txttres.setText(dato3);
                txtcuatro.setText(dato4);

            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

//     public void modificarProducto(Producto producto, String numUsuario) {
//        Connection con = null;
//        con = connectionBD.getConexion();
//        try {
//            String sentencia = "UPDATE producto SET medidaproducto=?, nombreproducto=?, precioproducto=?, stockproducto=? WHERE codigoproducto='"+numUsuario+"'";
//            PreparedStatement estatus = con.prepareStatement(sentencia);
//            estatus.setString(1, producto.getMedida());
//            estatus.setString(2, producto.getNomProducto());
//            estatus.setFloat(3, producto.getPrecio());
//            estatus.setInt(4, producto.getExistente());
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
    public void modificarAnulada(String anulada, String codigoProducto) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE factura SET anuladafactura=? WHERE numerofactura='" + codigoProducto + "'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, anulada);
            estatus.executeUpdate();
            estatus.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }

    }

}
