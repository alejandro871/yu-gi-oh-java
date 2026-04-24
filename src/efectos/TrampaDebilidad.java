package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class TrampaDebilidad implements Efecto {

    private int reduccionAtk;

    public TrampaDebilidad(int reduccionAtk) {
        this.reduccionAtk = reduccionAtk;
    }

    @Override
    public void activar(Jugador jugador) {
        // No aplica
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        int atkActual = mounstruo.getAtk();
        int nuevoAtk = Math.max(0, atkActual - reduccionAtk);
        mounstruo.setAtk(nuevoAtk);
        System.out.println(" ¡Trampa Debilidad activada! ATK reducido de " + atkActual + " a " + nuevoAtk);
    }
}
