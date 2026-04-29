package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class TrampaRecuperacion implements Efecto {

    // Trampa que recupera un monstruo del cementerio (simulado)

    @Override
    public void activar(Jugador jugador) {
        System.out.println(" ¡Trampa Recuperación activada! Se recuperan 500 LP por protección");
        int vidaActual = jugador.getVida();
        jugador.setVida(vidaActual + 500);
    }

    @Override
    public void activar(Monstruo Monstruo) {
        // No aplica
    }
}
