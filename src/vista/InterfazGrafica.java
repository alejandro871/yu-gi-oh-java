package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import modelo.Carta;
import modelo.CartaMagica;
import modelo.CartaTrampa;
import modelo.Monstruo;
import modelo.Juego;
import modelo.Jugador;

import controlador.Controlador;

public class InterfazGrafica implements Vista {
    private JFrame frame;
    private JTextArea consoleArea;
    private JScrollPane scrollPane;
    private JPanel handPanel;
    private JPanel actionPanel;
    private JPanel fieldPanel;
    private JLabel monsterInfoLabel;
    private JButton[] handButtons;
    private JButton[] actionButtons;
    private CountDownLatch latch;
    private int selectedValue;
    private SwingWorker<Void, Void> gameWorker;
    private volatile boolean gameRunning = false;

    public InterfazGrafica() {
        setupGUI();
        // No llamamos redirectConsoleToGUI() automáticamente para evitar modificar System.out globalmente
    }

    public void iniciarJuegoAsync(Controlador controlador) {
        gameRunning = true;
        gameWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    controlador.iniciarJuego();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                    mostrarMensaje("ERROR: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void done() {
                gameRunning = false;
                mostrarMensaje("=== JUEGO FINALIZADO ===");
            }
        };
        gameWorker.execute();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    private void setupGUI() {
        frame = new JFrame("Yu-Gi-Oh! Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 240, 220));
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 3));

        // Panel lateral derecho - Consola de mensajes
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
        consoleArea.setBackground(new Color(245, 240, 220));
        consoleArea.setForeground(new Color(0, 0, 0));
        consoleArea.setLineWrap(true);
        
        consoleArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(consoleArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(320, 0));
        scrollPane.setBackground(new Color(245, 240, 220));
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 2), "Mensajes"));
        mainPanel.add(scrollPane, BorderLayout.EAST);

        // Panel central - Campo de batalla
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBackground(new Color(245, 240, 220));
        fieldPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 2), "Campo de Batalla"));
        
        // Subpanel para monstruos
        JPanel monstersPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        monstersPanel.setBackground(new Color(245, 240, 220));
        JLabel tuCampoLabel = new JLabel("Tu Campo:");
        JLabel campoEnemigoLabel = new JLabel("Campo Enemigo:");
        tuCampoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        campoEnemigoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tuCampoLabel.setOpaque(true);
        campoEnemigoLabel.setOpaque(true);
        tuCampoLabel.setBackground(new Color(245, 240, 220));
        campoEnemigoLabel.setBackground(new Color(245, 240, 220));
        monstersPanel.add(tuCampoLabel);
        monstersPanel.add(campoEnemigoLabel);
        fieldPanel.add(monstersPanel, BorderLayout.NORTH);
        
        // Panel para información del monstruo seleccionado
        monsterInfoLabel = new JLabel("<html><center>Selecciona un monstruo para ver detalles</center></html>");
        monsterInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monsterInfoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        monsterInfoLabel.setOpaque(true);
        monsterInfoLabel.setBackground(new Color(255, 255, 230));
        monsterInfoLabel.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 2));
        monsterInfoLabel.setMaximumSize(new Dimension(200, 80));
        monsterInfoLabel.setPreferredSize(new Dimension(150, 80));
        fieldPanel.add(monsterInfoLabel, BorderLayout.EAST);
        
        mainPanel.add(fieldPanel, BorderLayout.CENTER);

        // Panel inferior - Mano y acciones
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 2));

        // Panel de acciones
        actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 2), "Acciones"));
        bottomPanel.add(actionPanel, BorderLayout.NORTH);

        // Panel de mano
        handPanel = new JPanel(new FlowLayout());
        handPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 150, 80), 2), "Tu Mano"));
        bottomPanel.add(handPanel, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void redirectConsoleToGUI() {
        // Solo configurar si no se ha configurado antes (evitar sobrescribir múltiples veces)
        if (System.out.getClass().getName().contains("InterfazGrafica")) {
            return; // Ya está configurado
        }

        PrintStream printStream = new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                buffer.append((char) b);
                // Flush en newline para mejor rendimiento
                if (b == '\n') {
                    flushBuffer();
                }
            }

            @Override
            public void flush() {
                flushBuffer();
            }

            private synchronized void flushBuffer() {
                if (buffer.length() > 0) {
                    consoleArea.append(buffer.toString());
                    consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
                    buffer.setLength(0);
                }
            }
        });
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private JButton createCardButton(Carta carta, int index) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(120, 80));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        String tipo = "";
        Color color = Color.GRAY;

        if (carta instanceof Monstruo) {
            Monstruo m = (Monstruo) carta;
            tipo = "MONSTRUO";
            color = Color.RED;
            button.setText("<html><center>" + carta.getNombre() + "<br/>ATK: " + m.getAtk() + "<br/>DEF: " + m.getDef() + "</center></html>");
        } else if (carta instanceof CartaMagica) {
            tipo = "MÁGICA";
            color = Color.BLUE;
            button.setText("<html><center>" + carta.getNombre() + "<br/>Carta Mágica</center></html>");
        } else if (carta instanceof CartaTrampa) {
            tipo = "TRAMPA";
            color = Color.ORANGE;
            button.setText("<html><center>" + carta.getNombre() + "<br/>Carta Trampa</center></html>");
        }

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        // Actualizar detalles al hacer clic
        button.addActionListener(e -> {
            mostrarDetallesCarta(carta);
            selectedValue = index;
            if (latch != null) {
                latch.countDown();
            }
        });

        return button;
    }
    
    private void mostrarDetallesCarta(Carta carta) {
        String detalles;
        
        if (carta instanceof Monstruo) {
            Monstruo m = (Monstruo) carta;
            detalles = "<html><center><b>" + m.getNombre() + "</b><br/>" +
                    "Tipo: Monstruo<br/>" +
                    "Nivel: " + m.getNivel() + "<br/>" +
                    "ATK: " + m.getAtk() + " | DEF: " + m.getDef() + "<br/>" +
                    "Descripción: " + m.getDescripcion() + "</center></html>";
        } else if (carta instanceof CartaMagica) {
            detalles = "<html><center><b>" + carta.getNombre() + "</b><br/>" +
                    "Tipo: Carta Mágica<br/>" +
                    "Descripción: " + carta.getDescripcion() + "</center></html>";
        } else if (carta instanceof CartaTrampa) {
            detalles = "<html><center><b>" + carta.getNombre() + "</b><br/>" +
                    "Tipo: Carta Trampa<br/>" +
                    "Descripción: " + carta.getDescripcion() + "</center></html>";
        } else {
            detalles = "<html><center>" + carta.getNombre() + "</center></html>";
        }
        
        monsterInfoLabel.setText(detalles);
    }

    private JButton createActionButton(String text, int value) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(Color.GREEN);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        button.addActionListener(e -> {
            selectedValue = value;
            if (latch != null) {
                latch.countDown();
            }
        });

        return button;
    }

    @Override
    public void mostrarBienvenida() {
        String mensaje = "=== BIENVENIDO A YU-GI-OH! JAVA ===\n" +
                        "Un juego de cartas estratégico donde dos duelistas se enfrentan\n" +
                        "con monstruos, cartas mágicas y de trampa.\n\n" +
                        "Haz clic en OK para continuar.";
        SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(frame, mensaje, "Yu-Gi-Oh!", JOptionPane.INFORMATION_MESSAGE)
        );
    }

    @Override
    public void mostrarEstadoCompleto(Juego juego) {
        Jugador actual = juego.getJugadorActual();
        Jugador enemigo = juego.getJugadorEnemigo();

        mostrarMensaje("=== ESTADO DEL JUEGO ===");
        mostrarMensaje("Turno actual: " + actual.getNombre());
        mostrarMensaje("");

        // Actualizar panel de campo en el EDT
        SwingUtilities.invokeLater(() -> {
            fieldPanel.removeAll();
            JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
            centerPanel.setBackground(new Color(245, 240, 220));

            centerPanel.add(createPlayerFieldPanel(actual, "🛡️ TU CAMPO", true));
            centerPanel.add(createPlayerFieldPanel(enemigo, "⚔️ CAMPO ENEMIGO", false));

            fieldPanel.add(centerPanel, BorderLayout.CENTER);
            fieldPanel.add(monsterInfoLabel, BorderLayout.EAST);

            fieldPanel.revalidate();
            fieldPanel.repaint();
        });

        mostrarMensaje("");
    }

    private JPanel createPlayerFieldPanel(Jugador jugador, String titulo, boolean esJugadorActual) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        // Calcular color según vida del jugador
        Color vidaColor = Color.GREEN;
        if (jugador.getVida() < 3000) vidaColor = Color.ORANGE;
        if (jugador.getVida() < 1000) vidaColor = Color.RED;
        
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(vidaColor, 2),
            titulo + " - " + jugador.getNombre() + " (❤ " + jugador.getVida() + " LP)"
        );
        panel.setBorder(border);
        panel.setBackground(new Color(245, 240, 220));

        if (!jugador.getCampo().isEmpty()) {
            for (Monstruo m : jugador.getCampo()) {
                JButton monsterButton = new JButton();
                monsterButton.setPreferredSize(new Dimension(110, 70));
                
                // Cambiar color si el monstruo ya atacó
                Color bgColor = m.yaAtaco() ? new Color(180, 100, 100) : Color.RED;
                String atacoMark = m.yaAtaco() ? " ✓" : "";
                
                monsterButton.setText("<html><center>" + m.getNombre() + atacoMark + 
                    "<br/>⚔️ " + m.getAtk() + " 🛡️ " + m.getDef() + "</center></html>");
                monsterButton.setBackground(bgColor);
                monsterButton.setForeground(Color.WHITE);
                monsterButton.setFont(new Font("Arial", Font.BOLD, 9));
                monsterButton.setEnabled(false);
                monsterButton.setBorder(BorderFactory.createRaisedBevelBorder());
                
                panel.add(monsterButton);
            }
        } else {
            JLabel emptyLabel = new JLabel("Sin monstruos en campo");
            emptyLabel.setForeground(Color.LIGHT_GRAY);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            panel.add(emptyLabel);
        }

        return panel;
    }

    @Override
    public void mostrarManoConTipo(Jugador jugador) {
        List<Carta> mano = jugador.getMano();
        final List<Carta> manoFinal = mano;

        SwingUtilities.invokeLater(() -> {
            handPanel.removeAll();

            if (!manoFinal.isEmpty()) {
                handButtons = new JButton[manoFinal.size()];
                for (int i = 0; i < manoFinal.size(); i++) {
                    handButtons[i] = createCardButton(manoFinal.get(i), i + 1);
                    handPanel.add(handButtons[i]);
                }
            } else {
                JLabel emptyLabel = new JLabel("Mano vacía");
                handPanel.add(emptyLabel);
            }

            handPanel.revalidate();
            handPanel.repaint();
        });
    }

    @Override
    public int leerEntero(int min, int max) {
        try {
            if (min == 1 && max == 2) {
                // Caso especial para opciones de acción
                String[] opciones = {"Jugar una carta", "Pasar turno"};
                int result = JOptionPane.showOptionDialog(frame, "¿Qué deseas hacer?", "Yu-Gi-Oh!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                return result + 1; // 0 -> 1, 1 -> 2
            } else if (min == 0 && max > 0) {
                // Caso para selección de cartas (incluye opción 0 para cancelar)
                // Mantener botones en la ventana principal para selección de cartas
                actionPanel.removeAll();
                JButton cancelButton = createActionButton("Cancelar", 0);
                actionPanel.add(cancelButton);
                actionPanel.revalidate();
                actionPanel.repaint();
                // Los botones de cartas ya están creados en mostrarManoConTipo
            }

            latch = new CountDownLatch(1);
            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return min;
            }

            // Limpiar botones después de la selección
            actionPanel.removeAll();
            actionPanel.revalidate();
            actionPanel.repaint();

            return selectedValue;
        } catch (Exception e) {
            mostrarMensaje("ERROR en leerEntero: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(frame, 
                "Error en leerEntero:\n" + e.getClass().getName() + "\n" + e.getMessage(), 
                "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            return min;
        }
    }

@Override
    public void pausar() {
        JOptionPane.showMessageDialog(frame, "Haz clic en OK para continuar", "Pausa", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        final String msg = mensaje;
        SwingUtilities.invokeLater(() -> {
            consoleArea.append(msg + "\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    @Override
    public void mostrarTurno(int numeroTurno, String nombreJugador) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append("=== TURNO " + numeroTurno + " - " + nombreJugador.toUpperCase() + " ===\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    @Override
    public void mostrarFase(String nombreFase) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append("--- " + nombreFase.toUpperCase() + " ---\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    @Override
    public void mostrarOpciones(String[] opciones) {
        StringBuilder sb = new StringBuilder("Opciones disponibles:\n");
        for (int i = 0; i < opciones.length; i++) {
            sb.append("[").append(i + 1).append("] ").append(opciones[i]).append("\n");
        }
        mostrarMensaje(sb.toString());
    }

    @Override
    public void mostrarCartaJugada(String nombreJugador, String nombreCarta) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append("▶ " + nombreJugador + " juega: " + nombreCarta + "\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    @Override
    public void mostrarAtaque(String atacante, String defensor, int dano) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append("⚔ " + atacante + " ataca a " + defensor + " causando " + dano + " puntos de daño\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    @Override
    public void mostrarResultadoAtaque(String resultado) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append("Resultado del ataque: " + resultado + "\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    @Override
    public int seleccionarMonstruoCampo(Jugador jugador, String mensaje) {
        try {
            List<Monstruo> campo = jugador.getCampo();
            if (campo.isEmpty()) {
                mostrarMensaje("No hay monstruos en campo para seleccionar.");
                return 0;
            }

            String[] opciones = new String[campo.size() + 1];
            opciones[0] = "Cancelar";
            for (int i = 0; i < campo.size(); i++) {
                Monstruo m = campo.get(i);
                opciones[i + 1] = m.getNombre() + " (ATK: " + m.getAtk() + ")";
            }

            int result = JOptionPane.showOptionDialog(frame, mensaje, "Yu-Gi-Oh!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (result == 0) {
                return 0; // Cancelar
            } else if (result > 0) {
                return result; // 1-based index
            }
            return 0;
        } catch (Exception e) {
            mostrarMensaje("ERROR en seleccionarMonstruoCampo: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(System.out);
            return 0;
        }
    }

    @Override
    public int seleccionarSacrificio(Jugador jugador, String mensaje) {
        try {
            mostrarMensaje(mensaje);
            mostrarMensaje("  [0] Cancelar (no sacrificar)");

            if (jugador.getCampo().isEmpty()) {
                mostrarMensaje(" No hay monstruos en campo para sacrificar");
                return 0;
            }

            List<Monstruo> campo = jugador.getCampo();
            String[] opciones = new String[campo.size() + 1];
            opciones[0] = "Cancelar";
            for (int i = 0; i < campo.size(); i++) {
                Monstruo m = campo.get(i);
                opciones[i + 1] = m.getNombre() + " (Nivel: " + m.getNivel() + ")";
            }

            int result = JOptionPane.showOptionDialog(frame, mensaje, "Yu-Gi-Oh!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (result == 0) {
                return 0; // Cancelar
            } else if (result > 0) {
                return result; // 1-based index
            }
            return 0;
        } catch (Exception e) {
            mostrarMensaje("ERROR en seleccionarSacrificio: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(System.out);
            return 0;
        }
    }

    @Override
    public void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== FIN DEL JUEGO ===\n");
        sb.append("Turnos jugados: ").append(turnos).append("\n\n");

        if (juego.hayGanador()) {
            Jugador ganador = (j1.getVida() > 0) ? j1 : j2;
            Jugador perdedor = (j1.getVida() > 0) ? j2 : j1;
            sb.append("¡").append(ganador.getNombre()).append(" GANA!\n");
            sb.append(perdedor.getNombre()).append(" ha sido derrotado.\n");
        } else {
            sb.append("El juego terminó en empate.\n");
        }

        sb.append("\n=== ESTADÍSTICAS FINALES ===\n");
        sb.append(j1.getNombre()).append(" - Vida restante: ").append(j1.getVida()).append("\n");
        sb.append(j2.getNombre()).append(" - Vida restante: ").append(j2.getVida());

        JOptionPane.showMessageDialog(frame, sb.toString(), "Yu-Gi-Oh! - Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
    }
}
