package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

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

        System.out.println(" El monstruo pierde " + reduccion + " ataque ");
    }
}