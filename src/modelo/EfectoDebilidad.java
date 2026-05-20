package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class EfectoDebilidad implements Efecto {

    private int reduccion;

    public EfectoDebilidad(int reduccion){
        this.reduccion = reduccion;
    }

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Monstruo Monstruo) {

        int nuevoAtk = Monstruo.getAtk() - reduccion;
        if(nuevoAtk < 0) nuevoAtk = 0;

        Monstruo.setAtk(nuevoAtk);

        Mensajero.add(" El monstruo pierde " + reduccion + " ataque ");
    }
}
