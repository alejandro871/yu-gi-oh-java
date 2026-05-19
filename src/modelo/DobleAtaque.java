package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class DobleAtaque implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Monstruo Monstruo) {

        Monstruo.setAtk(Monstruo.getAtk() * 2);

        Mensajero.add(" El ataque se duplica ");
    }
}
