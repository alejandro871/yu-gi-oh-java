package src.jugadores;
import java.util.ArrayList;
import src.cartas.Carta;


public class Jugador {
    
    private String nombre;
    private int vida;
    private ArrayList<Carta> cartas; //es una lista de cartas que tiene el jugaodr
    //el <carta> es porq solo se pueden en esa lista los objetos cartas
    public Jugador (String nombre){

        this.nombre = nombre;
        this.vida = 8000; //inica directamente con la vida estipulada ya que no varia
        this.cartas = new ArrayList<>(); //nuevo bolsillo pa las cartas

    }

    public void agregarCarta(Carta carta){

        cartas.add(carta);

    }

    public void mostrarCartas(){

        for (Carta c: cartas) {

            System.out.println(c.grtNombre());

        }

    }

    public int getVida(){

        return vida;

    }

public String getNombre(){

    return nombre;

}




}
