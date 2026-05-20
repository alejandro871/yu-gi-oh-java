package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public interface Efecto {

    void activar(Jugador jugador);
    void activar(Monstruo Monstruo);

    // Activación con oponente (para mágicas que afectan al enemigo)
    default void activar(Jugador jugador, Jugador oponente) {
        // Por defecto, trata como activación normal
        activar(jugador);
    }

    // Activación con contexto de ataque: delegar a activar(Jugador) por defecto
    // CADA EFECTO debe sobrescribir este metodo para manejar su propia logica
    default void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        // Por defecto, trata como activación normal de carta
        activar(jugador);
    }

    // Verifica si la trampa debe activarse dado el contexto de ataque
    // Por defecto, todas las trampas se activan durante un ataque
    default boolean debeActivarse(Jugador jugador, Jugador oponente, Monstruo atacante) {
        return true;
    }

}



