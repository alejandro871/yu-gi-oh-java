package modelo;
import modelo.Jugador;
import modelo.efectoTemporalAtk;
import java.util.ArrayList;
import java.util.Random;

public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Jugador jugadorEnemigo;
    private boolean primerTurnoPartida; //Solo es durante el primer turno 
    private ArrayList<efectoTemporalAtk> efectosTemporalesActivos;
    private void cambiarTurno(){

        Jugador temp = jugadorActual;
        jugadorActual  = jugadorEnemigo;
        jugadorEnemigo = temp;

    }

    public Juego(Jugador j1 ,Jugador j2){

        this.jugador1 = j1;
        this.jugador2 = j2;

        efectosTemporalesActivos = new ArrayList<>();

        Random rand = new Random();

        if(rand.nextBoolean()){

            this.jugadorActual = j1;
            this.jugadorEnemigo = j2;
        }else{

            this.jugadorActual = j2;
            this.jugadorEnemigo = j1;
        }

        this.primerTurnoPartida = true;
        Mensajero.add(jugadorActual.getNombre() + " va primero "); //Ahora si al azar

    }

    public void estadoJuego(){

        Mensajero.add("------ESTADO DE LA PARTIDA-----");

        Mensajero.add(jugador1.getNombre() + " - LP: " + jugador1.getVida() + "| Mazo: " + jugador1.getCartasMazo());
        jugador1.mostrarCampo();

        Mensajero.add("");

        Mensajero.add(jugador2.getNombre() + " - LP: " + jugador2.getVida() + "| Mazo: " + jugador2.getCartasMazo());
        jugador2.mostrarCampo();

        Mensajero.add("-------------");
    }

    public boolean esPrimerTurno(){
    return primerTurnoPartida;
}

    public void iniciarTurno(){

        Mensajero.add(" Turno de: " + jugadorActual.getNombre());

        jugadorActual.robarCarta();
        jugadorActual.reiniciarTurno();
    }

    public Jugador getJugadorActual(){

        return jugadorActual;
    }
    
    public boolean hayGanador(){
    return jugador1.getVida() <= 0 || jugador2.getVida() <= 0;
}

    public boolean faseRobo(){

        Mensajero.add("----Robo----");

        return jugadorActual.robarCarta();
    } 

    public void faseBatalla(){

        Mensajero.add("----Fase batalla----");

        if (primerTurnoPartida) {

            Mensajero.add(" Primer turno: no se puede atacar ");

            return;
        }

        jugadorActual.atacarJugador(jugadorEnemigo);
    }

    public void fasePrincipal(){

        Mensajero.add(" ----Fase principal---- ");

        Mensajero.add(jugadorActual.getNombre() + " puede jugar 1 carta ");

        jugadorActual.mostrarMano();

    }

    public void faseFinal(){

        Mensajero.add("----Fase final---- ");

        // Revertir efectos temporales de ambos jugadores
        jugadorActual.revertirEfectosTemporales();
        jugadorEnemigo.revertirEfectosTemporales();

        // Limpiar la lista interna (ya no se usa, cada Jugador tiene la suya)
        efectosTemporalesActivos.clear();

        jugadorActual.reiniciarTurno();

        primerTurnoPartida = false;

        cambiarTurno();

        Mensajero.add(" Turno terminado ");
    }

    public boolean ejecutarTurnoCompleto(){

        Mensajero.add("-----------------");
        Mensajero.add(" Turno de: " + jugadorActual.getNombre());
        Mensajero.add("-----------------");

        if (!faseRobo()) return false; // mazo vacío  derrota

        fasePrincipal();
        faseBatalla();
        faseFinal();

        return true;
    }

    public void registrarEfectoTemporal(efectoTemporalAtk efecto){

        efectosTemporalesActivos.add(efecto);

        }

    public Jugador getJugadorEnemigo(){

        return jugadorEnemigo;
    }


}




