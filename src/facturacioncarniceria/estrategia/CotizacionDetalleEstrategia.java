/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.estrategia;

import facturacioncarniceria.datos.CotizacionDetalle;
import facturacioncarniceria.datos.Cotizacion;
import facturacioncarniceria.modelo.CotizacionDetalleDAO;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CotizacionDetalleEstrategia implements InterfaceStrategy
{
    CotizacionDetalleDAO cotizaciondetalledao=new CotizacionDetalleDAO();

    public CotizacionDetalleEstrategia() {
    }
    
    @Override
    public void add(Object obj, int tipo) {
       Cotizacion cotizacion;
        cotizacion = (Cotizacion) obj;
        cotizaciondetalledao.addCotizaion(cotizacion);
    }

    @Override
    public void visualize(DefaultTableModel modelo, int tipo) {
    
        cotizaciondetalledao.visualizeFacturaDetalle(modelo);
    }

    @Override
    public void modify(Object obj, String numUsuario) {
//        Usuario usuario;
//        usuario = (Usuario) obj;
//        cotizaciondetalledao.modifyUsuario(usuario, numUsuario);
    }

    @Override
    public void vizualizeTextoPedidoDatos(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, String codigoPedido) {
//        cotizaciondetalledao.visualizarCliente(txtuno, txtdos, txttres, txtcuatro, codigoPedido);
    }

    @Override
    public String codigoFactura() {
        return cotizaciondetalledao.CodigoFactura();
    }

    @Override
    public float precioProducto(String codigoProducto) {
        return cotizaciondetalledao.precioFactura(codigoProducto);
    }

    @Override
    public void addFacturaDetalle(DefaultTableModel tablaProductos, String cedulaCliente, String numeroFactura, String cedulaUsuario, int metodoPago) {
       // cotizaciondetalledao.addFacturaDetalle(tablaProductos, cedulaCliente, numeroFactura, cedulaUsuario, metodoPago);
    }

    @Override
    public float cantidadProducto(String codigoProducto) {
        return cotizaciondetalledao.cantidadProducto(codigoProducto);
    }

    @Override
    public void modificarCantidad(float cantidad, String codigoProducto) {
        cotizaciondetalledao.modificarCantidad(cantidad, codigoProducto);
    }

    @Override
    public void visualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarContrasena(String nuevaContrasena, String numUsuario) {
       // cotizaciondetalledao.modificarAnulada(numUsuario, numUsuario);
    }

    @Override
    public void llenarComboBox(JComboBox combo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizeRucProveedor(JTextField txtuno, String codigoPedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCompra(String codigoProovedor, String numFactura, String fechaCompra, DefaultTableModel tablaCotizacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizeAnular(DefaultTableModel tableProvider, String codigoFacturaAnulada) {
//        cotizaciondetalledao.visualizeAnular(tableProvider, codigoFacturaAnulada);
    }

    @Override
    public void addCotiacionDetalle(DefaultTableModel tablaProductos, String numeroFactura, String cedulaUsuario, float totalCotizacion) {
        cotizaciondetalledao.addFacturaDetalle(tablaProductos, numeroFactura, cedulaUsuario, totalCotizacion);
    }

    @Override
    public void addCompraVenta(String idmetologia, String cedulausuario, String fechaCompraVenta, String identificacionCompraVenta, String nombreCompraVenta, String numeroCompraVenta, double subtotal12compraventa, double base0compraventa, double iva12compraventa, double totalcompraventa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizeCompraVenta(DefaultTableModel tableProvider, String fechaIncio, String fechaFin, int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizeCotizacionDetalle(DefaultTableModel tableProvider, String codigoCotizacion) {
        cotizaciondetalledao.visualizeCotizacionDetalle(tableProvider, codigoCotizacion);
    }

    @Override
    public void visualizarCliente(JTextField txtuno, JTextField txtdos, String codigoCotizacion) {
        cotizaciondetalledao.visualizarCliente(txtuno, txtdos, codigoCotizacion);
    }


}
