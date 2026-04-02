import src.cartas.CartaMagica;
import src.cartas.Mounstruo;
import src.jugadores.Jugador;


public class Main {

    public static void main(String[] arg) {

        Jugador jugador1 = new Jugador("Alejo");
        Jugador jugador2 = new Jugador("Ojela");

        Mounstruo mounstruo1 = new Mounstruo("Dragoncillo", 3000, 2500, 10, "  "  );

        CartaMagica magia = new CartaMagica("mago electrico", "");


         jugador2.recibirDanio(mounstruo1.getAtk());



        jugador1.agregarCarta (mounstruo1);
        jugador1.agregarCarta(magia);

        jugador1.mostrarCartas();

    }
    
}
