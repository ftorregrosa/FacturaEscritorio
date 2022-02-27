/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.modelo;

import facturacion.datos.Producto;
import facturacion.datos.TipoIva;
import facturacion.datos.TipoProducto;
import facturacion.datos.TipoVenta;
import facturacion.vista.VPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JORDY
 */
public class ProductoDAO {

    Conexion connectionBD;
    DefaultTableModel model = new DefaultTableModel();
    VPrincipal vMain;

    public ProductoDAO(Conexion connectionBD) {
        this.connectionBD = connectionBD;
        vMain = new VPrincipal();
    }

    public void addProducto(Producto producto) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO PRODUCTO (NOMBRE, PRECIO, IVA, TIPO_VENTA, CATEGORIA) VALUES(?, ?, ?, ?, ?)");
            sentencia.setString(1, producto.getNombre());
            sentencia.setDouble(2, producto.getPrecio());
            sentencia.setInt(3, producto.getIva());
            sentencia.setInt(4, producto.getTipoVenta());
            sentencia.setString(5, producto.getTipoProducto());
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

    public void visualizeProducto(DefaultTableModel tableProvider) {
        visualizeProducto(tableProvider, null);
    }

    public void visualizeProducto(DefaultTableModel tableProvider, String categoria) {
        String data[] = new String[6];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            String sentencia2 = "SELECT CODIGO, NOMBRE, CATEGORIA,TIPO_VENTA, PRECIO, IVA FROM producto WHERE 1=1";
            if (categoria != null && !"".equals(categoria)) {
                sentencia2 = sentencia2.concat(String.format(" and categoria='%s' ", categoria));
            }
            sentencia2 = sentencia2.concat("order by nombre asc;");
            ResultSet rs = st.executeQuery(sentencia2);
            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = TipoProducto.getDescTipoProducto(rs.getString(3));//CATEGORIA
                data[3] = TipoVenta.getDescTipoVenta(rs.getInt(4));//TIPO VENTA
                data[4] = String.valueOf(rs.getDouble(5));
                data[5] = String.valueOf(TipoIva.getDescTipoIva(rs.getInt(6)));
                tableProvider.addRow(data);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Producto> getProducto(Integer codProducto) {
        List<Producto> productos = new ArrayList<Producto>();
        try {
            Connection connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            String sentencia2 = "SELECT CODIGO, NOMBRE, CATEGORIA,TIPO_VENTA, PRECIO, IVA FROM producto WHERE 1=1";
            if (codProducto != null) {
                sentencia2 = sentencia2.concat(String.format(" and producto=%d ", codProducto));
            }
            sentencia2 = sentencia2.concat("order by nombre asc;");
            ResultSet rs = st.executeQuery(sentencia2);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setTipoProducto(rs.getString(3));
                producto.setTipoVenta(rs.getInt(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setIva(rs.getInt(6));
                productos.add(producto);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }

    public Producto findProductoById(Integer codigo) {
        Producto producto = new Producto();
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            st.setMaxRows(1);
            String sentencia2 = String.format("SELECT CODIGO, NOMBRE, CATEGORIA,TIPO_VENTA, PRECIO, IVA FROM producto WHERE codigo=%d", codigo);
            ResultSet rs = st.executeQuery(sentencia2);
            while (rs.next()) {
                producto.setCodigo(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setTipoProducto(rs.getString(3));
                producto.setTipoVenta(rs.getInt(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setIva(rs.getInt(6));

            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return producto;
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
    public void modificarProducto(Producto producto) {
        Connection con = null;
        con = connectionBD.getConexion();
        try {
            String sentencia = String.format("UPDATE producto SET nombre=?, precio=?, iva=?, tipo_venta=?, categoria=? WHERE codigo=%d", producto.getCodigo());

            PreparedStatement estatus = con.prepareStatement(sentencia);
            estatus.setString(1, producto.getNombre());
            estatus.setDouble(2, producto.getPrecio());
            estatus.setInt(3, producto.getIva());
            estatus.setInt(4, producto.getTipoVenta());
            estatus.setString(5, producto.getTipoProducto());
            int filasActualizadas = estatus.executeUpdate();
            estatus.close();
            if (filasActualizadas == 1) {
                JOptionPane.showMessageDialog(vMain, "Se ha modificado");
            } else {
                JOptionPane.showMessageDialog(vMain, "No se Modifico");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vMain, "No se Modifico");
        }

    }

}
