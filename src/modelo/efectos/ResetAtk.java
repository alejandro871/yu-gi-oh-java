package modelo.efectos;

import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class ResetAtk implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Mounstruo mounstruo) {
        mounstruo.resetAtk();
        Mensajes.agregar(" El ataque vuelve a su valor original ");
    }
}
