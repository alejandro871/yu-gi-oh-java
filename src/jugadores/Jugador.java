package jugadores;
import java.util.ArrayList;
import cartas.Carta;
import cartas.Mounstruo;
import cartas.CartaMagica;


public class Jugador {
    
    private String nombre;
    private int vida;
    private ArrayList<Carta> cartas; //es una lista de cartas que tiene el jugaodr
    //el <carta> es porq solo se pueden en esa lista los objetos cartas
    private ArrayList<Mounstruo> campo;
    private ArrayList<Carta> cementerio;


public Jugador (String nombre){

        this.nombre = nombre;
        this.vida = 8000; //inica directamente con la vida estipulada ya que no varia
        this.cartas = new ArrayList<>(); //nuevo bolsillo pa las cartas
        this.campo = new ArrayList<>();
        this.cementerio = new ArrayList<>();

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

public void mostrarCampo(){

    System.out.println("Monstruos en campo de " + nombre + ":");

    for (Mounstruo m : campo){
        System.out.println("- " + m.getNombre() + " ATK: " + m.getAtk());
    }
}

public void eliminarMonstruo(Mounstruo m){

    campo.remove(m);
    cementerio.add(m);
    System.out.println( m.getNombre() + " fue enviado al cementerio ");
}

public void atacarJugador( Jugador enemigo ){

if (this.campo.isEmpty()){ //el isEmpty es para mirar lo que hay dentro de la lista y sebaer si esta vacia (sin mounstruos)

    System.out.println(" No tienes Mounstruos para atacar ");
    return;

    }

    Mounstruo atacante = this.campo.get(0); 

    if (atacante.yaAtaco()){

    System.out.println("Este monstruo ya atacó en este turno");

    return;
    
    }



if(enemigo.campo.isEmpty()){

        System.out.println("¡" + atacante.getNombre() + " ataca directamente! ");

        enemigo.recibirDanio(atacante.getAtk());//ataque directo

    }else{

        Mounstruo defensor = enemigo.campo.get(0);
        int resultado = atacante.atacar(defensor);

        if  (resultado > 0){

            System.out.println(atacante.getNombre() + " destruye a " + defensor.getNombre());

            enemigo.eliminarMonstruo(defensor);

            enemigo.recibirDanio(resultado);
        }

        else if(resultado < 0){

            System.out.println(defensor.getNombre() + " destruye a " + atacante.getNombre());

            this.eliminarMonstruo(atacante);

            this.recibirDanio(-resultado);

        }else{
            
            System.out.println("Ambos monstruos se destruyen");

            this.eliminarMonstruo(atacante);

            enemigo.eliminarMonstruo(defensor);

        }


    }
    
    atacante.marcarAtaque();

}

public void reiniciarAtaques(){

    for (Mounstruo m : campo){

        m.reiniciarAtaque();

    }
}
 
public void jugarMonstruo(Mounstruo m){

    campo.add(m);
    eliminarCarta(m);

    System.out.println(nombre + " invoca a " + m.getNombre());
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

public Carta robarCarta(){

    if(!cartas.isEmpty()){
        Carta carta = cartas.remove(0);//se roba la primera carta
        System.out.println( nombre + " roba " +carta.getNombre());
        return carta;

    }else{

        System.out.println(" No hay cartas para robar ");
        this.vida = 0;
        return null;
    }

}

public void jugarMagia(CartaMagica carta){

    if (!cartas.contains(carta)) {

        System.out.println(" No tienes esa carta en la mano ");

        return;
    }

    eliminarCarta(carta);

    System.out.println(nombre + " activa la carta magica: " + carta.getNombre());

    carta.activar(this);

    cementerio.add(carta);
    
}

public void mostrarCementerio(){

    System.out.println(" Cementerio de " + nombre + ": ");

    for (Carta c : cementerio){
        System.out.println("- " + c.getNombre());
    }
}

}





