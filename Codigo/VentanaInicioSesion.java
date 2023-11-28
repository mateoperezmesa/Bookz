import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicioSesion extends JFrame {

    private GestorBaseDatos gestorBD;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public VentanaInicioSesion() {
        // Configurar el gestorBD
        gestorBD = Conexion.obtenerGestorBD();

        // Configurar la ventana de inicio de sesión
        setTitle("Iniciar Sesión - Bienvenido a Bookz!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al presionar cerrar
        setLocationRelativeTo(null);

        // Crear un panel para el formulario de inicio de sesión
        JPanel panelInicioSesion = new JPanel(new GridLayout(3, 2, 10, 10));

        // Agregar componentes al panel de inicio de sesión
        panelInicioSesion.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelInicioSesion.add(txtUsuario);
        panelInicioSesion.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelInicioSesion.add(txtContrasena);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        panelInicioSesion.add(btnIniciarSesion);

        // Configurar acción del botón de inicio de sesión
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de inicio de sesión aquí
                String correo = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Verificar en la base de datos
                if (gestorBD.verificarCredenciales(correo, contrasena)) {
                    // Usuario autenticado correctamente
                    // Establecer el estado de sesión como true (o cualquier otro valor que desees)
                    Sesion.setEstadoSesion(true);
                    JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Inicio de sesión exitoso");
                    // Aquí puedes cerrar la ventana actual si es necesario
                    dispose();
                } else {
                    // Usuario o contraseña incorrectos
                    JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Usuario o contraseña incorrectos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar el panel de inicio de sesión a la ventana
        add(panelInicioSesion);

        // Hacer visible la ventana de inicio de sesión
        setVisible(true);
    }
}
