import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorBaseDatos {

    private Connection conexion;

    // Constructor
    public GestorBaseDatos(String dbURL, String username, String password) {
        try {
            // Establecer la conexión al crear una instancia de GestorBaseDatos
            conexion = DriverManager.getConnection(dbURL, username, password);
            // System.out.println("Conectado a la base de datos");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para verificar si un usuario está disponible
    public boolean usuarioDisponible(String username) {
        try {
            String consulta = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, username);
                ResultSet resultado = statement.executeQuery();
                resultado.next();
                return resultado.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String username, String contrasena) {
        // Verificar si el usuario está disponible
        if (usuarioDisponible(username)) {
            try {
                String insercion = "INSERT INTO usuarios (nombre_usuario, contrasena, correo_institucional) VALUES (?, ?, ?)";
                try (PreparedStatement statement = conexion.prepareStatement(insercion)) {
                    statement.setString(1, username);
                    statement.setString(2, contrasena);
                    statement.setString(3, username + "@unal.edu.co");
                    statement.executeUpdate();
                    System.out.println("Registro exitoso para " + username);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El username no está disponible.");
        }
    }

    // Obtener información de un usuario por su nombre de usuario
    public void obtenerInformacionUsuario(String username) {
        try {
            String consulta = "SELECT usuario_id, nombre_usuario, correo_institucional FROM usuarios WHERE nombre_usuario = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, username);
                ResultSet resultado = statement.executeQuery();

                if (resultado.next()) {
                    int id = resultado.getInt("usuario_id");
                    String correoInstitucional = resultado.getString("correo_institucional");

                    System.out.println("Información del usuario:");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre de usuario: " + username);
                    System.out.println("Correo institucional: " + correoInstitucional);
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void mostrarlibros() {
        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM libros");
                ResultSet resultSet = statement.executeQuery()) {

            // Iterar sobre los resultados y mostrar los géneros
            while (resultSet.next()) {
                String nombreLibro = resultSet.getString("titulo");
                System.out.println(nombreLibro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void mostrargeneros() {
        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM generos");
                ResultSet resultSet = statement.executeQuery()) {

            // Iterar sobre los resultados y mostrar los géneros
            while (resultSet.next()) {
                String nombreGenero = resultSet.getString("nombre_genero");
                System.out.println(nombreGenero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Obtener información de un libro por su titulo
    public void obtenerInformacionLibro(String parteDelTitulo) {
        try (PreparedStatement statement = conexion.prepareStatement("SELECT titulo, autor, isbn, resumen, fecha_publicacion, generos FROM libros WHERE titulo LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
    
            // Establecer el parámetro del título en el PreparedStatement
            statement.setString(1, "%" + parteDelTitulo + "%");
    
            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
    
                // Verificar si se encontraron libros
                while (resultSet.next()) {
                    // Mostrar la información del libro
                    String titulo = resultSet.getString("titulo");
                    String autor = resultSet.getString("autor");
                    String isbn = resultSet.getString("isbn");
                    String resumen = resultSet.getString("resumen");
                    String fechaPublicacion = resultSet.getString("fecha_publicacion");
                    String generos = resultSet.getString("generos");
    
                    System.out.println("Título: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("ISBN: " + isbn);
                    System.out.println("Resumen: " + resumen);
                    System.out.println("Fecha de Publicación: " + fechaPublicacion);
                    System.out.println("Géneros: " + generos);
                    System.out.println("--------------------");
                }
    
                if (!resultSet.first()) {
                    System.out.println("No se encontraron libros con el título que contiene: " + parteDelTitulo);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
