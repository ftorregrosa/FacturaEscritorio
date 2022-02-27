/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.datos;

import java.util.Date;

/**
 *
 * @author JORDY
 */
public class FacturaDetalle {
    private String cedulaCliente;
    private int numerofactura;
    private String codigoProducto;
    private String cedulaUsuario;
    private float cantidadFactura;
    private float devolucionFactura;
    private float precioFactura;
    private int metodoPago;

    public int getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(int metodoPago) {
        this.metodoPago = metodoPago;
    }

    
    
    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public int getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(int numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public float getCantidadFactura() {
        return cantidadFactura;
    }

    public void setCantidadFactura(float cantidadFactura) {
        this.cantidadFactura = cantidadFactura;
    }

    public float getDevolucionFactura() {
        return devolucionFactura;
    }

    public void setDevolucionFactura(float devolucionFactura) {
        this.devolucionFactura = devolucionFactura;
    }

    public float getPrecioFactura() {
        return precioFactura;
    }

    public void setPrecioFactura(float precioFactura) {
        this.precioFactura = precioFactura;
    }

    
   
}
