/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.datos;

import java.util.Date;

/**
 *
 * @author JORDY
 */
public class CotizacionDetalle {
    private String numeroCotizacion;
    private String codigoProductoCotizacion;
    private String cedulaUsuario;
    private float cantidadFactura;
    private float precioFactura;
    private float totalCotizacion;

    public String getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(String numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getCodigoProductoCotizacion() {
        return codigoProductoCotizacion;
    }

    public void setCodigoProductoCotizacion(String codigoProductoCotizacion) {
        this.codigoProductoCotizacion = codigoProductoCotizacion;
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

    public float getPrecioFactura() {
        return precioFactura;
    }

    public void setPrecioFactura(float precioFactura) {
        this.precioFactura = precioFactura;
    }

    public float getTotalCotizacion() {
        return totalCotizacion;
    }

    public void setTotalCotizacion(float totalCotizacion) {
        this.totalCotizacion = totalCotizacion;
    }

    
}
