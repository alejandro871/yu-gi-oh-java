import cartas.CartaMagica;
import cartas.Mounstruo;
import efectos.EfectoCuracion;
import jugadores.Jugador;
import efectos.AumentoAtaque;
import efectos.PotOfGreed;
import juego.Juego;


public class App {

    public static void main(String[] arg) {

        Jugador jugador1 = new Jugador("Alejo");
        Jugador jugador2 = new Jugador("Ojela");
    

        Mounstruo mounstruo1 = new Mounstruo("Dragoncillo", 1700, 2500, 10, "  "  );
        Mounstruo mounstruo2 = new Mounstruo("Vampiro", 1000, 2500, 5, "  "  );
        Mounstruo mounstruo3 = new Mounstruo("Pitbull", 1200, 500, 1, "  "  );

        CartaMagica magia1 = new CartaMagica("curandero", "Incrementa 100PH", new EfectoCuracion(100));
        CartaMagica magia2 = new CartaMagica("venenista", " posiones de veneno ", new AumentoAtaque(500));


         //magia2.activar(jugador1);
         
         //jugador2.recibirDanio(mounstruo1.getAtk());



        //jugador1.agregarCarta (mounstruo1);
        //jugador1.agregarCarta(magia2);

        //jugador1.mostrarCartas();

        //System.out.println("ATK antes: " + mounstruo1.getAtk());

        //magia2.activar(mounstruo1);

       // System.out.println("ATK despues: " + mounstruo1.getAtk());


        Juego juego = new Juego(jugador1, jugador2);

        // turno 1
        //juego.iniciarTurno();

        // turno 2
        //juego.cambiarTurno();
        //juego.iniciarTurno();


        //jugador1.agregarCarta(mounstruo1);

        //jugador1.mostrarCampo();

        //jugador1.jugarMonstruo(mounstruo1);

       

        //jugador1.agregarCarta(mounstruo1);
        //jugador1.jugarMonstruo(mounstruo1);

        // jugador2 NO tiene monstruos

        //jugador1.atacarJugador(jugador2);

        jugador1.agregarCarta(mounstruo1);
        jugador2.agregarCarta(mounstruo2);
        jugador2.agregarCarta(mounstruo3);
        jugador1.jugarMonstruo(mounstruo1);
        jugador2.jugarMonstruo(mounstruo2);
        jugador2.jugarMonstruo(mounstruo3);

        jugador2.atacarJugador(jugador1);
        jugador1.atacarJugador(jugador2);
        //jugador1.atacarJugador(jugador2);
    


        juego.ganador();
        juego.estadoJuego();

        
    }
  
    
}
