package modelo.efectos;

import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class Escudo implements Efecto {

    private int aumentoDef;

    public Escudo(int aumentoDef){
        this.aumentoDef = aumentoDef;
    }

    @Override
    public void activar(Jugador jugador) {
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        int defActual = mounstruo.getDef();
        int nuevaDef = defActual + aumentoDef;
        mounstruo.setDef(nuevaDef);
        Mensajes.agregar(" La defensa del monstruo aumenta de " + defActual + " a " + nuevaDef);
    }
}
