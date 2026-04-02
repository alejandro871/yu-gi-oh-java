package src.efectos;

import src.cartas.Mounstruo;
import src.jugadores.Jugador;

public interface Efecto {

    void activar(Jugador jugador);
    void activar(Mounstruo mounstruo);
    
}// para que la carta magica tenga como activarse
//osea que esa es la forma de activarse de tpda carta que tenga EFECTO



