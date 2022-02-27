package facturacion.datos;

import facturacion.components.ComboItem;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author fran_
 */
public class TipoIva {
    static Map tipoIva;

    public static void initTipoIva() {
        tipoIva = new HashMap<Integer,Integer>();
        tipoIva.put(0, 4);
        tipoIva.put(1, 10);
        tipoIva.put(2, 21);
    }

    public static Integer getDescTipoIva(Integer tipo) {
        if (tipoIva==null) initTipoIva();
        return (Integer)tipoIva.get(tipo);
    }

 public static void llenarComboTipoIva(JComboBox comboCategoria) {
       if (tipoIva==null) initTipoIva();
        for (Object clave:tipoIva.keySet()) {
            ComboItem c = new ComboItem(String.valueOf(clave), String.valueOf(tipoIva.get(clave))+"%");
            comboCategoria.addItem(c);
        }
        
    }

}
