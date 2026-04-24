package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class TrampaDefensa implements Efecto {

    private int aumentoDef;

    public TrampaDefensa(int aumentoDef) {
        this.aumentoDef = aumentoDef;
    }

    @Override
    public void activar(Jugador jugador) {
        // No aplica para jugador
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        int defActual = mounstruo.getDef();
        int nuevaDef = defActual + aumentoDef;
        mounstruo.setDef(nuevaDef);
        System.out.println(" ¡Trampa Defensa activada! DEF aumenta de " + defActual + " a " + nuevaDef);
    }
}
