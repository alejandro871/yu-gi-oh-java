package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

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
        System.out.println(" ¡Trampa Doble activada! DEF se duplica a " + nuevaDef);
    }
}
