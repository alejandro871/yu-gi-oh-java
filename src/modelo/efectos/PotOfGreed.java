package modelo.efectos;
import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class PotOfGreed implements Efecto{

    @Override
    public void activar(Jugador jugador){
        Mensajes.agregar(" Se activa Pot Of Greed ");
        jugador.robarCarta();
        jugador.robarCarta();
    }

    @Override
    public void activar(Mounstruo mounstruo){
    }
}
