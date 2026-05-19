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
        // El monstruo atacante será destruido por la lógica del juego
        Mensajero.add(" ¡Trampa Destructor activada! " + monstruo.getNombre() + " será destruido");
        // Marcar el monstruo para destruir
        monstruo.setAtk(0);
        monstruo.setDef(0);
    }
}
