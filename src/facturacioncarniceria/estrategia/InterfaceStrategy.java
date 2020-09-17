package facturacioncarniceria.estrategia;

import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface InterfaceStrategy {
    //anadir
    public void add(Object obj, int tipo);
    
    public void addFacturaDetalle(DefaultTableModel tablaProductos, String cedulaCliente, String numeroFactura, String cedulaUsuario, int metodoPago);
    
//    public void addCotizacionPedido(DefaultTableModel tablaCotizacion, String codigoPedido, String nombreRealizoPedido);
//    
    public void addCompra(String codigoProovedor, String numFactura, String fechaCompra, DefaultTableModel tablaCotizacion);
//   
    public void addCotiacionDetalle(DefaultTableModel tablaProductos, String numeroFactura, String cedulaUsuario, float totalCotizacion);
    //modificar
////    public void modify(Object obj);
////    
    public void modify(Object obj, String numUsuario);
    
    public void addCompraVenta(String idmetologia, String cedulausuario, String fechaCompraVenta, String identificacionCompraVenta, String nombreCompraVenta, String numeroCompraVenta, double subtotal12compraventa, double base0compraventa, double iva12compraventa, double totalcompraventa);
////
//    public void modifyCantidadProducto(int cantidad, String estado, String codigoProducto);
//    
//    public void modifyPrecioCompra(Float compra, String numProducto);
////    
////    
    //visualizar
    public void visualize(DefaultTableModel modelo, int tipo);
    
    public void visualizeAnular(DefaultTableModel tableProvider, String codigoFacturaAnulada);
    
    public void visualizeCompraVenta(DefaultTableModel tableProvider, String fechaIncio, String fechaFin, int num);
//    
//    public void visualizeCodigoPedido(JTextField codigo);
//    
//    public void visualizeImprovisto(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto,int num);
//    
//    public void visualizeTotalCotizacion(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto,String utilidad,int num);
//    
//    public void vizualizeContra(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, JTextField txtcinco,String cedula);
//    
    public void vizualizeTextoPedidoDatos(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, String codigoPedido);

    public void visualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, String cedula);
    
    public void visualizeCotizacionDetalle(DefaultTableModel tableProvider, String codigoCotizacion);
    
    public void visualizarCliente(JTextField txtuno, JTextField txtdos, String codigoCotizacion);
////
//    public void visualizeTablaDetalle(DefaultTableModel modelo, String detallePedido, int num);
////    
//    public void visualizeCorreoElectronico(JTextField txtuno, String codigoUsuario);
//    
//    public float visualizeTotalProductos(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto, String utilidad);
//    
//    public void visualizeDatosCotizacion(JTextField txtuno, JTextField txtdos, String cedula);
//    
//    public void visualizeUtilidad(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto, String utilidad);
//
//    public void visualizeTxtCotizacion(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro,
//            JTextField txtcinco, JTextField txtseis, JTextField txtsiete, JTextField txtocho, JTextField txtnueve,
//            JTextField txtdiez, JTextField txtonce, JTextField txtdoce, JTextField txttrece, JTextField txtcatorce,
//            JTextField txtquince, JTextField txtdiesiseis, JTextField txtdiesisiete, JTextField txtdiesiocho,
//            JTextField txtdiesinueve, JTextField txtveinte, JTextField txtveinteuno, String codigoCotizacion);
////
////    
////    //eliminar
//    public void remove(String numeroFactura);
////    
//    public void removePedido(String idPedido, String codigoProducto);
////    
////    
////    //buscar
//    public String serach(String nombre);
//    
//    public int searchCantidadProducto(String idProveedor);
////    public void search(DefaultTableModel modelo,String nombre );
////    
//    public void searchProductoHerramienta(DefaultTableModel modelo,String nombre, int num );
//    
//    public float searchPrecioProducto(String idProveedor);
////    
////    //public void searchData(String nombre,Object obj );
////    
////    
////    //comobox

//
//    
//    
//    //validar
//    public String validarNombreIngreso(String codigopedido);
    
//    
//    //devoluciones detalle pedido
//    public void modifyPedidoDevolucion(int cantidad, int devolucion, String codigoPedido, String codigoProducto);
//    
//    
    
    public void modificarCantidad(float cantidad, String codigoProducto);
    public void modificarContrasena(String nuevaContrasena, String numUsuario);
    
    public void visualizeRucProveedor(JTextField txtuno, String codigoPedido);
    public String codigoFactura();
    public float precioProducto(String codigoProducto);
    public float cantidadProducto(String codigoProducto);
    public void llenarComboBox(JComboBox combo);
}
