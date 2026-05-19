package modelo.efectos;
import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class EfectoCuracion implements Efecto{

    private int cantidadCuracion;

    public EfectoCuracion (int cantidadCuracion){
        this.cantidadCuracion = cantidadCuracion;
    }

    @Override
    public void activar(Jugador jugador) {
        jugador.setVida(jugador.getVida()+ cantidadCuracion);
        Mensajes.agregar(" El jugador recupera " + cantidadCuracion + " LP. Vida actual: " + jugador.getVida());
    }

    @Override
    public void activar(Mounstruo mounstruo) {
       return;
    }
}
