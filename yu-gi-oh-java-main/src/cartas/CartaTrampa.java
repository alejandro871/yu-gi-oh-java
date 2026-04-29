package cartas;
import efectos.Efecto;
import jugadores.Jugador;


public class CartaTrampa extends Carta implements Activable {

    private Efecto efecto;

    public CartaTrampa(String nombre, String descripcion, Efecto efecto) {
        super(nombre, descripcion);
        this.efecto = efecto;
    }

    @Override
    public void activar(Jugador jugador) {
        System.out.println("Se activa carta trampa: " + getNombre());
        efecto.activar(jugador);
    }

    public void activar(Monstruo monstruo) {
        System.out.println("Se activa carta trampa: " + getNombre());
        efecto.activar(monstruo);
    }

    public void jugar(Jugador jugadorActual) {
        System.out.println(jugadorActual.getNombre() + " juega la trampa: " + getNombre());
        jugadorActual.ponerTrampa(this);
    }
}

