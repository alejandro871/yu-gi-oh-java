package modelo.efectos;

import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class RoboDefinitivo implements Efecto {

    @Override
    public void activar(Jugador jugador) {
        jugador.robarCarta();
        jugador.setVida(jugador.getVida() + 300);
        Mensajes.agregar(" Roba 1 carta y gana 300 LP ");
    }

    @Override
    public void activar(Mounstruo mounstruo) {}
}
