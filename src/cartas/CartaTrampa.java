package cartas;
import efectos.Efecto;
import efectos.TrampaDevolucion;
import efectos.TrampaCuracion;
import efectos.TrampaDefensa;
import efectos.TrampaDebilidad;
import efectos.TrampaRobo;
import efectos.TrampaDestructor;
import efectos.TrampaBloqueo;
import jugadores.Jugador;


public class CartaTrampa extends Carta implements Activable {

    private Efecto efecto;


    public CartaTrampa (String nombre, String descripcion, Efecto efecto){

        super (nombre, descripcion);
        this.efecto = efecto;

    }

    @Override
    public void activar(Jugador jugador){

        System.out.println("Se activa carta trampa: "+ getNombre());
        efecto.activar(jugador);

    }

    public void activar(Monstruo Monstruo){

        System.out.println("Se activa carta trampa: "+ getNombre());
        efecto.activar(Monstruo);

    }

    // Activacion por ataque con monstruo atacante
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        System.out.println(" TRAMPA ACTIVADA: " + getNombre() + " contra " + atacante.getNombre());
        
        // Aplicar efectos específicos al monstruo atacante
        if (efecto instanceof TrampaDefensa) {
            efecto.activar(jugador);
        } else if (efecto instanceof TrampaDebilidad) {
            efecto.activar(atacante);
        } else if (efecto instanceof TrampaDestructor) {
            // Destruir el monstruo atacante
            oponente.eliminarMonstruo(atacante);
        } else if (efecto instanceof TrampaBloqueo) {
            efecto.activar(atacante);
        } else if (efecto instanceof TrampaDevolucion) {
            // REFLEJAR daño al oponente (atacante)
            int danio = ((TrampaDevolucion)efecto).getDanioDevuelto();
            oponente.recibirDanio(danio);
            System.out.println("  FLECHA! Dano refletado: " + danio + " al oponente");
        }
        
        // También ejecutar lógica por defecto si la tiene
        efecto.activar(jugador, oponente, atacante);
    }

     
}