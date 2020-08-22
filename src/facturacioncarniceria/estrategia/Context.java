package facturacioncarniceria.estrategia;


import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Context {

  
    InterfaceStrategy strategy;

    public Context() {
    }

    
    public Context(InterfaceStrategy estrategia) {
        this.strategy = estrategia;
    }

    public void RunAnadir(Object obj, int tipo) {
        strategy.add(obj, tipo);
    }
    
    
    
//    public void RunAnadirCotizacionPedido(DefaultTableModel tablaCotizacion, String codigoPedido, String nombreRealizo){
//        strategy.addCotizacionPedido(tablaCotizacion, codigoPedido, nombreRealizo);
//    }
//    
    public void RunAnadirCompra(String codigoProovedor, String numFactura, String fechaCompra, DefaultTableModel tablaCotizacion)
    {
        strategy.addCompra(codigoProovedor, numFactura, fechaCompra, tablaCotizacion);
    }
    
    public void RunAnadirCompraVenta(String idmetologia, String cedulausuario, String fechaCompraVenta, String identificacionCompraVenta, String nombreCompraVenta, String numeroCompraVenta, double subtotal12compraventa, double base0compraventa, double iva12compraventa, double totalcompraventa) {
        strategy.addCompraVenta(idmetologia, cedulausuario, fechaCompraVenta, identificacionCompraVenta, nombreCompraVenta, numeroCompraVenta, subtotal12compraventa, base0compraventa, iva12compraventa, totalcompraventa);
    }
    
    public void RunVisualizar(DefaultTableModel tabla, int tipo) {
        strategy.visualize(tabla, tipo);
    }
    
    public void RunVisualizarAnular(DefaultTableModel tabla, String numeroFactura) {
        strategy.visualizeAnular(tabla, numeroFactura);
    }
    
    public String RunCodigo() {
        return strategy.codigoFactura();
    }

    public float RunPrecioCodigo(String codigoProducto) {
        return strategy.precioProducto(codigoProducto);
    }
    
    public float RunCantidadProducto(String codigoProducto) {
        return strategy.cantidadProducto(codigoProducto);
    }
    
    public void RunAnadirFacturaDetalle(DefaultTableModel tablaProductos, String cedulaCliente, String numeroFactura, String cedulaUsuario, int metodoPago) {
        strategy.addFacturaDetalle(tablaProductos, cedulaCliente, numeroFactura, cedulaUsuario, metodoPago);
    }
    
    public void RunAnadirCotizacionDetalle(DefaultTableModel tablaProductos, String numeroFactura, String cedulaUsuario, float metodoPago) {
        strategy.addCotiacionDetalle(tablaProductos, numeroFactura, cedulaUsuario, metodoPago);
    }
    
    public void RunvisualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, String cedula)
    {
        strategy.visualizeUsuarioContra(txtuno, txtdos, txttres, cedula);
    }
    
    public void RunvisualizeRucProveedor(JTextField txtuno,String cedula)
    {
        strategy.visualizeRucProveedor(txtuno, cedula);
    }
    
    public void RunvisualizeCotizacionDetalle(DefaultTableModel tableProvider, String codigoCotizacion)
    {
        strategy.visualizeCotizacionDetalle(tableProvider, codigoCotizacion);
    }
    
    public void RunvisualizarCliente(JTextField txtuno, JTextField txtdos, String codigoCotizacion) {
        strategy.visualizarCliente(txtuno, txtdos, codigoCotizacion);
    }

//    public void RunvisualizeDatosCotizacion(JTextField txtuno, JTextField txtdos, String cedula)
//    {
//        strategy.visualizeDatosCotizacion(txtuno, txtdos, cedula);
//    }
//    
//    public void RunVisualizarTotalCotizaccion(DefaultTableModel tabla, String codigoCotizacion, String improvisto, String utilidad) {
//        strategy.visualizeTotalCotizacion(tabla, codigoCotizacion, improvisto, utilidad, 0);
//    }
//    
//    public float RunvisualizeTotalProductos(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto, String utilidad){
//        return strategy.visualizeTotalProductos(tablaTotalProducto, codigoCotizacion, improvisto, utilidad);
//    }
//    
//    public void RunVisualizarImprovisto(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto, int num)
//    {
//           strategy.visualizeImprovisto(tablaTotalProducto, codigoCotizacion, improvisto,num);         
//    }
//    
    public void RunVisualizarContra(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro ,String cedula) {
        strategy.vizualizeTextoPedidoDatos(txtuno,txtdos,txttres,txtcuatro,cedula);
    }
    
    public void RunvisualizeCompraVenta(DefaultTableModel tableProvider, String fechaIncio, String fechaFin, int num)
    {
        strategy.visualizeCompraVenta(tableProvider, fechaIncio, fechaFin, num);
    }
//    
//    public void RunVisualizaeCodigoPedido(JTextField txtuno)
//    {
//        strategy.visualizeCodigoPedido(txtuno);
//    }
//    
//    public void RunvisualizeUtilidad(DefaultTableModel tablaTotalProducto, String codigoCotizacion, String improvisto, String utilidad)
//    {
//        strategy.visualizeUtilidad(tablaTotalProducto, codigoCotizacion, improvisto, utilidad);
//    }
//
//    
//    public void RunVisualizarTextoDatos(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, JTextField txtcinco, String codigo){
//        strategy.vizualizeTextoPedidoDatos(txtuno, txtdos, txttres, txtcuatro, txtcinco, codigo);
//    }
//       
//    public void RunVisualizarDetalle(DefaultTableModel modelo2, String codigoDetallePedido2, int num)
//    {
//        strategy.visualizeTablaDetalle(modelo2, codigoDetallePedido2,num);
//    }
//    
//    public void RunVisualizarCorreoElectronico(JTextField txtuno, String codigoUsuario)
//    {
//        strategy.visualizeCorreoElectronico(txtuno, codigoUsuario);
//    }
//    
//    public void RunVisualizarTxtCotizacion(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, JTextField txtcinco, JTextField txtseis, JTextField txtsiete, JTextField txtocho, JTextField txtnueve, JTextField txtdiez, JTextField txtonce, JTextField txtdoce, JTextField txttrece, JTextField txtcatorce, JTextField txtquince, JTextField txtdiesiseis, JTextField txtdiesisiete, JTextField txtdiesiocho, JTextField txtdiesinueve, JTextField txtveinte, JTextField txtveinteuno, String codigoCotizacion)
//    {
//        strategy.visualizeTxtCotizacion(txtuno, txtdos, txttres, txtcuatro, txtcinco, txtseis, txtsiete, txtocho, txtnueve, txtdiez, txtonce, txtdoce, txttrece, txtcatorce, txtquince, txtdiesiseis, txtdiesisiete, txtdiesiocho, txtdiesinueve, txtveinte, txtveinteuno, codigoCotizacion);
//    }
//    
//    
//    public void RunEliminar(String codigo){
//        strategy.remove(codigo);
//    }
//    
    public void RunModificar(Object obj, String codigo){
        strategy.modify(obj, codigo);
    }
    
    public void RunModificarCantidad(float cantidad, String codigoProducto) {
        strategy.modificarCantidad(cantidad, codigoProducto);
    }
    
    public void RunModificarContrasena(String contrasena, String ceculaUsuario) {
        strategy.modificarContrasena(contrasena, ceculaUsuario);
    }
//    
    public void RunLlenarCombo(JComboBox combo)
    {
        strategy.llenarComboBox(combo);
    }
//     
//    public String RunBuscarCodigoProyecto(String nombreProyecto)
//    {
//        return strategy.serach(nombreProyecto);
//    }
//    
//    public int RunBuscarCantidadProducto(String nombreProyecto)
//    {
//        return strategy.searchCantidadProducto(nombreProyecto);
//    }
//    
//    public float RunBuscarPrecioProducto(String nombreProyecto)
//    {
//        return strategy.searchPrecioProducto(nombreProyecto);
//    }
//    
//    public void RunModificarCantidadProducto(int cantidad,String estado,String codigoProducto)
//    {
//        
//        strategy.modifyCantidadProducto(cantidad, estado, codigoProducto);
//    }
//    
//    public void RunModificarPrecioCompra(Float compra, String numProducto)
//    {
//        strategy.modifyPrecioCompra(compra, numProducto);
//    }
//    
//    public void RunEliminarPedido(String idPedido, String codigoProducto)
//    {
//        strategy.removePedido(idPedido, codigoProducto);
//    }
//
//    public String RunValidarNombre(String codigo)
//    {
//        return strategy.validarNombreIngreso(codigo);
//    }
//    
//    public void RunDevolucionPedido(int cantidad, int devolucion, String codigoPedido, String codigoProducto) 
//    {
//        strategy.modifyPedidoDevolucion(cantidad, devolucion, codigoPedido, codigoProducto);
//    }
}

