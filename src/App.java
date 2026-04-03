import cartas.CartaMagica;
import cartas.Mounstruo;
import efectos.EfectoCuracion;
import jugadores.Jugador;


public class App {

    public static void main(String[] arg) {

        Jugador jugador1 = new Jugador("Alejo");
        Jugador jugador2 = new Jugador("Ojela");

        Mounstruo mounstruo1 = new Mounstruo("Dragoncillo", 3000, 2500, 10, "  "  );

        CartaMagica magia = new CartaMagica("curandero", "Incrementa 100PH", new EfectoCuracion(100));

         magia.activar(jugador1);
         
         jugador2.recibirDanio(mounstruo1.getAtk());



        jugador1.agregarCarta (mounstruo1);
        jugador1.agregarCarta(magia);

        jugador1.mostrarCartas();

    }

    
}
