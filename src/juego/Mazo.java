package juego;

import cartas.Carta;
import cartas.Mounstruo;
import cartas.CartaMagica;
import efectos.*;
import jugadores.Jugador;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

   
    public static ArrayList<Carta> crearMazo() {

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

    
        mazo.add(new CartaMagica("Pot of Greed",
                "Roba 2 cartas del mazo ",
                new PotOfGreed()));

        mazo.add(new CartaMagica("Curación",
                "Recupera 1500 LP ",
                new EfectoCuracion(1500)));

        mazo.add(new CartaMagica("Daño",
                "Inflige 800 de daño directo ",
                new DanioInstantaneo(800)));

        mazo.add(new CartaMagica("Aumento ataque",
                "Aumenta el ATK de un monstruo en 700 ",
                new AumentoAtaque(700)));

        mazo.add(new CartaMagica("Escudo",
                "Aumenta la DEF de un monstruo en 900 ",
                new Escudo(900)));

        mazo.add(new CartaMagica("Doble ataque",
                "Duplica el ATK de un monstruo ",
                new DobleAtaque()));

        mazo.add(new CartaMagica("Debilidad",
                "Reduce el ATK de un monstruo en 500 ",
                new EfectoDebilidad(500)));

        mazo.add(new CartaMagica("Drenaje",
                "Drena 600 LP del objetivo ",
                new EfectoDrenaje(600)));

        mazo.add(new CartaMagica("Robo Curacion",
                "Roba 1 carta y gana 300 LP ",
                new RoboDefinitivo()));

        mazo.add(new CartaMagica("Reset",
                "Restaura el ATK original de un monstruo ",
                new ResetAtk()));

        System.out.println("Mazo creado con " + mazo.size() + " cartas ");

        return mazo;
    }

    // Mezcla el mazo 
    public static void repartir(Jugador j1, Jugador j2) {

        ArrayList<Carta> mazo = crearMazo();

        Collections.shuffle(mazo); // Reparticion aleatoria

        for (int i = 0; i < 20; i++) {
            j1.agregarCarta(mazo.get(i));       // primeras 20 jugador 1
        }

        for (int i = 20; i < 40; i++) {
            j2.agregarCarta(mazo.get(i));       // siguientes 20 jugador 2
        }

        System.out.println("Cartas repartidas: 20 para " + j1.getNombre()
                + " y 20 para " + j2.getNombre());
    }
}