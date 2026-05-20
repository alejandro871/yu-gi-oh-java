package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaDestructor implements Efecto {

    @Override
    public void activar(Jugador jugador) {
        // No aplica directamente
    }

    @Override
    public void activar(Monstruo monstruo) {
        Mensajero.add(" ¡Trampa Destructor activada! " + monstruo.getNombre() + " será destruido");
        monstruo.setAtk(0);
        monstruo.setDef(0);
    }

    @Override
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        Mensajero.add(" ¡Trampa Destructor activada! " + atacante.getNombre() + " será destruido");
        oponente.eliminarMonstruo(atacante);
    }
}
