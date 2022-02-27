/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.datos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JORDY
 */
public class Factura_ {

    private Integer codigo;
    private Date fecha;
    private Date fechaAnulada;
    private String cif;
    private String formaCobro;
    private Double totalIva1;
    private Double totalIva2;
    private Double totalIva3;
    private Double precioTotal;
    private String comentario;
    private List<FacturaLinea> lineas;

    public Factura_() {
        totalIva1 = 0.0;
        totalIva2 = 0.0;
        totalIva3 = 0.0;
        precioTotal = 0.0;
        lineas = new ArrayList<FacturaLinea>();
    }


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getFormaCobro() {
        return formaCobro;
    }

    public void setFormaCobro(String formaCobro) {
        this.formaCobro = formaCobro;
    }

    public Double getTotalIva1() {
        return totalIva1;
    }

    public void setTotalIva1(Double totalIva1) {
        this.totalIva1 = totalIva1;
    }

    public Double getTotalIva2() {
        return totalIva2;
    }

    public void setTotalIva2(Double totalIva2) {
        this.totalIva2 = totalIva2;
    }

    public Double getTotalIva3() {
        return totalIva3;
    }

    public void setTotalIva3(Double totalIva3) {
        this.totalIva3 = totalIva3;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<FacturaLinea> getLineas() {
        return lineas;
    }

    public void setLineas(List<FacturaLinea> lineas) {
        this.lineas = lineas;
    }

    public FacturaLinea getFacturaLineaByProducto(Integer codProducto) {
        for (FacturaLinea fLinea : lineas) {
            if (codProducto==fLinea.getCodProducto()) {
                return fLinea;
            }
        }
        return null;
    }
    public FacturaLinea deleteFacturaLineaByProducto(Integer codProducto) {
        for (FacturaLinea fLinea : lineas) {
            if (codProducto==fLinea.getCodProducto()) {
                lineas.remove(fLinea);
            }
        }
        return null;
    }

    public FacturaLinea updateFacturaLineaByProducto(Integer codProducto,FacturaLinea facturaLinea) {
        for (FacturaLinea fLinea : lineas) {
            if (codProducto==fLinea.getCodProducto()) {
                lineas.remove(fLinea);
            }
        }
        lineas.add(facturaLinea);
        return null;
    }

}
