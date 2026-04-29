package vista;

import datos.GestorEstadisticas;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;

public class SelectorModo extends JFrame {
    private JButton botonGUI;
    private JButton botonConsola;
    private JButton botonVolver;
    private Usuario usuario;
    private GestorEstadisticas gestorStats;
    private String nombreOponente;

    public SelectorModo(Usuario usuario, GestorEstadisticas gestorStats) {
        this.usuario = usuario;
        this.gestorStats = gestorStats;
        initComponents();
    }

    private void initComponents() {
        setTitle("Yu-Gi-Oh! Java - Selector de Modo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelFondo.setBackground(new Color(30, 30, 50));

        // Título
        JLabel labelTitulo = new JLabel("SELECCIONA EL MODO", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        labelTitulo.setForeground(new Color(255, 215, 0));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelFondo.add(labelTitulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        botonGUI = crearBoton("MODO GRÁFICO (GUI)", new Color(100, 50, 150));
        botonGUI.addActionListener(e -> iniciarGUI());

        botonConsola = crearBoton("MODO CONSOLA", new Color(80, 80, 80));
        botonConsola.addActionListener(e -> iniciarConsola());

        botonVolver = crearBoton("VOLVER", new Color(150, 50, 50));
        botonVolver.addActionListener(e -> volver());

        panelBotones.add(botonGUI);
        panelBotones.add(botonConsola);
        panelBotones.add(botonVolver);

        panelFondo.add(panelBotones, BorderLayout.CENTER);

        // Info
        JLabel labelInfo = new JLabel("Jugando como: " + usuario.getNombre(), SwingConstants.CENTER);
        labelInfo.setForeground(Color.LIGHT_GRAY);
        labelInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFondo.add(labelInfo, BorderLayout.SOUTH);

        add(panelFondo);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 50));
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        return boton;
    }

    private String pedirNombreOponente() {
        String nombre = JOptionPane.showInputDialog(this, 
            "Ingresa el nombre del oponente:", 
            "Duelista 2", 
            JOptionPane.QUESTION_MESSAGE);
        return (nombre == null || nombre.trim().isEmpty()) ? "Duelista 2" : nombre.trim();
    }

    private void iniciarGUI() {
        nombreOponente = pedirNombreOponente();
        this.dispose();
        
        // Iniciar vista/App.java con argumentos
        vista.App.main(new String[]{usuario.getNombre(), nombreOponente});
    }

    private void iniciarConsola() {
        nombreOponente = pedirNombreOponente();
        this.dispose();
        
        // Iniciar App.java (consola) con argumentos
        App.main(new String[]{usuario.getNombre(), nombreOponente});
    }

    private void volver() {
        this.dispose();
        MenuPrincipal menu = new MenuPrincipal(usuario, gestorStats);
        menu.setVisible(true);
    }
}