package src.cartas;

import src.efectos.Efecto;
import src.jugadores.Jugador;


public class CartaMagica extends Carta {

    private Efecto efecto;


    public CartaMagica (String nombre, String descripcion, Efecto efecto){

        super (nombre, descripcion);
        this.efecto = efecto;

    }

   
    public void activar(Jugador jugador){

        System.out.println("Se activa carta magica: "+ getNombre());
        efecto.activar(jugador);


    }


     public void activar(Mounstruo mounstruo){

        System.out.println("Se activa carta magica: "+ getNombre());
        efecto.activar(mounstruo);


    }

    
}
