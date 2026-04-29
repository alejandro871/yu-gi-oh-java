package vista;

import juego.Juego;
import jugadores.Jugador;

public interface Vista {
    void mostrarBienvenida();
    void mostrarEstadoCompleto(Juego juego);
    void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos);
    void mostrarManoConTipo(Jugador jugador);
    int leerEntero(int min, int max);
    void pausar();

    // Nuevos métodos para mensajes específicos del juego
    void mostrarMensaje(String mensaje);
    void mostrarTurno(int numeroTurno, String nombreJugador);
    void mostrarFase(String nombreFase);
    void mostrarOpciones(String[] opciones);
    void mostrarCartaJugada(String nombreJugador, String nombreCarta);
    void mostrarAtaque(String atacante, String defensor, int dano);
    void mostrarResultadoAtaque(String resultado);
    int seleccionarMonstruoCampo(Jugador jugador, String mensaje);
}
