package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class ResetAtk implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Monstruo Monstruo) {

        Monstruo.resetAtk();

        System.out.println(" El ataque vuelve a su valor original ");
    }
}
