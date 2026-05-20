package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public interface Efecto {

    void activar(Jugador jugador);
    void activar(Monstruo Monstruo);
    
    // Por defecto: no hace nada (para backward compatibility)
    default void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        activar(jugador); // Por defecto, trata como activación normal
    }
    
}



