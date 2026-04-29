package vista;

import datos.GestorEstadisticas;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuPrincipal extends JFrame {
    private JButton botonNuevaPartida;
    private JButton botonEstadisticas;
    private JButton botonSalir;
    private JLabel labelBienvenida;
    private JLabel labelUsuario;
    private Usuario usuario;
    private GestorEstadisticas gestorStats;

    public MenuPrincipal(Usuario usuario, GestorEstadisticas gestorStats) {
        this.usuario = usuario;
        this.gestorStats = gestorStats;
        initComponents();
    }

    private void initComponents() {
        setTitle("Yu-Gi-Oh! Java - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo
        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelFondo.setBackground(new Color(30, 30, 50));

        // Panel superior - Bienvenida
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setOpaque(false);
        
        labelBienvenida = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 32));
        labelBienvenida.setForeground(new Color(255, 215, 0));
        
        labelUsuario = new JLabel("Duelista: " + usuario.getNombre(), SwingConstants.CENTER);
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        labelUsuario.setForeground(Color.WHITE);
        
        panelSuperior.add(labelBienvenida);
        panelSuperior.add(labelUsuario);
        panelFondo.add(panelSuperior, BorderLayout.NORTH);

        // Panel central - Botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        botonNuevaPartida = crearBoton("NUEVA PARTIDA", new Color(0, 150, 0));
        botonNuevaPartida.addActionListener(e -> iniciarNuevaPartida());

        botonEstadisticas = crearBoton("ESTADÍSTICAS", new Color(0, 100, 180));
        botonEstadisticas.addActionListener(e -> mostrarEstadisticas());

        botonSalir = crearBoton("SALIR", new Color(150, 50, 50));
        botonSalir.addActionListener(e -> salir());

        panelBotones.add(botonNuevaPartida);
        panelBotones.add(botonEstadisticas);
        panelBotones.add(botonSalir);

        panelFondo.add(panelBotones, BorderLayout.CENTER);

        add(panelFondo);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 50));
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        return boton;
    }

    private void iniciarNuevaPartida() {
        this.dispose();
        SelectorModo selector = new SelectorModo(usuario, gestorStats);
        selector.setVisible(true);
    }

    private void mostrarEstadisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTADÍSTICAS DE ").append(usuario.getNombre()).append(" ===\n\n");
        sb.append("Partidas jugadas: ").append(usuario.getTotalPartidas()).append("\n");
        sb.append("Partidas ganadas: ").append(usuario.getPartidasGanadas()).append("\n");
        sb.append("Partidas perdidas: ").append(usuario.getPartidasPerdidas()).append("\n");
        sb.append("Victorias: ").append(String.format("%.1f", usuario.getPorcentajeVictorias())).append("%\n\n");
        
        sb.append("Veces como Jugador 1: ").append(usuario.getVecesJugador1()).append("\n");
        sb.append("Veces como Jugador 2: ").append(usuario.getVecesJugador2()).append("\n");

        // Top 5 global
        List<Usuario> top = gestorStats.getTop5Jugadores();
        if (!top.isEmpty()) {
            sb.append("\n=== TOP 5 JUGADORES ===\n");
            for (int i = 0; i < top.size(); i++) {
                Usuario u = top.get(i);
                sb.append((i + 1)).append(". ").append(u.getNombre())
                  .append(" - ").append(u.getPartidasGanadas()).append(" victorias\n");
            }
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro que quieres salir?", 
            "Salir", 
            JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}