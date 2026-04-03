package juego;
import jugadores.Jugador;

public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;

    public Juego(Jugador j1 ,Jugador j2){

        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActual = j1;

    }

    public void iniciarTurno(){

        System.out.println(" Turno de: " + jugadorActual.getNombre());

        jugadorActual.robarCarta();
    }

    public void cambiarTurno(){

        if(jugadorActual == jugador1){

            jugadorActual = jugador2;

            } else {

        jugadorActual = jugador1;
        }
    }

        public Jugador getJugadorActual(){

            return jugadorActual;
        }
    
}
