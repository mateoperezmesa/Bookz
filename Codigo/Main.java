import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Ejemplo de uso
        Scanner scan = new Scanner(System.in);
        String nuevoUsuario = scan.nextLine();
        String contrasena = scan.nextLine();

        if (GestorBaseDatos.usuarioDisponible(nuevoUsuario)) {
            GestorBaseDatos.registrarUsuario(nuevoUsuario, contrasena);
            System.out.println("Registro exitoso para " + nuevoUsuario);
        } else {
            System.out.println("El username no está disponible.");
        }

        scan.close();
    }
}
