package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaDefensa implements Efecto {

    private int aumentoDef;

    public TrampaDefensa(int aumentoDef) {
        this.aumentoDef = aumentoDef;
    }

    @Override
    public void activar(Jugador jugador) {
        if (!jugador.getCampo().isEmpty()) {
            Monstruo m = jugador.getCampo().get(0);
            int defActual = m.getDef();
            m.setDef(defActual + aumentoDef);
            Mensajero.add(" Trampa Defensa activada! " + m.getNombre() + " DEF +" + aumentoDef + " (ahora " + m.getDef() + ")");
        }
    }

    @Override
    public void activar(Monstruo monstruo) {
        int defActual = monstruo.getDef();
        monstruo.setDef(defActual + aumentoDef);
        Mensajero.add(" Trampa Defensa activada! DEF aumenta de " + defActual + " a " + monstruo.getDef());
    }

    @Override
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        // Aumentar DEF del monstruo que está siendo atacado (el del jugador)
        if (!jugador.getCampo().isEmpty()) {
            Monstruo m = jugador.getCampo().get(0);
            int defActual = m.getDef();
            m.setDef(defActual + aumentoDef);
            Mensajero.add(" Trampa Defensa activada! " + m.getNombre() + " DEF +" + aumentoDef + " (ahora " + m.getDef() + ")");
        }
    }
}