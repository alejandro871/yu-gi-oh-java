package modelo.efectos;

import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class EfectoDebilidad implements Efecto {

    private int reduccion;

    public EfectoDebilidad(int reduccion){
        this.reduccion = reduccion;
    }

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Mounstruo mounstruo) {
        int nuevoAtk = mounstruo.getAtk() - reduccion;
        if(nuevoAtk < 0) nuevoAtk = 0;
        mounstruo.setAtk(nuevoAtk);
        Mensajes.agregar(" El monstruo pierde " + reduccion + " ataque ");
    }
}
