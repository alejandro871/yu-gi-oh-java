package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class DobleAtaque implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Monstruo Monstruo) {

        Monstruo.setAtk(Monstruo.getAtk() * 2);

        System.out.println(" El ataque se duplica ");
    }
}
