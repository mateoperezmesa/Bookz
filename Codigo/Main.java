import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        GestorBaseDatos gestorBD = Conexion.obtenerGestorBD();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana();
            }
        });

        // Aquí puedes cambiar el username y la contraseña según tus necesidades
        System.out.println("Usuario con @unal.edu.co");
        // String correo = scan.nextLine();
        // String nuevoUsuario = Usuario.extraerUsername(correo);

        /*
         * while (nuevoUsuario == null) {
         * correo = scan.nextLine();
         * nuevoUsuario = Usuario.extraerUsername(correo);
         * }
         */

        System.out.println("Contraseña");
        // String contrasena = scan.nextLine();

        // Registrar un nuevo usuario
        // gestorBD.registrarUsuario(nuevoUsuario, contrasena);
        // gestorBD.obtenerInformacionUsuario(nuevoUsuario);
        // gestorBD.mostrarlibros();
        gestorBD.obtenerInformacionLibro("n");

        scan.close();
    }
}
