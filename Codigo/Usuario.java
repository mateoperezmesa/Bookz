import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String correoInstitucional; // Asumiendo que el correo es la clave única
    private String nombreUsuario; // Puede cambiar después del registro
    private String contrasena;
    private Map<String, Integer> votosListas;
    private Map<String, String> resenas;
    private Map<String, ListaLibros> listasCreadas;
    private static Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    public Usuario(String correoInstitucional, String contrasena) {
        this.correoInstitucional = correoInstitucional;
        this.nombreUsuario = extraerUsername(correoInstitucional);
        this.contrasena = contrasena;
        this.votosListas = new HashMap<>();
        this.resenas = new HashMap<>();
        this.listasCreadas = new HashMap<>();
    }

    private String extraerUsername(String correo) {
        // Verificar si el correo tiene el formato correcto
        if (correo.matches("[a-zA-Z0-9]+@unal\\.edu\\.co")) {
            return correo.split("@")[0];
        } else {
            System.out.println("El correo no tiene el formato esperado (username@unal.edu.co).");
            return null;
        }
    }

    public boolean registrar() {
        // Verificar si ya existe un usuario con el mismo correo
        if (usuariosRegistrados.containsKey(correoInstitucional)) {
            System.out.println("Ya existe un usuario registrado con este correo.");
            return false;
        }
        // Almacenar el nuevo usuario en memoria
        usuariosRegistrados.put(correoInstitucional, this);

        System.out.println("Registro exitoso para " + correoInstitucional);
        return true;
    }

    public boolean iniciarSesion(String contrasena) {
    }

    public void agregarResena(String tituloLibro, String resena) {
        resenas.put(tituloLibro, resena);
        System.out.println("Reseña agregada con éxito.");
    }

    public void votarPorLista(String nombreLista, int calificacion) {
        votosListas.put(nombreLista, calificacion);
        System.out.println("Voto registrado con éxito.");
    }

    public void crearLista(String nombreLista, ListaLibros lista) {
        listasCreadas.put(nombreLista, lista);
        System.out.println("Lista creada con éxito.");
    }

    // Getters y Setters necesarios
}