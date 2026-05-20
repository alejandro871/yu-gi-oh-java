package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class ResetAtk implements Efecto {

    @Override
    public void activar(Jugador jugador) {}

    @Override
    public void activar(Monstruo Monstruo) {

        Monstruo.resetAtk();

        Mensajero.add(" El ataque vuelve a su valor original ");
    }
}
