package efectos;
import cartas.Mounstruo;
import jugadores.Jugador;


public class PotOfGreed implements Efecto{

    @Override
    public void activar(Jugador jugador){

    System.out.println(" Se activa Pot Of Greed ");

        jugador.robarCarta();
        jugador.robarCarta();

    }

    @Override
    public void activar(Mounstruo mounstruo){

        //no hace nada
    }
    
}


