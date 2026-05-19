package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaVolteada implements Efecto {

    // Trampa que voltea la posición de un monstruo

    @Override
    public void activar(Jugador jugador) {
        // No aplica
    }

    @Override
    public void activar(Monstruo Monstruo) {
        Monstruo.cambiarPosicion();
        Mensajero.add(" ¡Trampa Volteada activada! " + Monstruo.getNombre() + " cambia de posición");
    }
}
