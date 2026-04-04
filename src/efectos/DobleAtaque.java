package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class DobleAtaque implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Mounstruo mounstruo) {

        mounstruo.setAtk(mounstruo.getAtk() * 2);

        System.out.println(" El ataque se duplica ");
    }
}