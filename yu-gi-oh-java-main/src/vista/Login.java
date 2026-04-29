package vista;

import datos.GestorEstadisticas;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField campoNombre;
    private JButton botonEntrar;
    private JLabel labelTitulo;
    private JLabel labelNombre;
    private GestorEstadisticas gestorStats;
    private Usuario usuarioActual;

    public Login() {
        gestorStats = new GestorEstadisticas();
        initComponents();
    }

    private void initComponents() {
        setTitle("Yu-Gi-Oh! Java - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(30, 30, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        labelTitulo = new JLabel("YU-GI-OH! JAVA");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        labelTitulo.setForeground(new Color(255, 215, 0)); // Dorado
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(labelTitulo, gbc);

        // Subtítulo
        JLabel labelSubtitulo = new JLabel("¡Bienvenido, Duelista!");
        labelSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        labelSubtitulo.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 20, 10);
        panelPrincipal.add(labelSubtitulo, gbc);

        // Label nombre
        labelNombre = new JLabel("Nombre de Duelista:");
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelPrincipal.add(labelNombre, gbc);

        // Campo de texto
        campoNombre = new JTextField(20);
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        campoNombre.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelPrincipal.add(campoNombre, gbc);

        // Botón entrar
        botonEntrar = new JButton("ENTRAR AL JUEGO");
        botonEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        botonEntrar.setBackground(new Color(255, 215, 0));
        botonEntrar.setForeground(Color.BLACK);
        botonEntrar.setFocusPainted(false);
        botonEntrar.setPreferredSize(new Dimension(200, 40));
        botonEntrar.addActionListener(e -> procesarLogin());
        botonEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    procesarLogin();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        panelPrincipal.add(botonEntrar, gbc);

        add(panelPrincipal);
    }

    private void procesarLogin() {
        String nombre = campoNombre.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingresa tu nombre de duelista", 
                "Nombre requerido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nombre.length() > 20) {
            JOptionPane.showMessageDialog(this, 
                "El nombre no puede tener más de 20 caracteres", 
                "Nombre muy largo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener o crear usuario
        usuarioActual = gestorStats.getOrCrearUsuario(nombre);
        
        // Mostrar menú principal
        this.dispose();
        MenuPrincipal menu = new MenuPrincipal(usuarioActual, gestorStats);
        menu.setVisible(true);
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}