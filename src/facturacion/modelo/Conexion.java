package facturacion.modelo;

import facturacion.vista.VPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class Conexion {

    Connection coneccion;
    
    JPanel myPanel = new JPanel();
    VPrincipal vmain = new VPrincipal();
    public Connection getConexion() 
    {
        do {

            coneccion = null;
            //String url = "jdbc:postgresql://192.168.0.2:5454/FACTURACION?";
            String url = "jdbc:hsqldb:hsql://localhost:9001/facturacion";
            //String user = "postgres";
            //String pass = "REYDECOPAS25";
            //String pass = "123098";
            try {
                Class.forName("org.hsqldb.jdbcDriver").newInstance();
                coneccion = (Connection) DriverManager.getConnection(url);
                return coneccion;
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(vmain, "Error de Conexión");              
                //System.exit(0);
            }

        } while (coneccion == null);
        
        

        return coneccion;
    }
}