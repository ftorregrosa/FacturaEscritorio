package facturacion.servicio;

import facturacion.components.ComboItem;
import facturacion.datos.Cliente;
import facturacion.modelo.ClienteDAO;
import facturacion.modelo.Conexion;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author fran_
 */
public class ClienteServicio {

    private ClienteDAO clienteDAO;

    public ClienteServicio( Conexion conexionDb) {
        clienteDAO = new ClienteDAO(conexionDb);
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }


    public List<Cliente> getClientes() {
        try {
            return clienteDAO.getClientes(null);
        } catch (Exception e) {
            return null;
        }

    }
    public Cliente getCliente(Integer codCliente) {
        try {
            List<Cliente> clientes = clienteDAO.getClientes(codCliente);
            if (clientes.size() != 1) {
                return null;
            } else {
                return clientes.get(0);
            }
        } catch (Exception e) {
            return null;
        }

    }

    public void llenarComboCLientes(JComboBox comboCategoria) {

        List<Cliente> clientes = getClientes();
        comboCategoria.addItem(new ComboItem("",""));
        for (Cliente cli : clientes) {
            ComboItem c = new ComboItem((String.valueOf(cli.getCodigo())), String.format("%s %s", cli.getCif(), cli.getRazonSocial()));
            comboCategoria.addItem(c);
        }
    }
}
