package modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.Carta;
import modelo.Monstruo;
import modelo.CartaMagica;
import modelo.CartaTrampa;


public class Jugador {
    
    private static final int VIDA_MAXIMA = 8000;
    private String nombre;
    private int vida;
    private ArrayList<Carta> mano; //es una lista de cartas que tiene el jugaodor
    private ArrayList<Carta> mazo;
    private ArrayList<Monstruo> campo;
    private ArrayList<Carta> cementerio;
    private ArrayList<CartaTrampa> trampas; // Trampas boca abajo
    private boolean cartaJugadaEsteTurno;
    private boolean yaAtacoEsteTurno;


public Jugador (String nombre){

        this.nombre = nombre;
        this.vida = VIDA_MAXIMA; 
        this.mano = new ArrayList<>(); 
        this.mazo = new ArrayList<>();
        this.campo = new ArrayList<>();
        this.cementerio = new ArrayList<>();
        this.trampas = new ArrayList<>();
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

        Mensajero.add(nombre + " toma su mano inicial de 5 cartas.");
}

public void mostrarCartas(){

        for (Carta c: mazo) {

            Mensajero.add(c.getNombre());

        }

    }

public int getVida(){

        return vida;

    }

public void setVida(int vida){
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > VIDA_MAXIMA) {
            this.vida = VIDA_MAXIMA;
        } else {
            this.vida = vida;
        }
    }

public String getNombre(){

    return nombre;

}

public void mostrarCampo(){

    Mensajero.add("Monstruos en campo de " + nombre + ":");

    if(campo.isEmpty()){

        Mensajero.add(" (vacio) ");
    }

    for (Monstruo m : campo){
        Mensajero.add("- " + m.getNombre() + "--ATK: " + m.getAtk() + "--DEF: " + m.getDef());
    }
}

public void eliminarMonstruo(Monstruo m){

    campo.remove(m);
    cementerio.add(m);
    Mensajero.add( m.getNombre() + " fue enviado al cementerio ");
}

public void atacarJugador(Jugador enemigo) {
    if (yaAtacoEsteTurno) {
        Mensajero.add(" Ya ataco este turno ");
        return;
    }

    if (campo.isEmpty()) {
        Mensajero.add(nombre + " No tienes Monstruos para atacar ");
        return;
    }

    Monstruo atacante = null;
    for (Monstruo m : campo) {
        if (!m.yaAtaco()) {
            atacante = m;
            break;
        }
    }

    if (atacante == null) {
        Mensajero.add(" Todos tus monstruos ya atacaron este turno ");
        return;
    }

    atacarJugador(enemigo, atacante);
}

public void atacarJugador(Jugador enemigo, Monstruo atacante) {
    if (atacante == null) {
        Mensajero.add("No se seleccionó un monstruo atacante.");
        return;
    }

    if (!campo.contains(atacante)) {
        Mensajero.add(" Ese monstruo no está en tu campo ");
        return;
    }

    if (atacante.yaAtaco()) {
        Mensajero.add(" Ese monstruo ya ataco este turno ");
        return;
    }

    if (enemigo.campo.isEmpty()) {
        Mensajero.add("¡" + atacante.getNombre() + " ataca directamente! ");
        enemigo.recibirDanio(atacante.getAtk());
        atacante.marcarAtaque();
        yaAtacoEsteTurno = true;
        return;
    }

    Monstruo defensor = enemigo.campo.get(0);
    int resultado = atacante.atacar(defensor);

    if (resultado > 0) {
        Mensajero.add(atacante.getNombre() + " destruye a " + defensor.getNombre() + " (diferencia: " + resultado + ")");
        enemigo.eliminarMonstruo(defensor);
        enemigo.recibirDanio(resultado);
    } else if (resultado < 0) {
        Mensajero.add(defensor.getNombre() + " destruye a " + atacante.getNombre() + " (diferencia: " + -resultado + ")");
        this.eliminarMonstruo(atacante);
        this.recibirDanio(-resultado);
    } else {
        Mensajero.add("Ambos monstruos se destruyen");
        this.eliminarMonstruo(atacante);
        enemigo.eliminarMonstruo(defensor);
    }

    atacante.marcarAtaque();
    yaAtacoEsteTurno = true;
}

public void reiniciarTurno(){

    cartaJugadaEsteTurno = false;

    yaAtacoEsteTurno = false;

    for (Monstruo m : campo){

        m.reiniciarAtaque();

    }
}

public void reiniciarAtaque(){

    reiniciarTurno();
}
 
public boolean requiereSacrificio(Monstruo m) {
    return m.getNivel() > 4;
}

public boolean tieneMonstruosParaSacrificio() {
    return campo.size() >= 1;
}

public void jugarMonstruo(Monstruo m) {
    jugarMonstruo(m, null);
}

public void jugarMonstruo(Monstruo m, Monstruo sacrificio) {

    if(cartaJugadaEsteTurno){

        Mensajero.add(" Ya jugaste carta este turno");
        return;
    }

    if(!mano.contains(m)){

        Mensajero.add(" Ese monstruo no esta en tu mano ");
        return;
    }

    // Verificar sacrificio para monstruos nivel > 4
    if (requiereSacrificio(m)) {
        if (sacrificio == null) {
            Mensajero.add(" Este monstruo requiere sacrificio (Nivel " + m.getNivel() + ")");
            Mensajero.add(" Selecciona un monstruo de tu campo para sacrificar");
            return;
        }
        if (!campo.contains(sacrificio)) {
            Mensajero.add(" El monstruo a sacrificar no está en tu campo");
            return;
        }
        // Eliminar el monstruo sacrificado
        campo.remove(sacrificio);
        cementerio.add(sacrificio);
        Mensajero.add(" ⚔️ " + sacrificio.getNombre() + " ha sido sacrificado!");
    }

    mano.remove(m);
    campo.add(m);
    cartaJugadaEsteTurno = true;
    Mensajero.add(nombre + " Invoca: " + m.getNombre());

}

public void recibirDanio(int danio){
    // El daño nunca debe ser negativo para evitar curaciones accidentales.
    int danioReal = Math.max(0, danio);
    setVida(this.vida - danioReal);
    Mensajero.add(nombre + " recibe " + danioReal + " de daño. Vida restante: " + vida);


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
        Mensajero.add( nombre + " roba " +carta.getNombre() + "- Cartas del mazo: " + mazo.size());
        return true;

    }else{

        Mensajero.add(" No hay cartas para robar --PIERDE DUELO-- ");
        this.vida = 0;
        return false;
    }

}

public void jugarMagia(CartaMagica carta){

    if (cartaJugadaEsteTurno) {

            Mensajero.add(" Ya jugaste una carta este turno ");

            return;
        }

        if (!mano.contains(carta)) {

            Mensajero.add(" Esa carta mágica no está en tu mano ");

            return;
        }

        mano.remove(carta);
        cartaJugadaEsteTurno = true;
        Mensajero.add(nombre + " activa la carta mágica: " + carta.getNombre());
    
}

public void jugarTrampa(CartaTrampa carta){

    if (cartaJugadaEsteTurno) {
        Mensajero.add(" Ya jugaste una carta este turno");
        return;
    }

    if (!mano.contains(carta)) {
        Mensajero.add(" Esa carta trampa no está en tu mano");
        return;
    }

    mano.remove(carta);
    trampas.add(carta);
    cartaJugadaEsteTurno = true;
    Mensajero.add(nombre + " activa la carta trampa: " + carta.getNombre() + " (boca abajo)");
}

public void removerCartaMano(int indice) {
    if (indice >= 0 && indice < mano.size()) {
        mano.remove(indice);
    }
}

public void swapCampo(int i, int j) {
    if (i >= 0 && i < campo.size() && j >= 0 && j < campo.size()) {
        Collections.swap(campo, i, j);
    }
}

public void activarTrampas(Jugador oponente, Monstruo atacante) {
    ArrayList<CartaTrampa> trampasActivadas = new ArrayList<>(trampas);
    
    for (CartaTrampa trampa : trampasActivadas) {
        Mensajero.add(" ⚠️ Trampa activada: " + trampa.getNombre());
        trampa.activar(this, oponente, atacante);
        trampas.remove(trampa);
        cementerio.add(trampa);
    }
}

public boolean tieneTrampas(){
    return !trampas.isEmpty();
}

public void mostrarCementerio(){

    Mensajero.add(" Cementerio de " + nombre + ": ");

    for (Carta c : cementerio){
        Mensajero.add("- " + c.getNombre());
    }
}

public void mostrarMano(){

     Mensajero.add("Mano de " + nombre + " (" + mano.size() + " cartas):");

     for (int i = 0; i < mano.size(); i++) {

        Mensajero.add("  [" + i + "] " + mano.get(i).getNombre());

        }
    }

public List<Carta> getMano() {
    return mano;
}

public List<Monstruo> getCampo() {
    return campo;
}

public List<CartaTrampa> getTrampas(){
    return trampas;
}

public int getCartasMazo(){

    return mazo.size();
}
}





