package efectos;

import cartas.Monstruo;
import jugadores.Jugador;

public class TrampaDestructor implements Efecto {

    // Trampa que destruye el monstruo atacante
    // Nota: La destrucción real se debe manejar en la lógica del juego

    @Override
    public void activar(Jugador jugador) {
        // No aplica directamente
    }

    @Override
    public void activar(Monstruo Monstruo) {
        System.out.println(" ¡Trampa Destructor activada! El monstruo " + Monstruo.getNombre() + " es destruido");
        // El monstruo es marcado pero la eliminación real ocurre en la lógica del juego
    }
}
