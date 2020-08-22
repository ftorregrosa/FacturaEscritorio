/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Cotizacion;
import facturacioncarniceria.datos.CotizacionDetalle;
import facturacioncarniceria.vista.VPrincipal;
import java.sql.Connection;
import java.sql.Date;
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
public class CotizacionDetalleDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public CotizacionDetalleDAO() {
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
    
    
    public void addFacturaDetalle(DefaultTableModel tablaProductos, String numeroFactura, String cedulaUsuario, float totalCotizacion) {
        Connection con = null;
        Integer entero = Integer.valueOf(numeroFactura);
        try {
            con = connectionBD.getConexion();
            for (int j = 0; j < tablaProductos.getRowCount()-4; j++) {
                //PreparedStatement sentencia = con.prepareStatement("INSERT INTO detallefactura(cedulacliente, numerofactura, codigoproducto, cedulausuario, idmetodopago,cantidadfactura, devolucionfactura, preciofactura)VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement sentencia = con.prepareStatement("INSERT INTO detallecotizacion(numerocotizacion, codigoproducto, cedulausuario, cantidadcotizacion, preciocotizacion, totalcotizacion)VALUES (?, ?, ?, ?, ?, ?);");
                sentencia.setInt(1, entero);
                sentencia.setString(2, tablaProductos.getValueAt(j, 0).toString());
                sentencia.setString(3, cedulaUsuario);
                sentencia.setFloat(4, Float.parseFloat(tablaProductos.getValueAt(j, 2).toString()));
                sentencia.setFloat(5, Float.parseFloat(tablaProductos.getValueAt(j, 3).toString()));
                sentencia.setFloat(6, totalCotizacion);

                //sentencia.setInt(5, 0);
                int i = sentencia.executeUpdate();

                if (i > 0) {
                    //JOptionPane.showMessageDialog(vMain, "Se almacenó correctamente");
                } else {

                    JOptionPane.showMessageDialog(vMain, "NO se pudo almacenar los datos");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vMain, "El Articulo ya se encuentra la listaaaaa   " + ex);
        }

    }
    
    public void visualizeFacturaDetalle(DefaultTableModel tableProvider) {

        String data[] = new String[4];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT codigoproducto, medidaproducto, nombreproducto, ivaproducto FROM producto order by codigoproducto asc");
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
    
    public void visualizeCotizacionDetalle(DefaultTableModel tableProvider, String codigoCotizacion) {

        String data[] = new String[6];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT A.codigoproducto, B.nombreproducto, A.cantidadcotizacion, A.preciocotizacion, ROUND((A.cantidadcotizacion*A.preciocotizacion),2) , B.ivaproducto\n" +
"FROM detallecotizacion A, producto B\n" +
"WHERE A.codigoproducto=B.codigoproducto AND numerocotizacion = '" + codigoCotizacion + "'");
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
    
    
    
    public float cantidadProducto(String codigoProducto) {
        float data = 0.00f;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT stockproducto FROM producto WHERE codigoproducto = '" + codigoProducto + "'");
            while (sentencia2.next()) {
                data = sentencia2.getFloat(1);
                return data;
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }

     public void modificarCantidad(float cantidad, String codigoProducto) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE producto SET stockproducto=? WHERE codigoproducto='"+codigoProducto+"'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setFloat(1, cantidad);
            estatus.executeUpdate();
            estatus.close();
           

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }
         
    }
    public String CodigoFactura() {
        String data=" ";
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT numerocotizacion FROM cotizacion order by numerocotizacion desc");
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

    public float precioFactura(String codigoProducto) {
        float data = 0;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT precioproducto FROM producto WHERE codigoproducto = '" + codigoProducto + "'");
            while (sentencia2.next()) {
                data = sentencia2.getFloat(1);
                return data;

            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
    
//    public void visualizeAnular(DefaultTableModel tableProvider, String codigoFacturaAnulada) {
//
//        String data[] = new String[6];
//        Connection connect = null;
//        try {
//            connect = connectionBD.getConexion();
//            Statement st = connect.createStatement();
//            ResultSet sentencia2 = st.executeQuery("SELECT A.codigoproducto, B.nombreproducto ,A.cantidadfactura, A.preciofactura, round((A.cantidadfactura*A.preciofactura),2) AS calculo, B.ivaproducto FROM detallefactura A, producto B Where A.codigoproducto=B.codigoproducto AND numerofactura= '" + codigoFacturaAnulada + "'");
//            while (sentencia2.next()) {              
//                data[0] = sentencia2.getString(1);
//                data[1] = sentencia2.getString(2);
//                data[2] = sentencia2.getString(3);
//                data[3] = sentencia2.getString(4);
//                data[4] = sentencia2.getString(5);
//                data[5] = sentencia2.getString(6);
//                tableProvider.addRow(data);
//           
//            }
//            sentencia2.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//     public void modificarAnulada(String anulada, String codigoProducto) {
//        Connection con = null;
//        con = connectionBD.getConexion();
//        try {
//            String sentencia = "UPDATE factura SET anuladafactura=? WHERE numerofactura='" + codigoProducto + "'";
//            PreparedStatement estatus = con.prepareStatement(sentencia);
//            estatus.setString(1, "SI");
//            estatus.executeUpdate();
//            estatus.close();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(vMain, "No se Modifico");
//        }
//
//    }

//    public void addFactura(Factura factura) {
//        Connection con = null;
//        try {
//            con = connectionBD.getConexion();
//            PreparedStatement sentencia = con.prepareStatement("INSERT INTO factura (fechafactura, anuladafactura) values (?,?)");
//            sentencia.setString(1, factura.getFechaFactura());
//            sentencia.setString(2, factura.getAnuladaFactura());
//            int i = sentencia.executeUpdate();
//            if (i > 0) {
//                JOptionPane.showMessageDialog(vMain, "Se Almacenó Correctamente");
//            } else {
//                JOptionPane.showMessageDialog(vMain, "No se pudo almacenar los Datos");
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(vMain, "Error no se guardo" + ex);
//        }
//    }
    
    
    public void visualizarCliente(JTextField txtuno, JTextField txtdos, String codigoCotizacion) {
        String dato1, dato2;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT fechainiciocotizacion, fechafincotizacion\n" +
"FROM cotizacion\n" +
"WHERE numerocotizacion = '" + codigoCotizacion + "'");
            while (sentencia2.next()) {
                dato1 = sentencia2.getString(1);
                dato2 = sentencia2.getString(2);
                txtuno.setText(dato1);
                txtdos.setText(dato2);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

}
