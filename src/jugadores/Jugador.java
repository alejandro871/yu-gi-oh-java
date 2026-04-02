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

            System.out.println(c.getNombre());

        }

    }

    public int getVida(){

        return vida;

    }

    public void setVida(int vida){

        this.vida = vida;
    }

public String getNombre(){

    return nombre;

}

public void recibirDanio(int danio){

    this.vida -= danio; // Aqui es donde se le resta la vida

    if (this.vida < 0 ) {
        
        this.vida = 0;

    }// Para evitar os negativos

    System.out.println(nombre + " recibe "+ danio + " de daño. Vida restante: " + vida);


}

public void eliminarCarta(Carta carta){

    boolean eliminada = false;

    for (int i=0; i<cartas.size() && !eliminada; i++){

        if(carta.getNombre().equals(cartas.get(i).getNombre())){

            cartas.remove(i);
            eliminada = true;
        }


    }

}


}
