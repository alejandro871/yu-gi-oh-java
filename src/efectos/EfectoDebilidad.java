package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

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

        System.out.println(" El monstruo pierde " + reduccion + " ataque ");
    }
}
