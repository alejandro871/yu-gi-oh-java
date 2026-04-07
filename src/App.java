

import jugadores.Jugador;
import juego.Mazo;
import juego.Juego;
import cartas.Carta;
import cartas.Mounstruo;
import cartas.CartaMagica;


public class App {

    public static void main(String[] args) {

        Jugador j1 = new Jugador("Alejo");
        Jugador j2 = new Jugador("Ojela");

        
        Mazo.repartir(j1, j2);

        Juego juego = new Juego(j1, j2);
        juego.estadoJuego();

        int turnosJugados = 0;

         while (!juego.hayGanador() && turnosJugados < 10) { //hasta 10 turnos o hasta que haya ganador 

            Jugador actual = juego.getJugadorActual();

            // jugamos la primera carta disponible en mano automáticamente
            if (!actual.getMano().isEmpty()) {
                Carta cartaAJugar = actual.getMano().get(0);

                if (cartaAJugar instanceof Mounstruo) {

                    actual.jugarMonstruo((Mounstruo) cartaAJugar);

                } else if (cartaAJugar instanceof CartaMagica) {

                    actual.jugarMagia((CartaMagica) cartaAJugar);
                }
            }

            boolean continuarJuego = juego.ejecutarTurnoCompleto();

            if(!continuarJuego);

            juego.estadoJuego();

            turnosJugados++;

         }

         System.out.println(" Fin de los turnos jugados ");

        }

}
