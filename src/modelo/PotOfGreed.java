package modelo;
import modelo.Monstruo;
import modelo.Jugador;


public class PotOfGreed implements Efecto{

    @Override
    public void activar(Jugador jugador){

    Mensajero.add(" Se activa Pot Of Greed ");

        jugador.robarCarta();
        jugador.robarCarta();

    }

    @Override
    public void activar(Monstruo Monstruo){

        //no hace nada
    }
    
}


