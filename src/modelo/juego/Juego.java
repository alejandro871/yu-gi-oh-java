package modelo.juego;
import modelo.jugadores.Jugador;
import modelo.efectos.efectoTemporalAtk;
import modelo.Mensajes;
import java.util.ArrayList;
import java.util.Random;

public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Jugador jugadorEnemigo;
    private boolean primerTurnoPartida;
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
        Mensajes.agregar(jugadorActual.getNombre() + " va primero ");
    }

    public boolean esPrimerTurno(){
        return primerTurnoPartida;
    }

    public Jugador getJugadorActual(){
        return jugadorActual;
    }

    public boolean hayGanador(){
        if (jugador1.getVida() <= 0){
            Mensajes.agregar(jugador2.getNombre() + " gana el duelo!");
            return true;
        }
        if (jugador2.getVida() <= 0){
            Mensajes.agregar(jugador1.getNombre() + " gana el duelo!");
            return true;
        }
        return false;
    }

    public boolean faseRobo(){
        return jugadorActual.robarCarta();
    }

    public void faseFinal(){
        for(efectoTemporalAtk ef: efectosTemporalesActivos){
            ef.revertir();
        }
        efectosTemporalesActivos.clear();
        jugadorActual.reiniciarTurno();
        primerTurnoPartida = false;
        cambiarTurno();
        Mensajes.agregar(" Turno terminado ");
    }

    public void registrarEfectoTemporal(efectoTemporalAtk efecto){
        efectosTemporalesActivos.add(efecto);
    }

    public Jugador getJugadorEnemigo(){
        return jugadorEnemigo;
    }
}
