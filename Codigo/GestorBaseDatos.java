import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USUARIO = "tu_usuario";
    private static final String CONTRASENA = "tu_contrasena";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean usuarioDisponible(String username) {
        try (Connection conexion = obtenerConexion()) {
            String consulta = "SELECT COUNT(*) FROM Usuario WHERE username = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, username);
                return statement.executeQuery().getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void registrarUsuario(String username, String contrasena) {
        try (Connection conexion = obtenerConexion()) {
            String insercion = "INSERT INTO Usuario (username, contrasena) VALUES (?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(insercion)) {
                statement.setString(1, username);
                statement.setString(2, contrasena);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos para editar la base de datos

    // ...
}