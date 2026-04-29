

import jugadores.Jugador;
import juego.Mazo;
import juego.Juego;
import cartas.Carta;
import cartas.Monstruo;
import cartas.CartaMagica;
import cartas.CartaTrampa;
import vista.Vista;
import vista.VistaConsola;

import java.util.Scanner;

import datos.GestorEstadisticas;


public class App {

    static Vista vista = new VistaConsola();
    static GestorEstadisticas gestorStats = new GestorEstadisticas();

public static void main(String[] args) {


        vista.mostrarBienvenida();

        // Si se pasan argumentos, usarlos; si no, pedir interactivamente
        String nombre1, nombre2;
        
        if (args.length >= 2) {
            nombre1 = args[0];
            nombre2 = args[1];
        } else {
            vista.mostrarMensaje("-----------------------");
            vista.mostrarMensaje("Registro de duelistas");
            vista.mostrarMensaje("-----------------------");
            nombre1 = vista.leerString("Ingresa el nombre del Duelista 1: ");
            if (nombre1.isEmpty()) nombre1 = "Duelista 1";
            nombre2 = vista.leerString("Ingresa el nombre del Duelista 2: ");
            if (nombre2.isEmpty()) nombre2 = "Duelista 2";
        }

        vista.mostrarMensaje(" ¡" + nombre1 + " VS " + nombre2 + "! ");

        vista.mostrarMensaje(" Que comience el duelo ");

        vista.pausar();

        Jugador j1 = new Jugador(nombre1);
        Jugador j2 = new Jugador(nombre2);

        
        Mazo.repartir(j1, j2);

        Juego juego = new Juego(j1, j2);

        vista.mostrarEstadoCompleto(juego);
        vista.pausar();

        int turnosJugados = 0;

         while (!juego.hayGanador()) {

            Jugador actual   = juego.getJugadorActual();
            Jugador enemigo  = juego.getJugadorEnemigo();
         

            vista.mostrarTurno(turnosJugados + 1, actual.getNombre().toUpperCase());

            vista.mostrarFase("Fase Robo");

            boolean puedeContinuar = juego.faseRobo();

            if (!puedeContinuar) {
                break;
            }

            vista.mostrarEstadoCompleto(juego);

            vista.pausar();

            vista.mostrarFase("Fase principal");
            ejecutarFasePrincipal(juego, actual);

            vista.mostrarFase("Fase batalla");
            ejecutarFaseBatalla(juego, actual, enemigo);

            juego.faseFinal();

            vista.mostrarEstadoCompleto(juego);
            turnosJugados++;

            if (juego.hayGanador()) break;

            vista.pausar();

    }

    vista.mostrarPantallaFinal(juego, j1, j2, turnosJugados);
}

private static void ejecutarFasePrincipal(Juego juego, Jugador actual) {

        if (actual.getMano().isEmpty()) {
            vista.mostrarMensaje(" (Mano vacía — no puedes jugar cartas) ");
            return;
        }

        vista.mostrarManoConTipo(actual);

        vista.mostrarMensaje(" ¿Qué deseas hacer? ");
        vista.mostrarOpciones(new String[]{"Jugar una carta", "Pasar (no jugar carta este turno)"});
        int opcion = vista.leerEntero(1, 2);

        if (opcion == 2) {
            vista.mostrarMensaje(" " + actual.getNombre() + " decide no jugar carta ");
            return;
        }

        vista.mostrarMensaje("¿Qué carta quieres jugar? ");
        vista.mostrarMensaje("  [0] Cancelar");

        int indice = vista.leerEntero(0, actual.getMano().size());

        if (indice == 0) {
            vista.mostrarMensaje(" Acción cancelada ");
            return;
        }

        Carta cartaElegida = actual.getMano().get(indice - 1);

        if (cartaElegida instanceof Monstruo) {
            actual.jugarMonstruo((Monstruo) cartaElegida);

        } else if (cartaElegida instanceof CartaMagica) {

            CartaMagica magica = (CartaMagica) cartaElegida;

            actual.jugarMagia(magica);
        } else if (cartaElegida instanceof CartaTrampa) {

            CartaTrampa trampa = (CartaTrampa) cartaElegida;

            actual.ponerTrampa(trampa);
        }
    }

private static void ejecutarFaseBatalla(Juego juego, Jugador actual, Jugador enemigo) {

        if (juego.esPrimerTurno()) {

            vista.mostrarMensaje(" Primer turno: no se puede atacar ");

            return;
        }

        if (actual.getCampo().isEmpty()) {
            vista.mostrarMensaje(" No tienes monstruos en campo para atacar ");
            return;
        }

        vista.mostrarMensaje(" ¿Deseas atacar este turno? ");
        vista.mostrarOpciones(new String[]{"Si, atacar", "No atacar (pasar)"});
        int opcion = vista.leerEntero(1, 2);

        if (opcion == 2) {
            vista.mostrarMensaje(" " + actual.getNombre() + " decide no atacar ");
            return;
        }

        int eleAtacante = vista.seleccionarMonstruoCampo(actual, "Elige el monstruo atacante:");
        if (eleAtacante == 0) {
            vista.mostrarMensaje(" Ataque cancelado.");
            return;
        }

        cartas.Monstruo atacante = actual.getCampo().get(eleAtacante - 1);

        if (atacante.yaAtaco()) {
            vista.mostrarMensaje(" Ese monstruo ya ataco este turno ");
            return;
        }

        if (!enemigo.getCampo().isEmpty()) {
            int eleDefensor = vista.seleccionarMonstruoCampo(enemigo, "Elige el objetivo del ataque:");
            if (eleDefensor == 0) {
                vista.mostrarMensaje(" Ataque cancelado.");
                return;
            }

            java.util.Collections.swap(enemigo.getCampo(), 0, eleDefensor - 1);
        }

        actual.atacarJugador(enemigo, atacante, vista);
    }

private static void mostrarEstadoCompleto(Juego juego) {

        Jugador j1 = juego.getJugadorActual();
        Jugador j2 = juego.getJugadorEnemigo();

        System.out.println("-----------------------------");
        System.out.println("      ESTADO DEL CAMPO       ");
        System.out.println("-----------------------------");


        System.out.printf(" %-20s LP: %5d  Mazo: %2d%n",

            j1.getNombre(), j1.getVida(), j1.getCartasMazo());

        System.out.println(" Campo: ");

        if (j1.getCampo().isEmpty()) {
            
            System.out.println("   (sin monstruos)  ");
        } else {

            for (cartas.Monstruo m : j1.getCampo()) {
                System.out.println("  - " + m.getNombre()
                        + "  ATK: " + m.getAtk() + "  DEF: " + m.getDef());
            }
        }

        System.out.println("---------------------------");

        System.out.printf(" %-20s LP: %5d  Mazo: %2d%n",

                j2.getNombre(), j2.getVida(), j2.getCartasMazo());

        System.out.println(" Campo: ");

        if (j2.getCampo().isEmpty()) {

            System.out.println("  (sin monstruos)  ");
        } else {

            for (cartas.Monstruo m : j2.getCampo()) {

                System.out.println(" - " + m.getNombre()

                        + "  ATK:" + m.getAtk() + "  DEF:" + m.getDef());
            }
        }

        System.out.println("-------------------------------");
    }

private static void mostrarManoConTipo(Jugador jugador) {

        for (int i = 0; i < jugador.getMano().size(); i++) {

            Carta c = jugador.getMano().get(i);
            String tipo;
            String detalle;

            if (c instanceof Monstruo) {
                Monstruo m = (Monstruo) c;
                tipo    = " [Monstruo] ";
                detalle = " ATK: " + m.getAtk() + " DEF: " + m.getDef()
                        + " Niv: " + m.getNivel();
            } else {
                tipo    = " [Mágica] ";
                detalle = " -- " + c.getDescripcion().trim();
            }

            System.out.println("  [" + (i + 1) + "] " + tipo + " " + c.getNombre() + detalle);
        }
    }

private static void mostrarBienvenida() {

    System.out.println("╔══════════════════════════════════════════╗");
    System.out.println("      ¡Bienvenido al Duelo de Cartas!      ");
    System.out.println("╚══════════════════════════════════════════╝");
    System.out.println();
}

private static void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos) {

        String nombre1 = j1.getNombre();
        String nombre2 = j2.getNombre();
        
        // Registrar estadísticas
        boolean j1Gano = j1.getVida() > 0;
        gestorStats.registrarPartida(nombre1, j1Gano, true);
        gestorStats.registrarPartida(nombre2, !j1Gano, false);

        String ganador = (j1.getVida() > 0) ? nombre1 : nombre2;
        String perdedor = (j1.getVida() > 0) ? nombre2 : nombre1;

        System.out.println();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("            FIN DEL DUELO              ");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println();
        System.out.println(" ¡" + ganador + " gana el duelo! ");
        System.out.println();
        System.out.println("  En hora buana,    ");
        System.out.println("   " + ganador + "!    ");
        System.out.println();
        System.out.println("  " + perdedor + " Lo podias hacer mejor ");
        System.out.println();
        System.out.println("╠═════════════════════════════════════╣");
        System.out.printf ("  %-20s LP finales: %5d%n",
                nombre1, j1.getVida());

        System.out.printf ("  %-20s LP finales: %5d%n",
                nombre2, j2.getVida());

        System.out.println("  Duracion: " + turnos + " turnos ");
        System.out.println("╚═════════════════════════════════════╝");
        
        System.out.println("\nEstadísticas actualizadas!");
    }
}

