package modelo;
import modelo.Efecto;
import modelo.Jugador;


public class CartaMagica extends Carta implements Activable {

    private Efecto efecto;


    public CartaMagica (String nombre, String descripcion, Efecto efecto){

        super (nombre, descripcion);
        this.efecto = efecto;

    }

    @Override
    public void activar(Jugador jugador){

        Mensajero.add("Se activa carta magica: "+ getNombre());
        efecto.activar(jugador);


    }


     public void activar(Monstruo Monstruo){

        Mensajero.add("Se activa carta magica: "+ getNombre());
        efecto.activar(Monstruo);


    }

    // Activar con oponente (para efectos que afectan al enemigo)
    public void activar(Jugador jugador, Jugador oponente) {
        Mensajero.add("Se activa carta magica: "+ getNombre());
        efecto.activar(jugador, oponente);
    }

}
