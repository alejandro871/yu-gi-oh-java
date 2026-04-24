package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class TrampaDoble implements Efecto {

    // Trampa que duplica temporalmente la DEF de un monstruo

    @Override
    public void activar(Jugador jugador) {
        // No aplica
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        int defActual = mounstruo.getDef();
        int nuevaDef = defActual * 2;
        mounstruo.setDef(nuevaDef);
        System.out.println(" ¡Trampa Doble activada! DEF se duplica a " + nuevaDef);
    }
}
