package facturacioncarniceria.modelo;

import facturacioncarniceria.vista.VLoginMain;
import facturacioncarniceria.vista.VPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Conexion {

    Connection coneccion;
    
    JPanel myPanel = new JPanel();
    VPrincipal vmain = new VPrincipal();
    public Connection getConexion() 
    {
        do {

            coneccion = null;
            String url = "jdbc:postgresql://127.0.0.1:5454/FACTURACION?";
            String user = "postgres";
            String pass = "123098";
            try {
                Class.forName("org.postgresql.Driver").newInstance();
                coneccion = (Connection) DriverManager.getConnection(url,user,pass);
                return coneccion;
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(vmain, "Error de Conexión");              
                //System.exit(0);
            }

        } while (coneccion == null);
        
        

        return coneccion;
    }
}