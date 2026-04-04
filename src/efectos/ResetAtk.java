package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class ResetAtk implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Mounstruo mounstruo) {

        mounstruo.resetAtk();

        System.out.println(" El ataque vuelve a su valor original ");
    }
}