package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public interface Efecto {

    void activar(Jugador jugador);
    void activar(Monstruo Monstruo);
    
    // Por defecto: no hace nada (para backward compatibility)
    default void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        activar(jugador); // Por defecto, trata como activación normal
    }
    
}



