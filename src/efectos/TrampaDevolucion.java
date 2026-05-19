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
        System.out.println(" Trampa Devolucion activada! El oponente recibe " + danioDevuelto + " de dano reflejado");
    }

    @Override
    public void activar(Monstruo monstruo) {
        System.out.println(" Trampa Devolucion activada! Dano reflejado al oponente");
    }
    
    public int getDanioDevuelto() {
        return danioDevuelto;
    }
}