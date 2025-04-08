
package conexión;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Sqlusuario extends ConexionBD{
    
    public boolean registrar(Usuario user) {
        String sql = "INSERT INTO usuarios (nombre_completo, apellido_paterno, apellido_materno, correo, usuario, rol, turno, contraseña_hash) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Usar try-with-resources para cierre automático de recursos
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Establecer parámetros
            ps.setString(1, user.getNombreCompleto());
            ps.setString(2, user.getApellidoPaterno());
            ps.setString(3, user.getApellidoMaterno());
            ps.setString(4, user.getCorreo());
            ps.setString(5, user.getUser());
            ps.setString(6, user.getRol());
            ps.setString(7, user.getTurno());
            ps.setString(8, user.getContrasenaHash());
            
            // Ejecutar inserción
            int filasAfectadas = ps.executeUpdate();
            
            // Retornar true si se insertó correctamente
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    public boolean existeUsuario(String usuario) throws SQLException {
    String sql = "SELECT EXISTS(SELECT 1 FROM usuarios WHERE usuario = ?)";
    
    try (Connection con = getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, usuario);
        
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() && rs.getBoolean(1);
        }
    }
}
    public boolean LOGIN(String usuario) throws SQLException {
    String sql = "SELECT EXISTS(SELECT 1 FROM usuarios WHERE usuario = ?)";
    
    try (Connection con = getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, usuario);
        
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() && rs.getBoolean(1);
        }
    }
}
}
