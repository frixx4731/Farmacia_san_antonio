
package conexión;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Sqlusuario extends ConexionBD{
    
    public boolean registrar(Usuario user) {
        String sql = "INSERT INTO usuarios (nombre_completo, apellido_paterno, apellido_materno, correo, rol, turno, contraseña_hash) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Usar try-with-resources para cierre automático de recursos
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Establecer parámetros
            ps.setString(1, user.getNombreCompleto());
            ps.setString(2, user.getApellidoPaterno());
            ps.setString(3, user.getApellidoMaterno());
            ps.setString(4, user.getCorreo());
            ps.setString(5, user.getRol());
            ps.setString(6, user.getTurno());
            ps.setString(7, user.getContrasenaHash());
            
            // Ejecutar inserción
            int filasAfectadas = ps.executeUpdate();
            
            // Retornar true si se insertó correctamente
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
