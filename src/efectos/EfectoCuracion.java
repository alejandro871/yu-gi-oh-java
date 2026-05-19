package efectos;
import cartas.Mounstruo;
import jugadores.Jugador;


public class EfectoCuracion implements Efecto{

    private int cantidadCuracion;

    public EfectoCuracion (int cantidadCuracion){

        this.cantidadCuracion = cantidadCuracion;

    }

    @Override
    public void activar(Jugador jugador) {
    
        jugador.setVida(jugador.getVida()+ cantidadCuracion);
        System.out.println(" El jugador recupera " + cantidadCuracion + " LP. Vida actual: " + jugador.getVida());
    }

    @Override
    public void activar(Mounstruo mounstruo) {
       return;
    }

    
}

