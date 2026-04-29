package cartas;
import efectos.Efecto;
import jugadores.Jugador;


public class CartaMagica extends Carta implements Activable {

    private Efecto efecto;


    public CartaMagica (String nombre, String descripcion, Efecto efecto){

        super (nombre, descripcion);
        this.efecto = efecto;

    }

    @Override
    public void activar(Jugador jugador){

        System.out.println("Se activa carta magica: "+ getNombre());
        efecto.activar(jugador);


    }


     public void activar(Monstruo Monstruo){

        System.out.println("Se activa carta magica: "+ getNombre());
        efecto.activar(Monstruo);


    }

    
}
