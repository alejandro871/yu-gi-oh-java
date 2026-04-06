

import jugadores.Jugador;
import juego.Mazo;
import juego.Juego;

public class App {

    public static void main(String[] args) {

        Jugador j1 = new Jugador("Alejo");
        Jugador j2 = new Jugador("Ojela");

        
        Mazo.repartir(j1, j2);

        Juego juego = new Juego(j1, j2);
        juego.estadoJuego();

        System.out.println("\nCartas de " + j1.getNombre() + ":");
        j1.mostrarCartas();

        System.out.println("\nCartas de " + j2.getNombre() + ":");
        j2.mostrarCartas();
    }
}
