package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class TrampaBloqueo implements Efecto {

    // Trampa que bloquea el ataque del monstruo

    @Override
    public void activar(Jugador jugador) {
        System.out.println(" ¡Trampa Bloqueo activada! Los ataques son bloqueados este turno");
    }

    @Override
    public void activar(Monstruo Monstruo) {
        System.out.println(" ¡Trampa Bloqueo activada! El ataque de " + Monstruo.getNombre() + " es bloqueado");
    }
}
