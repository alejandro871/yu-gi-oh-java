package juego;
import jugadores.Jugador;

public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private boolean primerTurno = true;

    public Juego(Jugador j1 ,Jugador j2){

        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActual = j1;

    }

    public void estadoJuego(){

        System.out.println("------ESTADO DEL PARTIDA-----");

        System.out.println(jugador1.getNombre() + " - LP: " + jugador1.getVida() );
        jugador1.mostrarCampo();

        System.out.println("");

        System.out.println(jugador2.getNombre() + " - LP: " + jugador2.getVida());
        jugador2.mostrarCampo();

        System.out.println("-------------");
    }


    public boolean esPrimerTurno(){
    return primerTurno;
}

    public void iniciarTurno(){

        System.out.println(" Turno de: " + jugadorActual.getNombre());

        jugadorActual.robarCarta();
        jugadorActual.reiniciarAtaques();
    }

    public void cambiarTurno(){

        primerTurno = false;

        if(jugadorActual == jugador1){

            jugadorActual = jugador2;

            } else {

        jugadorActual = jugador1;
        }
    }

    public Jugador getJugadorActual(){

        return jugadorActual;
    }
    
    public boolean hayGanador(){

    if (jugador1.getVida() <= 0){
        System.out.println(jugador2.getNombre() + " gana el duelo!");
        return true;
    }

    if (jugador2.getVida() <= 0){
        System.out.println(jugador1.getNombre() + " gana el duelo!");
        return true;
    }

    return false;

}

}
