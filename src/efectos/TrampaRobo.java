package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class TrampaRobo implements Efecto {

    // Trampa que roba cartas del oponente

    @Override
    public void activar(Jugador jugador) {
        if (!jugador.getMano().isEmpty()) {
            jugador.getMano().remove(0);
            System.out.println(" ¡Trampa Robo activada! Una carta del oponente es descartada");
        }
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        // No aplica directamente al monstruo
    }
}
