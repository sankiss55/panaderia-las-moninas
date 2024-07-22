package base_de_datos;
import java.sql.*;
public class ConexionBaseDatos {
    public String bd="software";
    public String usuario="root";
    public String contrasena="";
    public String url="jdbc:mysql://localhost/"+bd;
    public Connection conexion=null;
    public ConexionBaseDatos(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection(url,usuario,contrasena);
            if(conexion!=null){
                System.out.println("Conexion a la base de datos exitosa");
            }
        }catch(SQLException | ClassNotFoundException e){
              e.printStackTrace();
              e.getMessage();
        }
    }
    public Connection getconexion(){
        return conexion;
    }
    
    public static void main (String []args){
      ConexionBaseDatos h=new ConexionBaseDatos();
     
        
      
    }
}