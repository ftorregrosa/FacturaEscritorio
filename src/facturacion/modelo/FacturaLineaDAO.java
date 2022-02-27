package facturacion.modelo;

import facturacion.datos.FacturaLinea;
import facturacion.datos.Factura_;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fran_
 */
public class FacturaLineaDAO {
     Conexion connectionBD;
 public FacturaLineaDAO(Conexion connectionBD) {
        this.connectionBD = connectionBD;
    }


public Integer addFacturaLinea(FacturaLinea facturaLinea) throws Exception {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO PUBLIC.PUBLIC.FACTURA_LINEA " +
            "(COD_FACTURA, COD_PRODUCTO, NOMRE_PRODUCTO, DESCRIPCION_PRODUCTO, CANTIDAD, PRECIO_PRODUCTO, DESCUENTO, IMPORTE_DESCUENTO, IVA, IMPORTE_IVA, TOTAL_LINEA) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            sentencia.setInt(1, facturaLinea.getCodFactura());
            sentencia.setInt(2, facturaLinea.getCodProducto());
            sentencia.setString(3, facturaLinea.getNombreProducto());
            sentencia.setString(4, facturaLinea.getDescProducto());
            sentencia.setDouble(5, facturaLinea.getCantidad());
            sentencia.setDouble(6, facturaLinea.getPrecioProducto());
            sentencia.setDouble(7, facturaLinea.getDescuento());
            sentencia.setDouble(8, facturaLinea.getImporteDescuento());
            sentencia.setDouble(9, facturaLinea.getIva());
            sentencia.setDouble(10, facturaLinea.getImporteIva());
            sentencia.setDouble(11, facturaLinea.getTotalLinea());
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

    public Integer updateFacturaLinea(FacturaLinea facturaLinea) throws Exception {
        Connection con = null;
        try {
            con = connectionBD.getConexion();
            PreparedStatement sentencia = con.prepareStatement("UPDATE FACTURA_LINEA "
                    + "SET NOMRE_PRODUCTO=?, DESCRIPCION_PRODUCTO=?, CANTIDAD=?, PRECIO_PRODUCTO=?, DESCUENTO=?, IMPORTE_DESCUENTO=?, IVA=?, IMPORTE_IVA=?, TOTAL_LINEA=? "
                    + " WHERE COD_FACTURA=? AND COD_PRODUCTO=?");
            sentencia.setString(1, facturaLinea.getNombreProducto());
            sentencia.setString(2, facturaLinea.getDescProducto());
            sentencia.setDouble(3, facturaLinea.getCantidad());
            sentencia.setDouble(4, facturaLinea.getPrecioProducto());
            sentencia.setDouble(5, facturaLinea.getDescuento());
            sentencia.setDouble(6, facturaLinea.getImporteDescuento());
            sentencia.setDouble(7, facturaLinea.getIva());
            sentencia.setDouble(8, facturaLinea.getImporteIva());
            sentencia.setDouble(9, facturaLinea.getTotalLinea());
            sentencia.setInt(10, facturaLinea.getCodFactura());
            sentencia.setInt(11, facturaLinea.getCodProducto());
            int i = sentencia.executeUpdate();
            if (i != 1) {
                throw new Exception("Fallo al actualizar Factura");
            }

            return i;
        } catch (Exception ex) {
            throw new Exception("Fallo al actualizar Factura: " + ex.getMessage());
        }
    }

    public Integer deleteFacturaLinea(Factura_ factura) {
        return 0;
    }

    public List<FacturaLinea> getFacturaLineaByCodFactura(Integer codFactura) {
        List<FacturaLinea> facturaLineas = new ArrayList<FacturaLinea>();
        Connection connect = null;
        try {
            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            String sentencia = "SELECT COD_FACTURA, COD_PRODUCTO, NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, CANTIDAD, PRECIO_PRODUCTO, DESCUENTO, IMPORTE_DESCUENTO, IVA, IMPORTE_IVA, TOTAL_LINEA "
                    + "FROM FACTURA_LINEA  WHERE 1=1";
            if (codFactura != null) {
                sentencia = sentencia.concat(String.format(" AND COD_FACTURA = %d", codFactura));
            }
            ResultSet sentencia2 = st.executeQuery(sentencia);
            while (sentencia2.next()) {
                FacturaLinea facturaLinea = new FacturaLinea();
                facturaLinea.setCodFactura(sentencia2.getInt(1));
                facturaLinea.setCodProducto(sentencia2.getInt(2));
                facturaLinea.setNombreProducto(sentencia2.getString(3));
                facturaLinea.setDescProducto(sentencia2.getString(4));
                facturaLinea.setCantidad(sentencia2.getDouble(5));
                facturaLinea.setPrecioProducto(sentencia2.getDouble(6));
                facturaLinea.setDescuento(sentencia2.getDouble(7));
                facturaLinea.setImporteDescuento(sentencia2.getDouble(8));
                facturaLinea.setIva(sentencia2.getInt(9));
                facturaLinea.setImporteIva(sentencia2.getDouble(10));
                facturaLinea.setTotalLinea(sentencia2.getDouble(11));
                facturaLineas.add(facturaLinea);

            }
            sentencia2.close();
        } catch (SQLException ex) {

        }
        return facturaLineas;
    }
}
