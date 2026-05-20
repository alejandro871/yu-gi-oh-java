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
        Mensajero.add(" Trampa Devolucion activada! Dano refletado al oponente");
    }

    @Override
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        // REFLEJAR daño al oponente (atacante)
        Mensajero.add(" Trampa Devolucion activada! Daño refletado al oponente");
        Mensajero.add("  FLECHA! Dano refletado: " + danioDevuelto + " al oponente");
        oponente.recibirDanio(danioDevuelto);
    }

    public int getDanioDevuelto() {
        return danioDevuelto;
    }
}