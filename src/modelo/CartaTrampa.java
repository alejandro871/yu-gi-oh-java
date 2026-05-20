package modelo;
import modelo.Efecto;
import modelo.TrampaDevolucion;
import modelo.TrampaCuracion;
import modelo.TrampaDefensa;
import modelo.TrampaDebilidad;
import modelo.TrampaRobo;
import modelo.TrampaDestructor;
import modelo.TrampaBloqueo;
import modelo.Jugador;


public class CartaTrampa extends Carta implements Activable {

    private Efecto efecto;


    public CartaTrampa (String nombre, String descripcion, Efecto efecto){

        super (nombre, descripcion);
        this.efecto = efecto;

    }

    @Override
    public void activar(Jugador jugador){

        Mensajero.add("Se activa carta trampa: "+ getNombre());
        efecto.activar(jugador);

    }

    public void activar(Monstruo Monstruo){

        Mensajero.add("Se activa carta trampa: "+ getNombre());
        efecto.activar(Monstruo);

    }

    // Activacion por ataque con monstruo atacante
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        // Verificar si la trampa debe activarse en este contexto
        if (!efecto.debeActivarse(jugador, oponente, atacante)) {
            Mensajero.add(" ⚠️ " + getNombre() + " no se activa (condición no cumplida)");
            return;
        }

        Mensajero.add(" TRAMPA ACTIVADA: " + getNombre() + " contra " + atacante.getNombre());

        // Delegar completamente al efecto - cada efecto maneja su propia lógica
        efecto.activar(jugador, oponente, atacante);
    }

     
}