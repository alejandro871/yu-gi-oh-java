package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class DanioInstantaneo implements Efecto {

    private int danio;

    public DanioInstantaneo(int danio){
        this.danio = danio;
    }

    @Override
    public void activar(Jugador jugador) {
        jugador.recibirDanio(danio);
    }

    @Override
    public void activar(Monstruo Monstruo) {}
}
