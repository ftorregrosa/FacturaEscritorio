/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.modelo;

import facturacion.datos.Cliente;
import facturacion.vista.VPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public ClienteDAO(Conexion connectionBd) {
        this.connectionBD = connectionBd;
        vMain = new VPrincipal();
    }

    public void addCliente(Cliente cliente) {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO cliente (cif,razon_social, direccion, telefono, codigo_postal,poblacion,provincia) values (?,?,?,?,?,?,?)");
            sentencia.setString(1, cliente.getCif());       
            sentencia.setString(2, cliente.getRazonSocial());
            sentencia.setString(3, cliente.getDireccion());
            sentencia.setString(4, cliente.getTelefono());
            sentencia.setString(5, cliente.getCodigoPostal());
            sentencia.setString(6, cliente.getPoblacion());
            sentencia.setString(7, cliente.getProvincia());
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
    
    
    public void visualize(DefaultTableModel tableProvider) {
        String data[] = new String[8];
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            ResultSet sentencia2 = st.executeQuery("SELECT codigo,cif,razon_social, direccion, telefono, codigo_postal,poblacion,provincia FROM cliente");
            while (sentencia2.next()) {
                data[0] = sentencia2.getString(1);
                data[1] = sentencia2.getString(2);
                data[2] = sentencia2.getString(3);
                data[3] = sentencia2.getString(4);
                data[4] = sentencia2.getString(5);
                data[5] = sentencia2.getString(6);
                data[6] = sentencia2.getString(7);
                data[7] = sentencia2.getString(8);
                tableProvider.addRow(data);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vMain, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }



public List<Cliente> getClientes(Integer codCliente) throws SQLException{
        List<Cliente> clientes = new ArrayList<Cliente>();
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            String sentencia= "SELECT codigo,cif,razon_social, direccion, telefono, codigo_postal,poblacion,provincia FROM cliente WHERE 1=1 ";
            if (codCliente!=null){
                sentencia= sentencia.concat(String.format ("and codigo=%d",codCliente));
            }
            ResultSet sentencia2 = st.executeQuery(sentencia);
            while (sentencia2.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(sentencia2.getInt(1));
                cliente.setCif(sentencia2.getString(2));
                cliente.setRazonSocial(sentencia2.getString(3));
                cliente.setDireccion(sentencia2.getString(4));
                cliente.setTelefono(sentencia2.getString(5));
                cliente.setCodigoPostal(sentencia2.getString(6));
                cliente.setPoblacion(sentencia2.getString(7));
                cliente.setProvincia(sentencia2.getString(8));
                clientes.add(cliente);
            }
            sentencia2.close();
        } catch (SQLException ex) {
            throw ex;
        }
    return clientes;
    }

}
