package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class Sacrificio implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Mounstruo mounstruo) {

        int vida = mounstruo.getAtk();

        mounstruo.setAtk(0);

        System.out.println(" El monstruo se sacrifica y otorga " + vida + " LP ");
    }
}