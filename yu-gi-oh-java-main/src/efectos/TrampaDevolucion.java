package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class TrampaDevolucion implements Efecto {

    private int danioDevuelto;

    public TrampaDevolucion(int danioDevuelto) {
        this.danioDevuelto = danioDevuelto;
    }

    @Override
    public void activar(Jugador jugador) {
        jugador.recibirDanio(danioDevuelto);
        System.out.println(" ¡Trampa Devolución activada! El oponente recibe " + danioDevuelto + " de daño reflejado");
    }

    @Override
    public void activar(Monstruo Monstruo) {
        // No aplica directamente al monstruo
    }
}
