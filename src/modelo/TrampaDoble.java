package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaDoble implements Efecto {

    // Trampa que duplica temporalmente la DEF de un monstruo

    @Override
    public void activar(Jugador jugador) {
        // No aplica
    }

    @Override
    public void activar(Monstruo Monstruo) {
        int defActual = Monstruo.getDef();
        int nuevaDef = defActual * 2;
        Monstruo.setDef(nuevaDef);
        Mensajero.add(" ¡Trampa Doble activada! DEF se duplica a " + nuevaDef);
    }
}
