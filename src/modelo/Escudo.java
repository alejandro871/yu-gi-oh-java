package modelo;

import modelo.Monstruo;
import modelo.Jugador;

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
    public void activar(Monstruo Monstruo) {

        int defActual = Monstruo.getDef();
        int nuevaDef = defActual + aumentoDef;

        Monstruo.setDef(nuevaDef);

        Mensajero.add(" La defensa del monstruo aumenta de " + defActual + " a " + nuevaDef);
    }
}
