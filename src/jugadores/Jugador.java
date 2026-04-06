package jugadores;
import java.util.ArrayList;
import cartas.Carta;
import cartas.Mounstruo;
import cartas.CartaMagica;


public class Jugador {
    
    private String nombre;
    private int vida;
    private ArrayList<Carta> mano; //es una lista de cartas que tiene el jugaodor
    private ArrayList<Carta> mazo;
    private ArrayList<Mounstruo> campo;
    private ArrayList<Carta> cementerio;
    private boolean cartaJugadaEsteTurno;


public Jugador (String nombre){

        this.nombre = nombre;
        this.vida = 8000; 
        this.mano = new ArrayList<>(); 
        this.mazo = new ArrayList<>();
        this.campo = new ArrayList<>();
        this.cementerio = new ArrayList<>();
        this.cartaJugadaEsteTurno = false;

    }

public void agregarCarta(Carta carta){

        mazo.add(carta);

    }

public void tomarManoInicial(){

    for (int i = 0; i < 5; i++) { //

            if (!mazo.isEmpty()) {

                mano.add(mazo.remove(0));

            }

        }

        System.out.println(nombre + " toma su mano inicial de 5 cartas.");
}

public void mostrarCartas(){

        for (Carta c: mazo) {

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

    Mounstruo atacante = null;

        for (Mounstruo m : campo) {

            if (!m.yaAtaco()) {

                atacante = m;

                break;

            }

        }

if (atacante == null) {

    System.out.println(" Todos tus monstruos ya atacaron este turno ");

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

    cartaJugadaEsteTurno = false;

    for (Mounstruo m : campo){

        m.reiniciarAtaque();

    }
}
 
public void jugarMonstruo(Mounstruo m){

    if(cartaJugadaEsteTurno){

        System.out.println(" Ya jugaste carta este turno");
        return;
    }

    if(!mano.contains(m)){

        System.out.println(" Ese mountruo no esta en tu mano ");
        return;
    }
    mano.remove(m);
    campo.add(m);
    cartaJugadaEsteTurno = true;
    System.out.println(nombre + " Invoca: " + m.getNombre());

}

public void recibirDanio(int danio){

    this.vida -= danio; // Aqui es donde se le resta la vida

    if (this.vida < 0 ) this.vida = 0; // Para evitar os negativos

    System.out.println(nombre + " recibe "+ danio + " de daño. Vida restante: " + vida);


}

public void eliminarCarta(Carta carta){

    boolean eliminada = false;

    for (int i=0; i<mazo.size() && !eliminada; i++){

        if(carta.getNombre().equals(mazo.get(i).getNombre())){

            mazo.remove(i);
            eliminada = true;
        }


    }

}

public boolean robarCarta(){

    if(!mazo.isEmpty()){
        Carta carta = mazo.remove(0);//se roba la primera carta
        mano.add(carta);
        System.out.println( nombre + " roba " +carta.getNombre() + "- Cartas del mazo: " + mazo.size());
        return true;

    }else{

        System.out.println(" No hay cartas para robar --PIERDE DUELO-- ");
        this.vida = 0;
        return false;
    }

}

public void jugarMagia(CartaMagica carta){

    if (cartaJugadaEsteTurno) {

            System.out.println(" Ya jugaste una carta este turno ");

            return;
        }

        if (!mano.contains(carta)) {

            System.out.println(" Esa carta mágica no está en tu mano ");

            return;
        }

        mano.remove(carta);
        cartaJugadaEsteTurno = true;
        System.out.println(nombre + " activa la carta mágica: " + carta.getNombre());
        carta.activar(this);
        cementerio.add(carta);
    
}

public void mostrarCementerio(){

    System.out.println(" Cementerio de " + nombre + ": ");

    for (Carta c : cementerio){
        System.out.println("- " + c.getNombre());
    }
}

public void mostrarMano(){

     System.out.println("Mano de " + nombre + " (" + mano.size() + " cartas):");

     for (int i = 0; i < mano.size(); i++) {

        System.out.println("  [" + i + "] " + mano.get(i).getNombre());

        }
    }

}








