package src.efectos;
import src.cartas.Mounstruo;
import src.jugadores.Jugador;


public class EfectoCuracion implements Efecto{

    private int cantidadCuracion;

    public EfectoCuracion (int cantidadCuracion){

        this.cantidadCuracion = cantidadCuracion;

    }

    @Override
    public void activar(Jugador jugador) {
    
        jugador.setVida(jugador.getVida()+ cantidadCuracion);
    }

    @Override
    public void activar(Mounstruo mounstruo) {
       return;
    }

    
}

