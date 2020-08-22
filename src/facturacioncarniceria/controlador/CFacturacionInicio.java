
package facturacioncarniceria.controlador;

import facturacioncarniceria.vista.VLoginMain;
import facturacioncarniceria.estrategia.Context;
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
        VLoginMain vlogin = new VLoginMain();
        CMain controladorvprincipal = new CMain(context, vlogin);
        controladorvprincipal.iniciarVLogin();
    }
}
