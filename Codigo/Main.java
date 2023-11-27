import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USUARIO = "tu_usuario";
    private static final String CONTRASENA = "tu_contrasena";

    public static void main(String[] args) {
        try {
            // Conectar a la base de datos
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            // Crear un nuevo usuario
            String nuevoUsuario = extraerUsername("correo@unal.edu.co");
            String contrasena = "contrasena123";

            // Verificar si el username está disponible
            if (usuarioDisponible(conexion, nuevoUsuario)) {
                // Registrar el nuevo usuario
                registrarUsuario(conexion, nuevoUsuario, contrasena);
                System.out.println("Registro exitoso para " + nuevoUsuario);
            } else {
                System.out.println("El username no está disponible.");
            }

            // Cerrar la conexión
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean usuarioDisponible(Connection conexion, String username) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM Usuario WHERE username = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, username);
            ResultSet resultado = statement.executeQuery();
            resultado.next();
            return resultado.getInt(1) == 0;
        }
    }

    private static void registrarUsuario(Connection conexion, String username, String contrasena) throws SQLException {
        String insercion = "INSERT INTO Usuario (username, contrasena) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(insercion)) {
            statement.setString(1, username);
            statement.setString(2, contrasena);
            statement.executeUpdate();
        }
    }

    private static String extraerUsername(String correo) {
        return correo.split("@")[0];
    }
}