package modelo.efectos;

import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;

public interface Efecto {

    void activar(Jugador jugador);
    void activar(Mounstruo mounstruo);
    
}// para que la carta magica tenga como activarse
//osea que esa es la forma de activarse de tpda carta que tenga EFECTO



