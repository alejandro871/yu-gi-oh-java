package modelo.efectos;

import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class DobleAtaque implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Mounstruo mounstruo) {
        mounstruo.setAtk(mounstruo.getAtk() * 2);
        Mensajes.agregar(" El ataque se duplica ");
    }
}
