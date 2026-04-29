package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class TrampaVolteada implements Efecto {

    // Trampa que voltea la posición de un monstruo

    @Override
    public void activar(Jugador jugador) {
        // No aplica
    }

    @Override
    public void activar(Monstruo Monstruo) {
        Monstruo.cambiarPosicion();
        System.out.println(" ¡Trampa Volteada activada! " + Monstruo.getNombre() + " cambia de posición");
    }
}
