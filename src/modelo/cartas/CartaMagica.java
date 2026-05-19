package modelo.cartas;
import modelo.efectos.Efecto;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class CartaMagica extends Carta implements Activable {

    private Efecto efecto;

    public Efecto getEfecto() {
        return efecto;
    }

    public CartaMagica (String nombre, String descripcion, Efecto efecto){
        super (nombre, descripcion);
        this.efecto = efecto;
    }

    @Override
    public void activar(Jugador jugador){
        Mensajes.agregar("Se activa carta magica: "+ getNombre());
        efecto.activar(jugador);
    }

     public void activar(Mounstruo mounstruo){
        Mensajes.agregar("Se activa carta magica: "+ getNombre());
        efecto.activar(mounstruo);
    }
}
