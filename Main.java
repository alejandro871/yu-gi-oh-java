import src.cartas.CartaMagica;
import src.cartas.Mounstruo;
import src.jugadores.Jugador;


public class Main {

    public static void main(String[] arg) {

        Jugador jugador = new Jugador("Alejo");

        Mounstruo dragon = new Mounstruo("Dragoncillo", 3000, 2500, 10);

        CartaMagica magia = new CartaMagica("mago electrico");


        jugador.agregarCarta (dragon);
        jugador.agregarCarta(magia);

        jugador.mostrarCartas();

    }
    
}
