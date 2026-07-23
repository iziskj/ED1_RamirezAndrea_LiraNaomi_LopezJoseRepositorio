/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import conexion.ConexionDB;
import java.sql.*;
/**
 *
 * @author panch
 */
public class UsuarioDB {

    public Usuario verificarLogin(String username, String password) {
        String query_sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
        try {
            Connection conn = ConexionDB.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Usuario usuario = new Usuario(
                    result.getInt("id_usuario"),
                    result.getString("usuario"),
                    result.getString("contrasena")
                );
                stmt.close();
                conn.close();
                return usuario;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
        return null;
    }
}
