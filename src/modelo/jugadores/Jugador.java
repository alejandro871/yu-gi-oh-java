package modelo.jugadores;
import java.util.ArrayList;
import modelo.cartas.Carta;
import modelo.cartas.Mounstruo;
import modelo.cartas.CartaMagica;

import modelo.Mensajes;

public class Jugador {
    
    private String nombre;
    private int vida;
    private ArrayList<Carta> mano;
    private ArrayList<Carta> mazo;
    private ArrayList<Mounstruo> campo;
    private ArrayList<Carta> cementerio;
    private boolean cartaJugadaEsteTurno;
    private boolean yaAtacoEsteTurno;

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
    for (int i = 0; i < 5; i++) {
            if (!mazo.isEmpty()) {
                mano.add(mazo.remove(0));
            }
        }
        Mensajes.agregar(nombre + " toma su mano inicial de 5 cartas.");
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

public void eliminarMonstruo(Mounstruo m){
    campo.remove(m);
    cementerio.add(m);
    Mensajes.agregar(m.getNombre() + " fue enviado al cementerio");
}

public void atacarJugador( Jugador enemigo ){

if(yaAtacoEsteTurno){
    Mensajes.agregar(" Ya ataco este turno ");
    return;
}

if (campo.isEmpty()){
    Mensajes.agregar(nombre + " No tienes Mounstruos para atacar ");
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
    Mensajes.agregar(" Todos tus monstruos ya atacaron este turno ");
    return;
    }

if(enemigo.campo.isEmpty()){
     Mensajes.agregar("¡" + atacante.getNombre() + " ataca directamente! ");
     enemigo.recibirDanio(atacante.getAtk());
     atacante.marcarAtaque();
     yaAtacoEsteTurno = true;
     return;
    }
    
Mounstruo defensor = enemigo.campo.get(0);
    int resultado = atacante.atacar(defensor);
    
if  (resultado > 0){
        Mensajes.agregar(atacante.getNombre() + " destruye a " + defensor.getNombre() + " (diferencia: " + resultado + ")");
        enemigo.eliminarMonstruo(defensor);
        enemigo.recibirDanio(resultado);
        atacante.marcarAtaque();
        }
        else if(resultado < 0){
            Mensajes.agregar(defensor.getNombre() + " destruye a " + atacante.getNombre() + " (diferencia: " + -resultado + ")");
            this.eliminarMonstruo(atacante);
            this.recibirDanio(-resultado);
        }else{
            Mensajes.agregar("Ambos monstruos se destruyen");
            this.eliminarMonstruo(atacante);
            enemigo.eliminarMonstruo(defensor);
        }
        yaAtacoEsteTurno = true;
    }

public void reiniciarTurno(){
    cartaJugadaEsteTurno = false;
    yaAtacoEsteTurno = false;
    for (Mounstruo m : campo){
        m.reiniciarAtaque();
    }
}
 
public void jugarMonstruo(Mounstruo m){
    if(cartaJugadaEsteTurno){
        Mensajes.agregar(" Ya jugaste carta este turno");
        return;
    }
    if(!mano.contains(m)){
        Mensajes.agregar(" Ese mountruo no esta en tu mano ");
        return;
    }
    mano.remove(m);
    campo.add(m);
    cartaJugadaEsteTurno = true;
    Mensajes.agregar(nombre + " Invoca: " + m.getNombre());
}

public void recibirDanio(int danio){
    this.vida -= danio;
    if (this.vida < 0 ) this.vida = 0;
    Mensajes.agregar(nombre + " recibe "+ danio + " de daño. Vida restante: " + vida);
}

public boolean robarCarta(){
    if(!mazo.isEmpty()){
        Carta carta = mazo.remove(0);
        mano.add(carta);
        Mensajes.agregar(nombre + " roba " + carta.getNombre() + " - Cartas del mazo: " + mazo.size());
        return true;
    }else{
        Mensajes.agregar(" No hay cartas para robar --PIERDE DUELO-- ");
        this.vida = 0;
        return false;
    }
}

public void jugarMagia(CartaMagica carta){
    if (cartaJugadaEsteTurno) {
            Mensajes.agregar(" Ya jugaste una carta este turno ");
            return;
        }
        if (!mano.contains(carta)) {
            Mensajes.agregar(" Esa carta mágica no está en tu mano ");
            return;
        }
        mano.remove(carta);
        cartaJugadaEsteTurno = true;
        Mensajes.agregar(nombre + " activa la carta mágica: " + carta.getNombre());
        carta.activar(this);
        cementerio.add(carta);
}

public ArrayList <Carta> getMano(){
    return mano;
}

public ArrayList<Mounstruo> getCampo(){
    return campo;
}

public int getCartasMazo(){
    return mazo.size();
}

public void colocarComoDefensor(int indice) {
    if (indice >= 0 && indice < campo.size()) {
        Mounstruo defensor = campo.remove(indice);
        campo.add(0, defensor);
    }
}
}
