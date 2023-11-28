public class Conexion {
    private static GestorBaseDatos gestorBD;

    static {
        // Configuración de la conexión a la base de datos
        String dbURL = "jdbc:mysql://localhost:3306/bookz";
        String username = "root";
        String password = "1234";
        gestorBD = new GestorBaseDatos(dbURL, username, password);
    }

    public static GestorBaseDatos obtenerGestorBD() {
        return gestorBD;
    }
}