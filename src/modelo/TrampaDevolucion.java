package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaDevolucion implements Efecto {

    private int danioDevuelto;

    public TrampaDevolucion(int danioDevuelto) {
        this.danioDevuelto = danioDevuelto;
    }

    @Override
    public void activar(Jugador jugador) {
        Mensajero.add(" Trampa Devolucion activada! El oponente recibe " + danioDevuelto + " de dano reflejado");
    }

    @Override
    public void activar(Monstruo monstruo) {
        Mensajero.add(" Trampa Devolucion activada! Dano reflejado al oponente");
    }
    
    public int getDanioDevuelto() {
        return danioDevuelto;
    }
}