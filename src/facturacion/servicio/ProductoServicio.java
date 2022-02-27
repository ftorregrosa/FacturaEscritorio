package facturacion.servicio;

import facturacion.datos.Producto;
import facturacion.datos.TipoIva;
import facturacion.datos.TipoProducto;
import facturacion.datos.TipoVenta;
import facturacion.modelo.ClienteDAO;
import facturacion.modelo.Conexion;
import facturacion.modelo.ProductoDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fran_
 */
public class ProductoServicio {

    ProductoDAO productoDAO;

    public ProductoServicio(Conexion conexionDb) {
        productoDAO = new ProductoDAO(conexionDb);
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public void rellenaComboProductosFactura(DefaultTableModel modeloTablaFacturaProdutos) {
        List<Producto> productos= productoDAO.getProducto(null);
        for(Producto producto:productos){
            String[] data = new String[4];
            data[0] = String.valueOf(producto.getCodigo());
            data[1] = producto.getNombre();
            data[2] = String.valueOf(producto.getPrecio());
            data[3] = String.valueOf(TipoIva.getDescTipoIva(producto.getIva()));
            modeloTablaFacturaProdutos.addRow(data);
    }

    }

}
