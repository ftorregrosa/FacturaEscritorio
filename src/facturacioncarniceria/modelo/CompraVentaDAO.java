
package facturacioncarniceria.modelo;

import facturacioncarniceria.datos.Compras;
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
public class CompraVentaDAO {
    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public CompraVentaDAO() {
        this.connectionBD = new Conexion();
        vMain = new VPrincipal();
    }

    public void addCompraVenta(String cedulausuario, String fechacompraventa, String fechaCompraVenta, String identificacionCompraVenta, String nombreCompraVenta, String numeroCompraVenta, float subtotal12compraventa, float base0compraventa, float iva12compraventa, float totalcompraventa) {

        Connection con = null;
        try {
            con = connectionBD.getConexion();

                PreparedStatement sentencia = con.prepareStatement("INSERT INTO compraventa(cedulausuario, tipocompraventa, fechacompraventa, identificacioncompraventa, nombrecompraventa, numerocompraventa, subtotal12compraventa, base0compraventa, iva12compraventa, totalcompraventa)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");             
                sentencia.setString(1, cedulausuario);
                sentencia.setString(2, "VENTA");
                sentencia.setString(3, fechaCompraVenta);
                sentencia.setString(4, identificacionCompraVenta);
                sentencia.setString(5, nombreCompraVenta);
                sentencia.setString(6, numeroCompraVenta);
                sentencia.setFloat(7, subtotal12compraventa);
                sentencia.setFloat(8, base0compraventa);
                sentencia.setFloat(9, iva12compraventa);
                sentencia.setFloat(10, totalcompraventa);
                
                int i = sentencia.executeUpdate();

                if (i > 0) {

                } else {

                    JOptionPane.showMessageDialog(vMain, "NO se pudo almacenar los datos");
                }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vMain, "El Articulo Repetido Revise la lista " +ex);
        }

    }
     
    public void visualizeRucProveedor(JTextField txtuno, String codigoPedido) {
        String dato1;
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("Select rucproveedor from proveedor where nombreproveedor ='"+codigoPedido+"' ");
            while (sentencia2.next()) {
                dato1 = sentencia2.getString(1);
                
                txtuno.setText(dato1);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void llenarComboCompra(JComboBox comboCompra) {
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT nombreproveedor FROM proveedor order by rucproveedor");
            while (sentencia2.next()) {
                comboCompra.addItem(sentencia2.getString("nombreproveedor"));
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void visualizeProductosCompra(DefaultTableModel tableProvider) {

        String data[] = new String[3];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT codigoproducto, medidaproducto, nombreproducto FROM producto order by codigoproducto asc");
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
    public void modifyProductoCompra(Float compra, String numProducto) {
        Connection con = null;
        
        con = connectionBD.getConexion();
      
        try {
            String sentencia = "UPDATE productos SET pvp=? WHERE codigoproducto='"+numProducto+"'";
            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setFloat(1, compra);
            estatus.executeUpdate();
            estatus.close();
              
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(vMain, "NO se ha modificado");
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
    
}
