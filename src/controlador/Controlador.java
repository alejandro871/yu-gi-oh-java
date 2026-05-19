package controlador;

import modelo.juego.Juego;
import modelo.juego.Mazo;
import modelo.jugadores.Jugador;
import modelo.cartas.Carta;
import modelo.cartas.Mounstruo;
import modelo.cartas.CartaMagica;
import vista.Vista;

import modelo.Mensajes;
import modelo.efectos.destruirMountruo;

public class Controlador {

    private Vista vista;

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    private void drenarMensajes() {
        vista.mostrarMensajes(Mensajes.obtenerTodos());
    }

    public void iniciarJuego() {
        vista.mostrarBienvenida();
        vista.mostrarTituloRegistro();

        String nombre1 = vista.pedirNombreDuelista(1);
        String nombre2 = vista.pedirNombreDuelista(2);

        vista.mostrarVs(nombre1, nombre2);
        drenarMensajes();
        vista.pausar();

        Jugador j1 = new Jugador(nombre1);
        Jugador j2 = new Jugador(nombre2);

        Mazo.repartir(j1, j2);
        drenarMensajes();

        Juego juego = new Juego(j1, j2);
        drenarMensajes();

        System.out.println();
        vista.mostrarEstadoCompleto(juego);
        drenarMensajes();
        vista.pausar();

        int turnosJugados = 0;

        while (!juego.hayGanador()) {
            Jugador actual = juego.getJugadorActual();
            Jugador enemigo = juego.getJugadorEnemigo();

            vista.mostrarTurno(turnosJugados + 1, actual.getNombre());

            vista.mostrarFaseRobo();
            boolean puedeContinuar = juego.faseRobo();
            drenarMensajes();
            if (!puedeContinuar) {
                break;
            }

            vista.mostrarEstadoCompleto(juego);
            drenarMensajes();

            vista.mostrarFasePrincipal();
            ejecutarFasePrincipal(actual, enemigo);

            vista.mostrarFaseBatalla();
            ejecutarFaseBatalla(juego, actual, enemigo);

            juego.faseFinal();
            drenarMensajes();

            vista.mostrarEstadoCompleto(juego);
            drenarMensajes();

            turnosJugados++;

            if (juego.hayGanador()) break;

            vista.pausarTurno();
        }

        vista.mostrarPantallaFinal(juego, j1, j2, turnosJugados);
        drenarMensajes();
        vista.cerrar();
    }

    private void ejecutarFasePrincipal(Jugador actual, Jugador enemigo) {
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
            if (magica.getEfecto() instanceof destruirMountruo) {
                ((destruirMountruo) magica.getEfecto()).setEnemigo(enemigo);
            }
            actual.jugarMagia(magica);
        }
        drenarMensajes();
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
            enemigo.colocarComoDefensor(eleDefensor - 1);
        }

        actual.atacarJugador(enemigo);
        drenarMensajes();
    }
}
