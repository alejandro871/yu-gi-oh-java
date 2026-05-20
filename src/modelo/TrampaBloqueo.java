package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaBloqueo implements Efecto {

    @Override
    public void activar(Jugador jugador) {
        Mensajero.add(" ¡Trampa Bloqueo activada! Los ataques son bloqueados este turno");
        // Marcar que el oponente no puede atacar
    }

    @Override
    public void activar(Monstruo monstruo) {
        Mensajero.add(" ¡Trampa Bloqueo activada! El ataque de " + monstruo.getNombre() + " es bloqueado");
        // Reducir ATK a 0 para bloquear
        monstruo.setAtk(0);
    }

    @Override
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        Mensajero.add(" ¡Trampa Bloqueo activada! El ataque de " + atacante.getNombre() + " es bloqueado");
        // Bloquear el ataque reduciendo ATK a 0
        atacante.setAtk(0);
        Mensajero.add("  El ataque fue bloqueado! No se inflict daño.");
    }
}
