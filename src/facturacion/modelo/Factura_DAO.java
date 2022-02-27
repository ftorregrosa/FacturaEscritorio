package facturacion.modelo;

import facturacion.datos.Factura_;

/**
 *
 * @author fran_
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura_DAO {

    Conexion connectionBD;

    public Factura_DAO(Conexion connectionBD) {
        this.connectionBD = connectionBD;
    }

    public Integer addFactura(Factura_ factura) throws Exception {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO FACTURA_ "
                    + "(FECHA, CIF, FORMA_COBRO, TOTAL_IVA_1, TOTAL_IVA_2, TOTAL_IVA_3, PRECIO_TOTAL, COMENTARIO) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sentencia.setDate(1, new java.sql.Date(factura.getFecha().getTime()));
            sentencia.setString(2, factura.getCif());
            sentencia.setString(3, factura.getFormaCobro());
            sentencia.setDouble(4, factura.getTotalIva1());
            sentencia.setDouble(5, factura.getTotalIva2());
            sentencia.setDouble(6, factura.getTotalIva3());
            sentencia.setDouble(7, factura.getPrecioTotal());
            sentencia.setString(8, factura.getComentario());
            int i = sentencia.executeUpdate();
            sentencia.close();
            if (i != 1) {
                throw new Exception("Fallo al insertar Factura");
            }

            return i;
        } catch (Exception ex) {
            throw new Exception("Fallo al insertar Factura: " + ex.getMessage());
        }
    }

    public Integer updateFactura(Factura_ factura) throws Exception {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("UPDATE FACTURA_ "
                    + "SET FECHA=?, CIF=?, FORMA_COBRO=?, TOTAL_IVA_1=?, TOTAL_IVA_2=?, TOTAL_IVA_3=?, PRECIO_TOTAL=?, COMENTARIO=?"
                    + "WHERE CODIGO=?");
            sentencia.setDate(1, new java.sql.Date(factura.getFecha().getTime()));
            sentencia.setString(2, factura.getCif());
            sentencia.setString(3, factura.getFormaCobro());
            sentencia.setDouble(4, factura.getTotalIva1());
            sentencia.setDouble(5, factura.getTotalIva2());
            sentencia.setDouble(6, factura.getTotalIva3());
            sentencia.setDouble(7, factura.getPrecioTotal());
            sentencia.setString(8, factura.getComentario());
            sentencia.setInt(9, factura.getCodigo());
            int i = sentencia.executeUpdate();
            if (i != 1) {
                throw new Exception("Fallo al actualizar Factura");
            }

            return i;
        } catch (Exception ex) {
            throw new Exception("Fallo al actualizar Factura: " + ex.getMessage());
        }
    }

    public Integer deleteFactura(Factura_ factura) {
        return 0;
    }

    public List<Factura_> getFactura(Integer codFactura) {
        List<Factura_> facturas = new ArrayList<Factura_>();
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            String sentencia = "SELECT CODIGO, FECHA, FECHA_ANULADA, CIF, FORMA_COBRO, TOTAL_IVA_1, TOTAL_IVA_2, TOTAL_IVA_3, PRECIO_TOTAL, COMENTARIO "
                    + "FROM FACTURA_  WHERE 1=1";
            if (codFactura != null) {
                sentencia = sentencia.concat(String.format(" AND CODIGO = %d", codFactura));
            }
            ResultSet sentencia2 = st.executeQuery(sentencia);
            while (sentencia2.next()) {
                Factura_ factura = new Factura_();
                factura.setCodigo(sentencia2.getInt(1));
                factura.setFecha(new Date(sentencia2.getDate(2).getTime()));
                factura.setFecha(sentencia2.getDate(3) == null ? null : new Date(sentencia2.getDate(3).getTime()));
                factura.setCif(sentencia2.getString(4));
                factura.setFormaCobro(sentencia2.getString(5));
                factura.setTotalIva1(sentencia2.getDouble(6));
                factura.setTotalIva2(sentencia2.getDouble(7));
                factura.setTotalIva3(sentencia2.getDouble(8));
                factura.setPrecioTotal(sentencia2.getDouble(9));
                factura.setComentario(sentencia2.getString(10));

                facturas.add(factura);

            }
            sentencia2.close();
        } catch (SQLException ex) {

        }
        return facturas;
    }
}
