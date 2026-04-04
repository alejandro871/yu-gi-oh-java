import java.util.ArrayList;

import efectos.PotOfGreed;
import efectos.EfectoCuracion;
import efectos.AumentoAtaque;
import efectos.DanioInstantaneo;
import efectos.DobleAtaque;
import efectos.EfectoDebilidad;
import efectos.EfectoDrenaje;
import efectos.RoboDefinitivo;
import efectos.ResetAtk;
import efectos.Escudo;

import cartas.Carta;
import cartas.CartaMagica;
import cartas.Mounstruo;

import jugadores.Jugador;
import juego.Juego;


public class App {

    public static void main(String[] arg) {


        Jugador jugador1 = new Jugador("Alejo");
        Jugador jugador2 = new Jugador("Ojela");

        ArrayList<Carta> mazo = new ArrayList<>(); 

        mazo.add(new Mounstruo("Dragon Blanco", 2200, 2000, 8, ""));
        mazo.add(new Mounstruo("Guerrero", 900, 1000, 4, ""));
        mazo.add(new Mounstruo("Mago", 1500, 1400, 5, ""));
        mazo.add(new Mounstruo("Bestia", 2000, 1800, 6, ""));
        mazo.add(new Mounstruo("Titan", 2100, 1900, 7, ""));
        mazo.add(new Mounstruo("Caballero", 1100, 900, 2, ""));
        mazo.add(new Mounstruo("Zombie", 800, 900, 1, ""));
        mazo.add(new Mounstruo("Vampiro", 2600, 1000, 9, ""));
        mazo.add(new Mounstruo("Pitbull", 2800, 3000, 12, ""));
        mazo.add(new Mounstruo("Golem", 2800, 2500, 10, ""));
        mazo.add(new Mounstruo("Esqueleto", 1000, 1200, 3, ""));
        mazo.add(new Mounstruo("Tarantula", 1100, 900, 4, ""));
        mazo.add(new Mounstruo("Gigante", 1800, 2500, 9, ""));
        mazo.add(new Mounstruo("Ciclope", 2800, 2000, 11, ""));
        mazo.add(new Mounstruo("Piraña", 700, 1000, 1, ""));
        mazo.add(new Mounstruo("Principe", 800, 800, 1, ""));
        mazo.add(new Mounstruo("Principe Oscuro", 1200, 1000, 2, ""));
        mazo.add(new Mounstruo("Rey Esqueleto", 2500, 2900, 12, ""));
        mazo.add(new Mounstruo("Caballero Dorado", 1700, 800, 7, ""));
        mazo.add(new Mounstruo("Golem Oscuro", 2300, 2200, 8, ""));
        mazo.add(new Mounstruo("Leon", 2100, 2500, 8, ""));
        mazo.add(new Mounstruo("Mago Oscuro", 1500, 1200, 5, ""));
        mazo.add(new Mounstruo("Mago Electrico", 1000, 1100, 1, ""));
        mazo.add(new Mounstruo("Furia Nocturna", 2600, 2800, 11, ""));
        mazo.add(new Mounstruo("Duende", 2500, 2000, 7, ""));
        mazo.add(new Mounstruo("Anguila", 1400, 1400, 4, ""));
        mazo.add(new Mounstruo("Lombris Sangrienta", 1900, 2000, 8, ""));
        mazo.add(new Mounstruo("Araña Aguja", 2200, 2000, 6, ""));
        mazo.add(new Mounstruo("Sativa", 2200, 2800, 10, ""));
        mazo.add(new Mounstruo("Golem", 2000, 2500, 8, ""));

        CartaMagica c1 = new CartaMagica("Curacion", "", new EfectoCuracion(300));
        CartaMagica c2 = new CartaMagica("Pot of Greed", "", new PotOfGreed());
        CartaMagica c3 = new CartaMagica("Aumento", "", new AumentoAtaque(500));
        CartaMagica c4 = new CartaMagica("Danio", "", new DanioInstantaneo(400));
        CartaMagica c5 = new CartaMagica("Drenaje", "", new EfectoDrenaje(300));
        CartaMagica c6 = new CartaMagica("Debilitar", "", new EfectoDebilidad(400));
        CartaMagica c7 = new CartaMagica("Doble Ataque", "", new DobleAtaque());
        CartaMagica c8 = new CartaMagica("RoboCuracion", "", new RoboDefinitivo());
        CartaMagica c9 = new CartaMagica("Reset", "", new ResetAtk());
        CartaMagica c10 = new CartaMagica("Escudo", "", new Escudo(200));
    

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
        jugador1.agregarCarta(magia2);
        jugador2.agregarCarta(mounstruo3);
        jugador1.jugarMagia(magia2);
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
