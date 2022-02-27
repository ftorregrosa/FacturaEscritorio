
package facturacion.controlador;

import facturacion.vista.VLoginMain;
import facturacion.servicio.Context;
import facturacion.repositorio.HsqldbServerJdbcDriver;
import java.sql.Connection;
/**
 *
 * @author JORDY
 */
public class CFacturacionInicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Context context = new Context();
        //Cargamos la bbdd
        HsqldbServerJdbcDriver driver = new HsqldbServerJdbcDriver();
        try{
         Connection bbdd= driver.connect("jdbc:hsqldb:file:C:/Users/fran_/bbdd_facturacion/facturacion",null);}
        catch (Exception e){}
        VLoginMain vlogin = new VLoginMain();
        CMain controladorvprincipal = new CMain(context, vlogin);
        controladorvprincipal.iniciar();
    }
}
