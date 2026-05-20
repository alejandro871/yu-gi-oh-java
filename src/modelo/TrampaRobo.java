package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaRobo implements Efecto {

    // Trampa que roba cartas del oponente

    @Override
    public void activar(Jugador jugador) {
        if (!jugador.getMano().isEmpty()) {
            jugador.removerCartaMano(0);
            Mensajero.add(" ¡Trampa Robo activada! Una carta del oponente es descartada");
        }
    }

    @Override
    public void activar(Monstruo Monstruo) {
        // No aplica directamente al monstruo
    }

    @Override
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        // Mantener comportamiento original: descartar una carta (del jugador que plays la trampa)
        if (!jugador.getMano().isEmpty()) {
            jugador.removerCartaMano(0);
            Mensajero.add(" ¡Trampa Robo activada! Una carta es descartada");
        }
    }
}
