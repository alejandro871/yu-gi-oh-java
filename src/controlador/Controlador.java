package controlador;

import juego.Juego;
import juego.Mazo;
import jugadores.Jugador;
import cartas.Carta;
import cartas.Mounstruo;
import cartas.CartaMagica;
import vista.Vista;

import java.util.Collections;

public class Controlador {

    private Vista vista;

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    public void iniciarJuego() {
        vista.mostrarBienvenida();
        vista.mostrarTituloRegistro();

        String nombre1 = vista.pedirNombreDuelista(1);
        String nombre2 = vista.pedirNombreDuelista(2);

        vista.mostrarVs(nombre1, nombre2);
        vista.pausar();

        Jugador j1 = new Jugador(nombre1);
        Jugador j2 = new Jugador(nombre2);

        Mazo.repartir(j1, j2);

        Juego juego = new Juego(j1, j2);

        System.out.println();
        vista.mostrarEstadoCompleto(juego);
        vista.pausar();

        int turnosJugados = 0;

        while (!juego.hayGanador()) {
            Jugador actual = juego.getJugadorActual();
            Jugador enemigo = juego.getJugadorEnemigo();

            vista.mostrarTurno(turnosJugados + 1, actual.getNombre());

            vista.mostrarFaseRobo();
            boolean puedeContinuar = juego.faseRobo();
            if (!puedeContinuar) {
                break;
            }

            vista.mostrarEstadoCompleto(juego);

            vista.mostrarFasePrincipal();
            ejecutarFasePrincipal(juego, actual);

            vista.mostrarFaseBatalla();
            ejecutarFaseBatalla(juego, actual, enemigo);

            juego.faseFinal();
            vista.mostrarEstadoCompleto(juego);

            turnosJugados++;

            if (juego.hayGanador()) break;

            vista.pausarTurno();
        }

        vista.mostrarPantallaFinal(juego, j1, j2, turnosJugados);
        vista.cerrar();
    }

    private void ejecutarFasePrincipal(Juego juego, Jugador actual) {
        if (actual.getMano().isEmpty()) {
            vista.mostrarManoVacia();
            return;
        }

        vista.mostrarManoConTipo(actual);

        int opcion = vista.mostrarMenuFasePrincipal();

        if (opcion == 2) {
            vista.mostrarPasarTurno(actual.getNombre());
            return;
        }

        int indice = vista.mostrarMenuSeleccionCarta(actual);

        if (indice == 0) {
            vista.mostrarAccionCancelada();
            return;
        }

        Carta cartaElegida = actual.getMano().get(indice - 1);

        if (cartaElegida instanceof Mounstruo) {
            actual.jugarMonstruo((Mounstruo) cartaElegida);
        } else if (cartaElegida instanceof CartaMagica) {
            CartaMagica magica = (CartaMagica) cartaElegida;
            actual.jugarMagia(magica);
        }
    }

    private void ejecutarFaseBatalla(Juego juego, Jugador actual, Jugador enemigo) {
        if (juego.esPrimerTurno()) {
            vista.mostrarPrimerTurnoSinAtaque();
            return;
        }

        if (actual.getCampo().isEmpty()) {
            vista.mostrarSinMonstruosAtaque();
            return;
        }

        int opcion = vista.mostrarMenuBatalla();
        if (opcion == 2) {
            vista.mostrarNoAtacar(actual.getNombre());
            return;
        }

        int eleAtacante = vista.mostrarMenuAtacante(actual);
        if (eleAtacante == 0) {
            vista.mostrarAtaqueCancelado();
            return;
        }

        Mounstruo atacante = actual.getCampo().get(eleAtacante - 1);

        if (atacante.yaAtaco()) {
            vista.mostrarYaAtaco();
            return;
        }

        if (!enemigo.getCampo().isEmpty()) {
            int eleDefensor = vista.mostrarMenuDefensor(enemigo);
            if (eleDefensor == 0) {
                vista.mostrarAtaqueCancelado();
                return;
            }
            Collections.swap(enemigo.getCampo(), 0, eleDefensor - 1);
        }

        actual.atacarJugador(enemigo);
    }
}
