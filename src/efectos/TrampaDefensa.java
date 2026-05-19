package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

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
            System.out.println(" Trampa Defensa activada! " + m.getNombre() + " DEF +" + aumentoDef + " (ahora " + m.getDef() + ")");
        }
    }

    @Override
    public void activar(Monstruo monstruo) {
        int defActual = monstruo.getDef();
        monstruo.setDef(defActual + aumentoDef);
        System.out.println(" Trampa Defensa activada! DEF aumenta de " + defActual + " a " + monstruo.getDef());
    }
}