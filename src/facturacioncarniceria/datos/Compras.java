
package facturacioncarniceria.datos;

import java.util.Date;

/**
 *
 * @author JORDY
 */
public class Compras {
    private int codigoProveedor;
    private String codigoProducto;
    private String numFactura;
    private int cantidadKardex;
    private float precioKardex;

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public int getCantidadKardex() {
        return cantidadKardex;
    }

    public void setCantidadKardex(int cantidadKardex) {
        this.cantidadKardex = cantidadKardex;
    }

    public float getPrecioKardex() {
        return precioKardex;
    }

    public void setPrecioKardex(float precioKardex) {
        this.precioKardex = precioKardex;
    }
    

  
}
