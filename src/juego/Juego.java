package juego;
import jugadores.Jugador;
import java.util.Random;

public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Jugador jugadorEnemigo;
    private boolean primerTurnoPartida; //Solo es durante el primer turno 
    private void cambiarTurno(){

        Jugador temp = jugadorActual;
        jugadorActual  = jugadorEnemigo;
        jugadorEnemigo = temp;

    }

    public Juego(Jugador j1 ,Jugador j2){

        this.jugador1 = j1;
        this.jugador2 = j2;
        
        Random rand = new Random();

        if(rand.nextBoolean()){

            this.jugadorActual = j1;
            this.jugadorEnemigo = j2;
        }else{

            this.jugadorActual = j2;
            this.jugadorEnemigo = j1;
        }

        this.primerTurnoPartida = true;
        System.out.println(jugadorActual.getNombre() + " va primero "); //Ahora si al azar

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
    return primerTurnoPartida;
}

    public void iniciarTurno(){

        System.out.println(" Turno de: " + jugadorActual.getNombre());

        jugadorActual.robarCarta();
        jugadorActual.reiniciarTurno();
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

    public boolean faseRobo(){

        System.out.println("----Robo----");

        return jugadorActual.robarCarta();
    } 

    public void faseBatalla(){

        System.out.println("----Fase batalla----");

        if (primerTurnoPartida) {

            System.out.println(" Primer turno: no se puede atacar ");

            return;
        }

        jugadorActual.atacarJugador(jugadorEnemigo);
    }

    public void fasePrincipal(){

        System.out.println(" ----Fase principal---- ");

        System.out.println(jugadorActual.getNombre() + " puede jugar 1 carta ");

        jugadorActual.mostrarMano();

    }

    public void faseFinal(){

        System.out.println("----Fase final---- ");

        jugadorActual.reiniciarTurno();

        primerTurnoPartida = false;

        cambiarTurno();

        System.out.println(" Turno terminado ");
    }

    public boolean ejecutarTurnoCompleto(){

        System.out.println("-----------------");
        System.out.println(" Turno de: " + jugadorActual.getNombre());
        System.out.println("-----------------");

        if (!faseRobo()) return false; // mazo vacío  derrota

        fasePrincipal();
        faseBatalla();
        faseFinal();

        return true;
    }

}




