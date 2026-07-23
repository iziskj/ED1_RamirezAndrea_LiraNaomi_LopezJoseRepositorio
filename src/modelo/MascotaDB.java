/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author panch
 */
public class MascotaDB {

    public boolean insertar(Mascota m) {
        String sql_query = "INSERT INTO mascotas(nombre, raza, color, edad, estado_adopcion) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = ConexionDB.conexion();
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, m.getNombre());
            stmt.setString(2, m.getRaza());
            stmt.setString(3, m.getColor());
            stmt.setInt(4, m.getEdad());
            stmt.setString(5, m.getEstadoAdopcion());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar mascota: " + e.getMessage());
            return false;
        }
    }

    public List<Mascota> consultarMascotas() {
        List<Mascota> lista = new ArrayList<>();
        String query_sql = "SELECT * FROM mascotas";
        try {
            Connection conn = ConexionDB.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Mascota m = new Mascota(
                    result.getInt("id_mascota"),
                    result.getString("nombre"),
                    result.getString("raza"),
                    result.getString("color"),
                    result.getInt("edad"),
                    result.getString("estado_adopcion")
                );
                lista.add(m);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Mascota m) {
        String query_sql = "UPDATE mascotas SET nombre = ?, raza = ?, color = ?, edad = ?, estado_adopcion = ? WHERE id_mascota = ?";
        try {
            Connection conn = ConexionDB.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            stmt.setString(1, m.getNombre());
            stmt.setString(2, m.getRaza());
            stmt.setString(3, m.getColor());
            stmt.setInt(4, m.getEdad());
            stmt.setString(5, m.getEstadoAdopcion());
            stmt.setInt(6, m.getIdMascota());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idMascota) {
        String query_sql = "DELETE FROM mascotas WHERE id_mascota = ?";
        try {
            Connection conn = ConexionDB.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            stmt.setInt(1, idMascota);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}