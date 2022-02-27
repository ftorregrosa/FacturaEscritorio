/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facturacion.datos;

import facturacion.components.ComboItem;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author fran_
 */
public class TipoProducto {
    static Map tipoProducto;

    public static void initTipoProducto() {
        tipoProducto = new HashMap<String,String>();
        tipoProducto.put("PAN", "Panadería");
        tipoProducto.put("BLL", "Bollería");
    }

    public static String getDescTipoProducto(String tipo) {
        if (tipoProducto==null) initTipoProducto();
        return (String) tipoProducto.get(tipo);
    }

 public static void llenarComboCategoria(JComboBox comboCategoria) {
       if (tipoProducto==null) initTipoProducto();
        for (Object clave:tipoProducto.keySet()) {
            ComboItem c = new ComboItem((String)clave, (String)tipoProducto.get(clave));
            comboCategoria.addItem(c);
        }
        
    }
}
