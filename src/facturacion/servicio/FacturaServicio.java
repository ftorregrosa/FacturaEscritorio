package facturacion.servicio;

import facturacion.modelo.ClienteDAO;
import facturacion.modelo.Conexion;
import facturacion.modelo.FacturaDAO;
import facturacion.modelo.FacturaLineaDAO;
import facturacion.modelo.Factura_DAO;

/**
 *
 * @author fran_
 */
public class FacturaServicio {
        private Factura_DAO factura_DAO;
        private FacturaLineaDAO facturaLineaDAO;

    public FacturaServicio( Conexion conexionDb) {
        factura_DAO = new Factura_DAO(conexionDb);
    }

    public Factura_DAO getFactura_DAO() {
        return factura_DAO;
    }

    public FacturaLineaDAO getFacturaLineaDAO() {
        return facturaLineaDAO;
    }

}
