package facturacion.servicio;

import facturacion.modelo.Conexion;
import facturacion.servicio.ClienteServicio;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Context {

    private ClienteServicio clienteServicio;
    private FacturaServicio facturaServicio;
    private ProductoServicio productoServicio;
    private Conexion conexionBd;

    public Context() {
        this.conexionBd = new Conexion();
        this.clienteServicio = new ClienteServicio(this.conexionBd);
        this.facturaServicio = new FacturaServicio(conexionBd);
        this.productoServicio = new ProductoServicio(conexionBd);
    }

    public ClienteServicio getClienteServicio() {
        return clienteServicio;
    }

    public FacturaServicio getFacturaServicio() {
        return facturaServicio;
    }

    public ProductoServicio getProductoServicio() {
        return productoServicio;
    }

    public Conexion getConexionBd() {
        return conexionBd;
    }

}
