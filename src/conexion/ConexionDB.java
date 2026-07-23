/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
/**
 * CONEXION DE LAS MASCOTAS
 * @author panch
 */
import java.sql.*;

public class ConexionDB {
    // Manejo de las constantes de datos de la conexion
    private static final String URL = "jdbc:mysql://localhost:3306/ed1_adoptcare_1";
    private static final String USER = "root";
    private static final String PASS = "";
    
    // Metodo para realizar la conexion
    public static Connection conexion(){
        Connection conn = null;
        // Manejar el error en la conexión con la base de datos
        try{
            // Guardar conexión en el objeto Connection
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.print("Conexion exitosa");
        }catch(SQLException e){
            // Manejar el error de SQL
            System.out.print("Error en la conexion: " + e.getMessage());
        }
        return conn;
    }
}
