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

import cartas.Carta;
import cartas.CartaMagica;
import cartas.CartaTrampa;
import cartas.Monstruo;
import juego.Juego;
import jugadores.Jugador;

public class InterfazGrafica implements Vista {
    private JFrame frame;
    private JTextArea consoleArea;
    private JScrollPane scrollPane;
    private JPanel handPanel;
    private JPanel actionPanel;
    private JPanel fieldPanel;
    private JButton[] handButtons;
    private JButton[] actionButtons;
    private CountDownLatch latch;
    private int selectedValue;

    public InterfazGrafica() {
        setupGUI();
        redirectConsoleToGUI();
    }

    private void setupGUI() {
        frame = new JFrame("Yu-Gi-Oh! Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel superior - Consola
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        consoleArea.setBackground(Color.BLACK);
        consoleArea.setForeground(Color.GREEN);
        consoleArea.setRows(10);

        scrollPane = new JScrollPane(consoleArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.NORTH);

        // Panel central - Campo de batalla
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2, 1));
        fieldPanel.setBorder(BorderFactory.createTitledBorder("Campo de Batalla"));
        frame.add(fieldPanel, BorderLayout.CENTER);

        // Panel inferior - Mano y acciones
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Panel de acciones
        actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBorder(BorderFactory.createTitledBorder("Acciones"));
        bottomPanel.add(actionPanel, BorderLayout.NORTH);

        // Panel de mano
        handPanel = new JPanel(new FlowLayout());
        handPanel.setBorder(BorderFactory.createTitledBorder("Tu Mano"));
        bottomPanel.add(handPanel, BorderLayout.CENTER);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void redirectConsoleToGUI() {
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                consoleArea.append(String.valueOf((char) b));
                consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
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
        String descripcion = "";

        if (carta instanceof Monstruo) {
            Monstruo m = (Monstruo) carta;
            tipo = "MONSTRUO";
            color = Color.RED;
            descripcion = "ATK: " + m.getAtk() + "   DEF: " + m.getDef();
        } else if (carta instanceof CartaMagica) {
            tipo = "MÁGICA";
            color = Color.BLUE;
            descripcion = "Activar efecto";
        } else if (carta instanceof CartaTrampa) {
            tipo = "TRAMPA";
            color = Color.ORANGE;
            descripcion = "Poner en campo";
        }

        button.setText("<html><center>" + carta.getNombre() + "<br/><b>" + tipo + "</b><br/>" + descripcion + "</center></html>");
        button.setToolTipText("Selecciona esta carta para jugarla");
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        button.addActionListener(e -> {
            selectedValue = index;
            if (latch != null) {
                latch.countDown();
            }
        });

        return button;
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
        mostrarMensaje("=== BIENVENIDO A YU-GI-OH! JAVA ===");
        mostrarMensaje("Un juego de cartas estratégico donde dos duelistas se enfrentan");
        mostrarMensaje("con monstruos, cartas mágicas y de trampa.");
        mostrarMensaje("");
    }

    @Override
    public void mostrarEstadoCompleto(Juego juego) {
        Jugador actual = juego.getJugadorActual();
        Jugador enemigo = juego.getJugadorEnemigo();

        mostrarMensaje("=== ESTADO DEL JUEGO ===");
        mostrarMensaje("Turno actual: " + actual.getNombre());
        mostrarMensaje("");

        // Actualizar panel de campo
        fieldPanel.removeAll();
        fieldPanel.add(createPlayerFieldPanel(actual, "TU CAMPO"));
        fieldPanel.add(createPlayerFieldPanel(enemigo, "CAMPO ENEMIGO"));
        fieldPanel.revalidate();
        fieldPanel.repaint();

        mostrarMensaje("");
    }

    private JPanel createPlayerFieldPanel(Jugador jugador, String titulo) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo + " - " + jugador.getNombre() + " (Vida: " + jugador.getVida() + ")"));

        if (!jugador.getCampo().isEmpty()) {
            for (Monstruo m : jugador.getCampo()) {
                JButton monsterButton = new JButton();
                monsterButton.setPreferredSize(new Dimension(100, 60));
                monsterButton.setText("<html><center>" + m.getNombre() + "<br/>ATK: " + m.getAtk() + "</center></html>");
                monsterButton.setBackground(Color.RED);
                monsterButton.setForeground(Color.WHITE);
                monsterButton.setEnabled(false); // Solo mostrar, no interactuar
                panel.add(monsterButton);
            }
        } else {
            JLabel emptyLabel = new JLabel("Sin monstruos en campo");
            panel.add(emptyLabel);
        }

        return panel;
    }

    @Override
    public void mostrarManoConTipo(Jugador jugador) {
        handPanel.removeAll();
        List<Carta> mano = jugador.getMano();

        if (!mano.isEmpty()) {
            handButtons = new JButton[mano.size()];
            for (int i = 0; i < mano.size(); i++) {
                handButtons[i] = createCardButton(mano.get(i), i + 1);
                handPanel.add(handButtons[i]);
            }
        } else {
            JLabel emptyLabel = new JLabel("Mano vacía");
            handPanel.add(emptyLabel);
        }

        handPanel.revalidate();
        handPanel.repaint();
    }

    @Override
    public int leerEntero(int min, int max) {
        actionPanel.removeAll();

        if (min >= 1 && max >= min) {
            int count = max - min + 1;
            actionButtons = new JButton[count];
            for (int i = 0; i < count; i++) {
                int value = min + i;
                actionButtons[i] = createActionButton("Opción " + value, value);
                actionPanel.add(actionButtons[i]);
            }
            actionPanel.revalidate();
            actionPanel.repaint();
        } else if (min == 0 && max > 0) {
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
    }

    @Override
    public void pausar() {
        JOptionPane.showMessageDialog(frame, "Haz clic en OK para continuar", "Pausa", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void mostrarTurno(int numeroTurno, String nombreJugador) {
        mostrarMensaje("=== TURNO " + numeroTurno + " - " + nombreJugador.toUpperCase() + " ===");
    }

    @Override
    public void mostrarFase(String nombreFase) {
        mostrarMensaje("--- " + nombreFase.toUpperCase() + " ---");
    }

    @Override
    public void mostrarOpciones(String[] opciones) {
        // Este método se maneja ahora con botones en leerEntero
        StringBuilder sb = new StringBuilder("Opciones disponibles:\n");
        for (int i = 0; i < opciones.length; i++) {
            sb.append("[").append(i + 1).append("] ").append(opciones[i]).append("\n");
        }
        mostrarMensaje(sb.toString());
    }

    @Override
    public void mostrarCartaJugada(String nombreJugador, String nombreCarta) {
        mostrarMensaje("▶ " + nombreJugador + " juega: " + nombreCarta);
    }

    @Override
    public void mostrarAtaque(String atacante, String defensor, int dano) {
        mostrarMensaje("⚔ " + atacante + " ataca a " + defensor + " causando " + dano + " puntos de daño");
    }

    @Override
    public void mostrarResultadoAtaque(String resultado) {
        mostrarMensaje("Resultado del ataque: " + resultado);
    }

    @Override
    public int seleccionarMonstruoCampo(Jugador jugador, String mensaje) {
        mostrarMensaje(mensaje);
        mostrarMensaje("  [0] Cancelar");

        // Crear botones para los monstruos del campo
        actionPanel.removeAll();
        JButton cancelButton = createActionButton("Cancelar", 0);
        actionPanel.add(cancelButton);

        // Crear botones para cada monstruo
        List<Monstruo> campo = jugador.getCampo();
        JButton[] monsterButtons = new JButton[campo.size()];
        for (int i = 0; i < campo.size(); i++) {
            Monstruo m = campo.get(i);
            JButton monsterButton = new JButton();
            monsterButton.setPreferredSize(new Dimension(120, 40));
            monsterButton.setText("<html><center>" + m.getNombre() + "<br/>ATK: " + m.getAtk() + "</center></html>");
            monsterButton.setBackground(Color.RED);
            monsterButton.setForeground(Color.WHITE);

            final int index = i + 1;
            monsterButton.addActionListener(e -> {
                selectedValue = index;
                if (latch != null) {
                    latch.countDown();
                }
            });

            monsterButtons[i] = monsterButton;
            actionPanel.add(monsterButton);
        }

        actionPanel.revalidate();
        actionPanel.repaint();

        latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        }

        // Limpiar botones después de la selección
        actionPanel.removeAll();
        actionPanel.revalidate();
        actionPanel.repaint();

        return selectedValue;
    }

    @Override
    public int seleccionarTrampaEnCampo(Jugador jugador, String mensaje) {
        mostrarMensaje(mensaje);
        mostrarMensaje("  [0] Cancelar");

        actionPanel.removeAll();
        JButton cancelButton = createActionButton("Cancelar", 0);
        actionPanel.add(cancelButton);

        List<CartaTrampa> trampas = jugador.getTrampasEnCampo();
        JButton[] trapButtons = new JButton[trampas.size()];
        for (int i = 0; i < trampas.size(); i++) {
            CartaTrampa t = trampas.get(i);
            JButton trapButton = new JButton();
            trapButton.setPreferredSize(new Dimension(140, 40));
            trapButton.setText("<html><center>" + t.getNombre() + "</center></html>");
            trapButton.setBackground(Color.ORANGE);
            trapButton.setForeground(Color.BLACK);

            final int index = i + 1;
            trapButton.addActionListener(e -> {
                selectedValue = index;
                if (latch != null) {
                    latch.countDown();
                }
            });

            trapButtons[i] = trapButton;
            actionPanel.add(trapButton);
        }

        actionPanel.revalidate();
        actionPanel.repaint();

        latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        }

        actionPanel.removeAll();
        actionPanel.revalidate();
        actionPanel.repaint();

        return selectedValue;
    }

    @Override
    public void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos) {
        mostrarMensaje("=== FIN DEL JUEGO ===");
        mostrarMensaje("Turnos jugados: " + turnos);

        if (juego.hayGanador()) {
            Jugador ganador = (j1.getVida() > 0) ? j1 : j2;
            Jugador perdedor = (j1.getVida() > 0) ? j2 : j1;
            mostrarMensaje("¡" + ganador.getNombre() + " GANA!");
            mostrarMensaje(perdedor.getNombre() + " ha sido derrotado.");
        } else {
            mostrarMensaje("El juego terminó en empate.");
        }

        mostrarMensaje("");
        mostrarMensaje("=== ESTADÍSTICAS FINALES ===");
        mostrarMensaje(j1.getNombre() + " - Vida restante: " + j1.getVida());
        mostrarMensaje(j2.getNombre() + " - Vida restante: " + j2.getVida());
    }
}
