package vista;

import controlador.Controlador;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        String nombre1 = "";
        String nombre2 = "";

        while (nombre1 == null || nombre1.trim().isEmpty()) {
            nombre1 = JOptionPane.showInputDialog(null,
                "Ingresa el nombre del Duelista 1:",
                "Registro de Duelistas",
                JOptionPane.QUESTION_MESSAGE);
            if (nombre1 == null) return;
            if (nombre1.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (nombre2 == null || nombre2.trim().isEmpty()) {
            nombre2 = JOptionPane.showInputDialog(null,
                "Ingresa el nombre del Duelista 2:",
                "Registro de Duelistas",
                JOptionPane.QUESTION_MESSAGE);
            if (nombre2 == null) return;
            if (nombre2.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        String[] opciones = {"Interfaz Grafica (GUI)", "Consola"};
        int modoSeleccionado = JOptionPane.showOptionDialog(null,
            "Selecciona el modo de juego:",
            "Modo de Juego",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]);

        Vista vista;
        if (modoSeleccionado == 0) {
            vista = new InterfazGrafica();
        } else {
            vista = new Consola();
        }

        Controlador controlador = new Controlador(vista, nombre1, nombre2);
        controlador.iniciarJuego();
    }
}
