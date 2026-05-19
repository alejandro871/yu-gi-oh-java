package modelo;

import modelo.Jugador;

public interface Activable {

    void activar(Jugador jugador);
    
    // Por defecto: no hace nada extra
    default void activar(Jugador jugador, Jugador oponente) {
        activar(jugador);
    }
} 
