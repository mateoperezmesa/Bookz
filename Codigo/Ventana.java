import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {

    public Ventana() {

        // Configurar la ventana
        setTitle("Bienvenido a Bookz!");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        // Parte izquierda con "BOOKZ"
        JLabel labelBookz = new JLabel("BOOKZ");
        labelBookz.setFont(new Font("Arial", Font.BOLD, 24));
        labelBookz.setForeground(new Color(0, 128, 0)); // Verde oscuro
        panelSuperior.add(labelBookz, BorderLayout.WEST);

        // Parte central con botones "Home" y "Buscar"
        JPanel panelBotonesHomeBuscar = new JPanel(new FlowLayout());
        JButton btnHome = new JButton("Home");
        JButton btnBuscar = new JButton("Buscar");
        panelBotonesHomeBuscar.add(btnHome);
        panelBotonesHomeBuscar.add(btnBuscar);
        panelSuperior.add(panelBotonesHomeBuscar, BorderLayout.CENTER);

        // Parte derecha con botones "Iniciar Sesión" y "Registrarse"
        JPanel panelBotonesSesionRegistrarse = new JPanel(new FlowLayout());
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrarse = new JButton("Registrarse");

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de registro al hacer clic en "Registrarse"
                new VentanaRegistro();
            }
        });

        // Estilizar los botones
        estilizarBoton(btnIniciarSesion);
        estilizarBoton(btnRegistrarse);
        panelBotonesSesionRegistrarse.add(btnIniciarSesion);
        panelBotonesSesionRegistrarse.add(btnRegistrarse);
        panelSuperior.add(panelBotonesSesionRegistrarse, BorderLayout.EAST);

        // Agregar el panel superior al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // Panel central con botones de imágenes
        JPanel panelImagenes = new JPanel(new GridLayout(2, 3, 10, 10)); // Puedes ajustar según necesites
        // Crear botones de imágenes (pueden ser JLabels con imágenes, aquí son botones
        // de ejemplo)
        for (int i = 1; i <= 6; i++) {
            JButton btnImagen = new JButton("Libro " + i);
            // Asignar un ActionListener para manejar clics (puedes redirigir a otras
            // ventanas aquí)
            btnImagen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(Ventana.this, "Clic en " + ((JButton) e.getSource()).getText());
                }
            });
            panelImagenes.add(btnImagen);
        }
        // Agregar el panel de imágenes al panel principal
        panelPrincipal.add(panelImagenes, BorderLayout.CENTER);

        // Panel inferior con noticias
        JPanel panelInferior = new JPanel(new BorderLayout());
        JLabel labelNoticias = new JLabel("Noticias acerca del mundo de la literatura");
        JTextArea areaNoticias = new JTextArea(
                "Proyectos como Dracula Daily, una `newsletter` que envía por email la novela de Bram Stoker por orden cronológico y en tiempo real, acercan los clásicos a una nueva generación de lectores que los comentan en redes sociales");
        areaNoticias.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaNoticias);
        panelInferior.add(labelNoticias, BorderLayout.NORTH);
        panelInferior.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel inferior al panel principal
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para estilizar los botones
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(0, 128, 0)); // Verde oscuro
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        /*
         * boton.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * JOptionPane.showMessageDialog(Ventana.this, "Clic en " + boton.getText());
         * }
         * });
         */
    }
}