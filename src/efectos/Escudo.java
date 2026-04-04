package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class Escudo implements Efecto {

    private int aumentoDef;

    public Escudo(int aumentoDef){
        this.aumentoDef = aumentoDef;
    }

    @Override
    public void activar(Jugador jugador) {
        // no aplica en este caso
    }

    @Override
    public void activar(Mounstruo mounstruo) {

        int defActual = mounstruo.getDef();
        int nuevaDef = defActual + aumentoDef;

        mounstruo.setDef(nuevaDef);

        System.out.println(" La defensa del monstruo aumenta de " + defActual + " a " + nuevaDef);
    }
}