package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class RoboDefinitivo implements Efecto {

    @Override
    public void activar(Jugador jugador) {

        jugador.robarCarta();
        jugador.setVida(jugador.getVida() + 300);

        Mensajero.add(" Roba 1 carta y gana 300 LP ");
    }

    @Override
    public void activar(Monstruo Monstruo) {}
}
