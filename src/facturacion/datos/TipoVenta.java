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
public class TipoVenta {

    static Map tipoVenta;

    public static void initTipoVenta() {
        tipoVenta = new HashMap();
        tipoVenta.put(1, "Unidad");
        tipoVenta.put(2, "Al Peso");
    }

    public static String getDescTipoVenta(int tipo) {
        if (tipoVenta == null) {
            initTipoVenta();
        }
        return (String) tipoVenta.get(tipo);
    }

    public static int getCodTipoVenta(String tipo) {
        if (tipoVenta == null) {
            initTipoVenta();
        }
        if (tipoVenta != null) {
            for (int i = 0; i < tipoVenta.size(); i++) {
                if (tipoVenta.get(i) != null && ((String) tipoVenta.get(i)).equals(tipo)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static void llenarComboTipoVenta(JComboBox comboTipoVenta) {
        if (tipoVenta == null) {
            initTipoVenta();
        }

        for (Object clave : tipoVenta.keySet()) {
            ComboItem c = new ComboItem(String.valueOf(clave), (String) tipoVenta.get(clave));
            comboTipoVenta.addItem(c);
        }

    }
}
