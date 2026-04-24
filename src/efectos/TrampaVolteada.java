package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class TrampaVolteada implements Efecto {

    // Trampa que voltea la posición de un monstruo

    @Override
    public void activar(Jugador jugador) {
        // No aplica
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        mounstruo.cambiarPosicion();
        System.out.println(" ¡Trampa Volteada activada! " + mounstruo.getNombre() + " cambia de posición");
    }
}
