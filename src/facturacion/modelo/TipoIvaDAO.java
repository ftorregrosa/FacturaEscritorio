package facturacion.modelo;

import facturacion.datos.FacturaLinea;
import facturacion.datos.TipoIva;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fran_
 */
public class TipoIvaDAO {

    Conexion connectionBD;

    public TipoIvaDAO(Conexion connectionBD) {
        this.connectionBD = connectionBD;
    }

    public Map<Integer, Integer> getTipoIva(Integer codigo) {
        Map<Integer, Integer> tiposIva = new HashMap<Integer, Integer>();
        Connection connect = null;
        try {

            connect = connectionBD.getConexion();
            Statement st = connect.createStatement();
            String sentencia = "SELECT CODIGO,NOMBRE,VALOR FROM TIPOS_IVA WHERE 1=1 ";
            if (codigo != null) {
                sentencia = sentencia.concat(String.format(" AND CODIGO = %d", codigo));
            }
            sentencia = sentencia.concat(" ORDER BY VALOR ASC ");
            ResultSet sentencia2 = st.executeQuery(sentencia);
            while (sentencia2.next()) {
                tiposIva.put(sentencia2.getInt(1), sentencia2.getInt(3));
            }
            sentencia2.close();
        } catch (SQLException ex) {

        }
        return tiposIva;
    }
}
