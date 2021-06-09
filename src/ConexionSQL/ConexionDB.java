
package ConexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionDB 
{
    private static Connection con = null;
    private static String user = "JAVA APP";
    private static String clave = "1234";
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    
    public static Connection getConnection()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, clave);
            con.setAutoCommit(false);
            if(con != null)
            {
                System.out.println("¡Conexión Exitosa!");
            }
            else
            {
                System.out.println("¡Conexión Fallida!");
            }
        } catch (ClassNotFoundException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "¡Conexión Erronea!"+e.getMessage());
        }
       return con;
    }
    
    public void desconexion(){
        try {
            con.close();
        } catch (Exception e) 
        {
            System.out.println("Error al desconectar "+e.getMessage());
        }
    }
    public static void main(String[] args) {
        ConexionDB c = new ConexionDB();
        c.getConnection();
    }
    
}
