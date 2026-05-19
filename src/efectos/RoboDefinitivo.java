package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class RoboDefinitivo implements Efecto {

    @Override
    public void activar(Jugador jugador) {

        jugador.robarCarta();
        jugador.setVida(jugador.getVida() + 300);

        System.out.println(" Roba 1 carta y gana 300 LP ");
    }

    @Override
    public void activar(Mounstruo mounstruo) {}
}