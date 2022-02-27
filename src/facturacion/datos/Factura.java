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
public class Factura {
    private int numeroFactura;
    private String fechaFactura;
    private String anuladaFactura;

    public String getAnuladaFactura() {
        return anuladaFactura;
    }

    public void setAnuladaFactura(String anuladaFactura) {
        this.anuladaFactura = anuladaFactura;
    }
    
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }


    
    

   
}
