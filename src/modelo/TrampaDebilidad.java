package modelo;

import modelo.Monstruo;
import modelo.Jugador;

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
    public void activar(Monstruo Monstruo) {
        int atkActual = Monstruo.getAtk();
        int nuevoAtk = Math.max(0, atkActual - reduccionAtk);
        Monstruo.setAtk(nuevoAtk);
        Mensajero.add(" ¡Trampa Debilidad activada! ATK reducido de " + atkActual + " a " + nuevoAtk);
    }
}
