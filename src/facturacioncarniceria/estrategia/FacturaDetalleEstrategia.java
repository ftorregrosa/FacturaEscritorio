/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.estrategia;

import facturacioncarniceria.datos.Factura;
import facturacioncarniceria.datos.FacturaDetalle;
import facturacioncarniceria.modelo.FacturaDetalleDAO;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class FacturaDetalleEstrategia implements InterfaceStrategy
{
    FacturaDetalleDAO facturadetalledao=new FacturaDetalleDAO();

    public FacturaDetalleEstrategia() {
    }
    
    @Override
    public void add(Object obj, int tipo) {
        Factura factura;
        factura = (Factura) obj;
        facturadetalledao.addFactura(factura);
    }

    @Override
    public void visualize(DefaultTableModel modelo, int tipo) {
    
        facturadetalledao.visualizeFacturaDetalle(modelo);
    }

    @Override
    public void modify(Object obj, String numUsuario) {
//        Usuario usuario;
//        usuario = (Usuario) obj;
//        facturadetalledao.modifyUsuario(usuario, numUsuario);
    }

    @Override
    public void vizualizeTextoPedidoDatos(JTextField txtuno, JTextField txtdos, JTextField txttres, JTextField txtcuatro, String codigoPedido) {
        facturadetalledao.visualizarCliente(txtuno, txtdos, txttres, txtcuatro, codigoPedido);
    }

    @Override
    public String codigoFactura() {
        return facturadetalledao.CodigoFactura();
    }

    @Override
    public float precioProducto(String codigoProducto) {
        return facturadetalledao.precioFactura(codigoProducto);
    }

    @Override
    public void addFacturaDetalle(DefaultTableModel tablaProductos, String cedulaCliente, String numeroFactura, String cedulaUsuario, int metodoPago) {
        facturadetalledao.addFacturaDetalle(tablaProductos, cedulaCliente, numeroFactura, cedulaUsuario, metodoPago);
    }

    @Override
    public float cantidadProducto(String codigoProducto) {
        return facturadetalledao.cantidadProducto(codigoProducto);
    }

    @Override
    public void modificarCantidad(float cantidad, String codigoProducto) {
        facturadetalledao.modificarCantidad(cantidad, codigoProducto);
    }

    @Override
    public void visualizeUsuarioContra(JTextField txtuno, JTextField txtdos, JTextField txttres, String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarContrasena(String nuevaContrasena, String numUsuario) {
        facturadetalledao.modificarAnulada(numUsuario, numUsuario);
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
        facturadetalledao.visualizeAnular(tableProvider, codigoFacturaAnulada);
    }

    @Override
    public void addCotiacionDetalle(DefaultTableModel tablaProductos, String numeroFactura, String cedulaUsuario, float totalCotizacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCompraVenta(String idmetologia, String cedulausuario, String fechaCompraVenta, String identificacionCompraVenta, String nombreCompraVenta, String numeroCompraVenta, double subtotal12compraventa, double base0compraventa, double iva12compraventa, double totalcompraventa) {
        facturadetalledao.addCompraVenta(idmetologia, cedulausuario, fechaCompraVenta, identificacionCompraVenta, nombreCompraVenta, numeroCompraVenta, subtotal12compraventa, base0compraventa, iva12compraventa, totalcompraventa);
    }

    @Override
    public void visualizeCompraVenta(DefaultTableModel tableProvider, String fechaIncio, String fechaFin, int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizeCotizacionDetalle(DefaultTableModel tableProvider, String codigoCotizacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizarCliente(JTextField txtuno, JTextField txtdos, String codigoCotizacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
