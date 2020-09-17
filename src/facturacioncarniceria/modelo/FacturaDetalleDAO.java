/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Factura;
import facturacioncarniceria.datos.FacturaDetalle;
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
public class FacturaDetalleDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public FacturaDetalleDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }
    
    public void addFacturaDetalle(DefaultTableModel tablaProductos, String cedulaCliente, String numeroFactura, String cedulaUsuario, int metodoPago) {
        Connection con = null;
        Integer entero = Integer.valueOf(numeroFactura);
        try {
            con = connectionBD.getConexion();
            for (int j = 0; j < tablaProductos.getRowCount()-4; j++) {
                PreparedStatement sentencia = con.prepareStatement("INSERT INTO detallefactura(cedulacliente, numerofactura, codigoproducto, cedulausuario, idmetodopago,cantidadfactura, devolucionfactura, preciofactura)VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

                sentencia.setString(1, cedulaCliente);
                sentencia.setInt(2, entero);
                sentencia.setString(3, tablaProductos.getValueAt(j, 0).toString());
                sentencia.setString(4, cedulaUsuario);
                sentencia.setInt(5, metodoPago);
                sentencia.setFloat(6, Float.parseFloat(tablaProductos.getValueAt(j, 2).toString()));
                sentencia.setFloat(7, 0);
                sentencia.setFloat(8, Float.parseFloat(tablaProductos.getValueAt(j, 3).toString()));

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

    public void addFactura(FacturaDetalle facturadetalle) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO detallefactura(cedulacliente, numerofactura, codigoproducto, cedulausuario, cantidadfactura, devolucionfactura, preciofactura)VALUES (?, ?, ?, ?, ?, ?, ?)");    
            sentencia.setString(1,facturadetalle.getCedulaCliente());
            sentencia.setInt(2,facturadetalle.getNumerofactura());
            sentencia.setString(3,facturadetalle.getCodigoProducto());
            sentencia.setString(4,facturadetalle.getCedulaUsuario());
            sentencia.setFloat(5,facturadetalle.getCantidadFactura());
            sentencia.setFloat(6,0);
            sentencia.setFloat(7,facturadetalle.getPrecioFactura());
            
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
    
    
    public void visualizeFacturaDetalle(DefaultTableModel tableProvider) {

        String data[] = new String[4];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT codigoproducto, medidaproducto, nombreproducto, ivaproducto FROM producto WHERE stockproducto>0 order by codigoproducto asc");
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
            ResultSet sentencia2 = st.executeQuery("SELECT numerofactura FROM factura order by numerofactura desc");
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
    
    public void visualizeAnular(DefaultTableModel tableProvider, String codigoFacturaAnulada) {

        String data[] = new String[6];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT A.codigoproducto, B.nombreproducto ,A.cantidadfactura, A.preciofactura, round((A.cantidadfactura*A.preciofactura),2) AS calculo, B.ivaproducto FROM detallefactura A, producto B Where A.codigoproducto=B.codigoproducto AND numerofactura= '" + codigoFacturaAnulada + "'");
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
     public void modificarAnulada(String anulada, String codigoProducto) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = "UPDATE factura SET anuladafactura=? WHERE numerofactura='" + codigoProducto + "'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, "SI");
            estatus.executeUpdate();
            estatus.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }

    }

    public void addFactura(Factura factura) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO factura (fechafactura, anuladafactura) values (?,?)");
            sentencia.setString(1, factura.getFechaFactura());
            sentencia.setString(2, factura.getAnuladaFactura());
            int i = sentencia.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(vMain, "Se Almacenó Correctamente");
            } else {
                JOptionPane.showMessageDialog(vMain, "No se pudo almacenar los Datos");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vMain, "Error no se guardo" + ex);
        }
    }
    
    
    public void visualizarCliente(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, String cedula) {
        String dato1, dato2, dato3, dato4;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT cedulacliente, direccioncliente, telefonocliente, emailcliente FROM cliente WHERE cedulacliente = '" + cedula + "'");
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
    
    public void addCompraVenta(String idmetologia, String cedulausuario, String fechaCompraVenta, String identificacionCompraVenta, String nombreCompraVenta, String numeroCompraVenta, double subtotal12compraventa, double base0compraventa, double iva12compraventa, double totalcompraventa) {
        int metodoPago = Integer.parseInt(idmetologia);
        int numfactura = Integer.parseInt(numeroCompraVenta);
        Connection con = null;
        try {
            con = connectionBD.getConexion();

                PreparedStatement sentencia = con.prepareStatement("INSERT INTO compraventa(idmetodopago, cedulausuario, numerofactura, tipocompraventa, fechacompraventa, identificacioncompraventa, nombrecompraventa, numerocompraventa, subtotal12compraventa, base0compraventa, iva12compraventa, totalcompraventa)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");             
                sentencia.setInt(1, metodoPago);
                sentencia.setString(2, cedulausuario);
                sentencia.setInt(3, numfactura);
                sentencia.setString(4, "VENTA");
                sentencia.setString(5, fechaCompraVenta);
                sentencia.setString(6, identificacionCompraVenta);
                sentencia.setString(7, nombreCompraVenta);
                sentencia.setString(8, numeroCompraVenta);
                sentencia.setDouble(9, subtotal12compraventa);
                sentencia.setDouble(10, base0compraventa);
                sentencia.setDouble(11, iva12compraventa);
                sentencia.setDouble(12, totalcompraventa);
                
                int i = sentencia.executeUpdate();

                if (i > 0) {

                } else {

                    JOptionPane.showMessageDialog(vMain, "NO se pudo almacenar los datos");
                }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vMain, "El Articulo Repetido Revise la lista " +ex);
        }

    }

}
