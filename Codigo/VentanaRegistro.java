import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {

    private GestorBaseDatos gestorBD;
    private JTextField txtUsuario; // Declarar txtUsuario como un campo de clase
    private JLabel lblEstadoUsuario;

    public VentanaRegistro() {
        // Configurar el gestorBD
        String dbURL = "jdbc:mysql://localhost:3306/bookz";
        String username = "root";
        String password = "1234";
        gestorBD = new GestorBaseDatos(dbURL, username, password);

        // Configurar la ventana de registro
        setTitle("Registro - Bienvenido a Bookz!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al presionar cerrar
        setLocationRelativeTo(null);

        // Crear un panel para el formulario de registro
        JPanel panelRegistro = new JPanel(new GridLayout(4, 2, 10, 10)); // Aumenté la cantidad de filas para agregar
                                                                         // espacio para el nuevo componente

        // Agregar componentes al panel de registro
        panelRegistro.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(); // Ahora es un campo de clase
        panelRegistro.add(txtUsuario);
        panelRegistro.add(new JLabel("Contraseña:"));
        JPasswordField txtContrasena = new JPasswordField();
        panelRegistro.add(txtContrasena);

        // Nuevo componente para la validación del usuario en tiempo real
        lblEstadoUsuario = new JLabel();
        panelRegistro.add(new JLabel("Estado del Usuario:"));
        panelRegistro.add(lblEstadoUsuario);

        JButton btnRegistrar = new JButton("Registrar");
        panelRegistro.add(btnRegistrar);

        // Agregar un DocumentListener al campo de texto del usuario para validar en
        // tiempo real
        txtUsuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarUsuario();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarUsuario();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarUsuario();
            }
        });

        // Configurar acción del botón de registro
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de registro aquí
                String correo = txtUsuario.getText();
                String nuevoUsuario = Usuario.extraerUsername(correo);

                if (nuevoUsuario == null) {
                    JOptionPane.showMessageDialog(VentanaRegistro.this, "Ingrese un usuario válido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String contrasena = new String(txtContrasena.getPassword());
                    // Llama a tu lógica de registro aquí con los datos ingresados
                    gestorBD.registrarUsuario(nuevoUsuario, contrasena);
                }
            }
        });

        // Agregar el panel de registro a la ventana de registro
        add(panelRegistro);

        // Hacer visible la ventana de registro
        setVisible(true);
    }

    private void validarUsuario() {
        String usuario = txtUsuario.getText();

        if (usuario.isEmpty()) {
            lblEstadoUsuario.setText(""); // Usuario vacío
            lblEstadoUsuario.setForeground(Color.BLACK); // Puedes cambiar el color aquí
        } else if (usuario.matches("[a-zA-Z0-9]+@unal\\.edu\\.co")) {
            lblEstadoUsuario.setText("Usuario correcto");
            lblEstadoUsuario.setForeground(new Color(0, 128, 0)); // Verde oscuro
        } else {
            lblEstadoUsuario.setText("Usuario incorrecto");
            lblEstadoUsuario.setForeground(Color.RED); // Rojo
        }
    }

}
