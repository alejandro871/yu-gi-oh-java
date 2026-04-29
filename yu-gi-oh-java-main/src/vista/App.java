package vista;

import cartas.Carta;
import cartas.CartaMagica;
import cartas.CartaTrampa;
import cartas.Monstruo;
import datos.GestorEstadisticas;
import jugadores.Jugador;
import juego.Mazo;
import juego.Juego;

public class App {

    private static final Vista vista = new InterfazGrafica();
    private static GestorEstadisticas gestorStats = new GestorEstadisticas();

    public static void main(String[] args) {

        vista.mostrarBienvenida();

        // Si se pasan argumentos, usarlos; si no, pedir interactivamente
        String nombre1, nombre2;
        
        if (args.length >= 2) {
            nombre1 = args[0];
            nombre2 = args[1];
        } else {
            nombre1 = obtenerNombreJugador("Ingresa el nombre del Duelista 1:");
            nombre2 = obtenerNombreJugador("Ingresa el nombre del Duelista 2:");
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

            Jugador actual = juego.getJugadorActual();
            Jugador enemigo = juego.getJugadorEnemigo();

            vista.mostrarTurno(turnosJugados + 1, actual.getNombre());

            // Fase de robo
            vista.mostrarFase("Fase Robo");
            boolean puedeContinuar = actual.robarCarta();
            if (!puedeContinuar) {
                break; // Mazo vacío, derrota
            }

            vista.mostrarEstadoCompleto(juego);
            vista.pausar();

            // Fase principal
            vista.mostrarFase("Fase principal");
            ejecutarFasePrincipal(juego, actual);

            // Fase de batalla
            vista.mostrarFase("Fase batalla");
            ejecutarFaseBatalla(juego, actual, enemigo);

            // Fase final
            juego.faseFinal();

            vista.mostrarEstadoCompleto(juego);
            turnosJugados++;

            if (juego.hayGanador()) break;

            vista.pausar();
        }

        // Registrar estadísticas
        boolean j1Gano = j1.getVida() > 0;
        gestorStats.registrarPartida(j1.getNombre(), j1Gano, true);
        gestorStats.registrarPartida(j2.getNombre(), !j1Gano, false);
        
        vista.mostrarPantallaFinal(juego, j1, j2, turnosJugados);
        vista.mostrarMensaje("Estadisticas actualizadas!");
    }

    private static void ejecutarFasePrincipal(Juego juego, Jugador actual) {
        if (actual.getMano().isEmpty()) {
            vista.mostrarMensaje(" (Mano vacía — no puedes jugar cartas) ");
            return;
        }

        vista.mostrarManoConTipo(actual);

        vista.mostrarMensaje(" ¿Qué deseas hacer? ");
        vista.mostrarOpciones(new String[]{"Invocar Monstruo", "Activar Carta Mágica", "Poner Carta Trampa", "Pasar (no jugar carta este turno)"});

        int opcion = vista.leerEntero(1, 4);

        if (opcion == 4) {
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

        switch (opcion) {
            case 1:
                if (cartaElegida instanceof Monstruo) {
                    actual.jugarMonstruo((Monstruo) cartaElegida);
                    vista.mostrarCartaJugada(actual.getNombre(), cartaElegida.getNombre());
                } else {
                    vista.mostrarMensaje("La carta seleccionada no es un monstruo.");
                }
                break;
            case 2:
                if (cartaElegida instanceof CartaMagica) {
                    CartaMagica magica = (CartaMagica) cartaElegida;
                    actual.jugarMagia(magica);
                    vista.mostrarCartaJugada(actual.getNombre(), cartaElegida.getNombre());
                } else {
                    vista.mostrarMensaje("La carta seleccionada no es una carta mágica.");
                }
                break;
            case 3:
                if (cartaElegida instanceof CartaTrampa) {
                    actual.ponerTrampa((CartaTrampa) cartaElegida);
                    vista.mostrarCartaJugada(actual.getNombre(), cartaElegida.getNombre());
                } else {
                    vista.mostrarMensaje("La carta seleccionada no es una carta trampa.");
                }
                break;
            default:
                vista.mostrarMensaje("Opción inválida.");
                break;
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

        vista.mostrarMensaje(" Elige el monstruo atacante: ");
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

    private static String obtenerNombreJugador(String mensaje) {
        String nombre = javax.swing.JOptionPane.showInputDialog(null, mensaje, "Registro de Duelistas", javax.swing.JOptionPane.QUESTION_MESSAGE);
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Duelista";
        }
        return nombre.trim();
    }
}

