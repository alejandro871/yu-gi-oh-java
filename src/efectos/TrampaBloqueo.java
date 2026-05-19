package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class TrampaBloqueo implements Efecto {

    @Override
    public void activar(Jugador jugador) {
        System.out.println(" ¡Trampa Bloqueo activada! Los ataques son bloqueados este turno");
        // Marcar que el oponente no puede atacar
    }

    @Override
    public void activar(Monstruo monstruo) {
        System.out.println(" ¡Trampa Bloqueo activada! El ataque de " + monstruo.getNombre() + " es bloqueado");
        // Reducir ATK a 0 para bloquear
        monstruo.setAtk(0);
    }
}
