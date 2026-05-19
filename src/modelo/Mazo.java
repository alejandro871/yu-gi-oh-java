package modelo;

import modelo.Carta;
import modelo.Monstruo;
import modelo.CartaMagica;
import modelo.CartaTrampa;
import modelo.*;
import modelo.Jugador;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

public static ArrayList<Carta> crearMazo() {

        ArrayList<Carta> mazo = new ArrayList<>();

        mazo.add(new Monstruo("Dragon Blanco", 2200, 2000, 8, "Monstruo Legendary que 控制a los elementos"));
        mazo.add(new Monstruo("Guerrero", 900, 1000, 4, "Guerrero cuerpo a cuerpo"));
        mazo.add(new Monstruo("Mago", 1500, 1400, 5, "Maestro de la magia arcana"));
        mazo.add(new Monstruo("Bestia", 2000, 1800, 6, "Bestia feroz de las montañas"));
        mazo.add(new Monstruo("Titan", 2100, 1900, 7, "Gigante de roca primordial"));
        mazo.add(new Monstruo("Caballero", 1100, 900, 2, "Caballero noble en arms"));
        mazo.add(new Monstruo("Zombie", 800, 900, 1, "No-muerto que vuelve de la muerte"));
        mazo.add(new Monstruo("Vampiro", 2600, 1000, 9, "Señor oscuro que succiona vida"));
        mazo.add(new Monstruo("Pitbull", 2800, 3000, 12, "Bestia de ataque feroz"));
        mazo.add(new Monstruo("Golem", 2800, 2500, 10, "Autómata de poder immense"));
        mazo.add(new Monstruo("Esqueleto", 1000, 1200, 3, "No-muerto skeletal"));
        mazo.add(new Monstruo("Tarantula", 1100, 900, 4, "Araña gigante venenosa"));
        mazo.add(new Monstruo("Gigante", 1800, 2500, 9, "Coloso de gran poder"));
        mazo.add(new Monstruo("Ciclope", 2800, 2000, 11, "Gigante de un solo ojo"));
        mazo.add(new Monstruo("Piraña", 700, 1000, 1, "Pez predador rápido"));
        mazo.add(new Monstruo("Principe", 800, 800, 1, "Noble de низкий rango"));
        mazo.add(new Monstruo("Principe Oscuro", 1200, 1000, 2, "Herdeiro del darkness"));
        mazo.add(new Monstruo("Rey Esqueleto", 2500, 2900, 12, "Monarca no-muerto"));
        mazo.add(new Monstruo("Caballero Dorado", 1700, 800, 7, "Caballero blindado en oro"));
        mazo.add(new Monstruo("Golem Oscuro", 2300, 2200, 8, "Criatura de sombra y piedra"));
        mazo.add(new Monstruo("Leon", 2100, 2500, 8, "Rey de las bestias"));
        mazo.add(new Monstruo("Mago Oscuro", 1500, 1200, 5, "Hechicero de artes negras"));
        mazo.add(new Monstruo("Mago Electrico", 1000, 1100, 1, "Mago del rayo"));
        mazo.add(new Monstruo("Furia Nocturna", 2600, 2800, 11, "Bestia de la noche eternal"));
        mazo.add(new Monstruo("Duende", 2500, 2000, 7, "Espíritu travieso del bosque"));
        mazo.add(new Monstruo("Anguila", 1400, 1400, 4, "Ser eléctrico marino"));
        mazo.add(new Monstruo("Lombriz Sangrienta", 1900, 2000, 8, "Gusano parasitic"));
        mazo.add(new Monstruo("Araña Aguja", 2200, 2000, 6, "Araña de colmillos mortales"));
        mazo.add(new Monstruo("Sativa", 2200, 2800, 10, "Planta carnívora gigante"));
        mazo.add(new Monstruo("Golem", 2000, 2500, 8, "Constructo de piedra"));


        mazo.add(new CartaMagica("Pot of Greed",
                " Roba 2 cartas del mazo ",
                new PotOfGreed()));

        mazo.add(new CartaMagica(" Curacion ",
                " Recupera 800 LP ",
                new EfectoCuracion(800)));

        efectoTemporalAtk terraforming = new efectoTemporalAtk(500);
        mazo.add(new CartaMagica("Terraforming",    
                " Ataque de un monstruo propio +500 por este turno ",
                terraforming));

        mazo.add(new CartaMagica(" Orden de Destruccion ",
                " Destruye el monstruo más débil del oponente ",    
                new destruirMonstruo()));

        mazo.add(new CartaMagica(" Daño ",
                " Inflige 800 de daño directo ",
                new DanioInstantaneo(800)));

        mazo.add(new CartaMagica("Aumento ataque",
                " Aumenta el ATK de un monstruo en 700 ",
                new AumentoAtaque(700)));

        mazo.add(new CartaMagica("Escudo",
                " Aumenta la DEF de un monstruo en 900 ",
                new Escudo(900)));

        mazo.add(new CartaMagica("Doble ataque",
                " Duplica el ATK de un monstruo ",
                new DobleAtaque()));

        mazo.add(new CartaMagica("Debilidad",
                " Reduce el ATK de un monstruo en 500 ",
                new EfectoDebilidad(500)));

        mazo.add(new CartaMagica("Drenaje",
                " Drena 600 LP del objetivo ",
                new EfectoDrenaje(600)));

        mazo.add(new CartaMagica(" Robo Curacion ",
                "Roba 1 carta y gana 300 LP ",
                new RoboDefinitivo()));

        /*mazo.add(new CartaMagica("Reset",
                " Restaura el ATK original de un monstruo ",
                new ResetAtk()));*/

        // CARTAS TRAMPA (10 cartas)
        mazo.add(new CartaTrampa("Trampa Destructor",
                " Destruye el monstruo atacante ",
                new TrampaDestructor()));

        mazo.add(new CartaTrampa("Trampa Defensa",
                " Aumenta DEF en 800 ",
                new TrampaDefensa(800)));

        mazo.add(new CartaTrampa("Trampa Bloqueo",
                " Bloquea el ataque enemigo ",
                new TrampaBloqueo()));

        mazo.add(new CartaTrampa("Trampa Devolución",
                " Refleja 700 LP de daño ",
                new TrampaDevolucion(700)));

        mazo.add(new CartaTrampa("Trampa Robo",
                " Descarta una carta del oponente ",
                new TrampaRobo()));

        mazo.add(new CartaTrampa("Trampa Curación",
                " Recupera 600 LP ",
                new TrampaCuracion(600)));

        mazo.add(new CartaTrampa("Trampa Debilidad",
                " Reduce ATK del enemigo en 600 ",
                new TrampaDebilidad(600)));

        mazo.add(new CartaTrampa("Trampa Volteada",
                " Voltea la posición del monstruo ",
                new TrampaVolteada()));

        mazo.add(new CartaTrampa("Trampa Recuperación",
                " Recupera recursos defensivos ",
                new TrampaRecuperacion()));

        mazo.add(new CartaTrampa("Trampa Doble",
                " Duplica DEF temporalmente ",
                new TrampaDoble()));

        Mensajero.add("Mazo creado con " + mazo.size() + " cartas ");

        return mazo;
}


    // Mezcla el mazo 
public static void repartir(Jugador j1, Jugador j2) {

        ArrayList<Carta> mazo = crearMazo();

        Collections.shuffle(mazo); // Reparticion aleatoria

        for (int i = 0; i < 25; i++) {
            j1.agregarCarta(mazo.get(i));       // primeras 25 jugador 1
        }

        for (int i = 25; i < 50; i++) {
            j2.agregarCarta(mazo.get(i));       // siguientes 25 jugador 2
        }

        j1.tomarManoInicial();
        j2.tomarManoInicial();

        Mensajero.add("Cartas repartidas: 25 para " + j1.getNombre()
                + " y 25 para " + j2.getNombre() + " Con 5 en mano y 20 en mazo cada uno ");
}
}
