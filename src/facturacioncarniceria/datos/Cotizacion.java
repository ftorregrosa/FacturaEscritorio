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
public class Cotizacion {
    private String fechaInicioCotizacion;
    private String fechaFinCotizacion;

    public String getFechaInicioCotizacion() {
        return fechaInicioCotizacion;
    }

    public void setFechaInicioCotizacion(String fechaInicioCotizacion) {
        this.fechaInicioCotizacion = fechaInicioCotizacion;
    }

    public String getFechaFinCotizacion() {
        return fechaFinCotizacion;
    }

    public void setFechaFinCotizacion(String fechaFinCotizacion) {
        this.fechaFinCotizacion = fechaFinCotizacion;
    }

    
}
