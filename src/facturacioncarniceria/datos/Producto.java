/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.datos;
/**
 *
 * @author JORDY
 */
public class Producto {
    private String codigo;
    private String medida;
    private String nomProducto;
    private float precio;
    private float existente;
    private String tieneIva;

    public String getTieneIva() {
        return tieneIva;
    }

    public void setTieneIva(String tieneIva) {
        this.tieneIva = tieneIva;
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getExistente() {
        return existente;
    }

    public void setExistente(float existente) {
        this.existente = existente;
    }
}
