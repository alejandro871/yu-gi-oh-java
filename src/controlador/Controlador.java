package controlador;

import modelo.Carta;
import modelo.CartaMagica;
import modelo.CartaTrampa;
import modelo.Monstruo;
import modelo.Jugador;
import modelo.Mazo;
import modelo.Juego;
import vista.Vista;

import modelo.Mensajero;
import java.util.List;

public class Controlador {

    private Vista vista;
    private Juego juego;
    private Jugador j1;
    private Jugador j2;

    public Controlador(Vista vista, String nombre1, String nombre2) {
        this.vista = vista;
        this.j1 = new Jugador(nombre1);
        this.j2 = new Jugador(nombre2);
    }

    private void mostrarMensajesPendientes() {
        List<String> mensajes = Mensajero.obtener();
        for (String msg : mensajes) {
            vista.mostrarMensaje(msg);
        }
    }

    public void iniciarJuego() {
        vista.mostrarBienvenida();

        vista.mostrarMensaje(" Duelista 1: " + j1.getNombre());
        vista.mostrarMensaje(" Duelista 2: " + j2.getNombre());
        vista.mostrarMensaje(" " + j1.getNombre() + " VS " + j2.getNombre() + "! ");
        vista.mostrarMensaje(" Que comience el duelo ");

        vista.pausar();

        Mazo.repartir(j1, j2);
        mostrarMensajesPendientes();

        juego = new Juego(j1, j2);
        mostrarMensajesPendientes();

        vista.mostrarEstadoCompleto(juego);
        vista.pausar();

        int turnosJugados = 0;

        while (!juego.hayGanador()) {

            Jugador actual = juego.getJugadorActual();
            Jugador enemigo = juego.getJugadorEnemigo();

            vista.mostrarTurno(turnosJugados + 1, actual.getNombre());

            vista.mostrarFase("Fase Robo");
            boolean puedeContinuar = actual.robarCarta();
            mostrarMensajesPendientes();
            if (!puedeContinuar) {
                break;
            }

            vista.mostrarEstadoCompleto(juego);
            vista.pausar();

            vista.mostrarFase("Fase principal");
            ejecutarFasePrincipal(actual);

            vista.mostrarFase("Fase batalla");
            ejecutarFaseBatalla(actual, enemigo);

            juego.faseFinal();
            mostrarMensajesPendientes();

            vista.mostrarEstadoCompleto(juego);
            turnosJugados++;

            if (juego.hayGanador()) break;

            vista.pausar();
        }

        vista.mostrarPantallaFinal(juego, j1, j2, turnosJugados);
    }

    private void ejecutarFasePrincipal(Jugador actual) {
        if (actual.getMano().isEmpty()) {
            vista.mostrarMensaje(" (Mano vacia - no puedes jugar cartas) ");
            return;
        }

        vista.mostrarManoConTipo(actual);

        vista.mostrarMensaje(" Que deseas hacer? ");
        vista.mostrarOpciones(new String[]{"Jugar una carta", "Pasar (no jugar carta este turno)"});

        int opcion = vista.leerEntero(1, 2);

        if (opcion == 2) {
            vista.mostrarMensaje(" " + actual.getNombre() + " decide no jugar carta ");
            return;
        }

        vista.mostrarMensaje("Que carta quieres jugar? ");
        vista.mostrarMensaje("  [0] Cancelar");

        int indice = vista.leerEntero(0, actual.getMano().size());

        if (indice == 0) {
            vista.mostrarMensaje(" Accion cancelada ");
            return;
        }

        Carta cartaElegida = actual.getMano().get(indice - 1);

        if (cartaElegida instanceof Monstruo) {
            Monstruo monstruo = (Monstruo) cartaElegida;

            if (monstruo.getNivel() > 4) {
                if (!actual.tieneMonstruosParaSacrificio()) {
                    vista.mostrarMensaje(" No tienes monstruos en campo para sacrificar!");
                    vista.mostrarMensaje(" Este monstruo requiere sacrificio (Nivel " + monstruo.getNivel() + ")");
                    return;
                }
                vista.mostrarMensaje(" Este monstruo requiere sacrificio (Nivel " + monstruo.getNivel() + ")");
                vista.mostrarMensaje(" Selecciona un monstruo de tu campo para sacrificar:");
                int indiceSacrificio = vista.seleccionarSacrificio(actual, "Elige el monstruo a sacrificar:");

                if (indiceSacrificio == 0) {
                    vista.mostrarMensaje(" Invocacion cancelada");
                    return;
                }

                Monstruo sacrificio = actual.getCampo().get(indiceSacrificio - 1);
                actual.jugarMonstruo(monstruo, sacrificio);
            } else {
                actual.jugarMonstruo(monstruo);
            }
            mostrarMensajesPendientes();
            vista.mostrarCartaJugada(actual.getNombre(), cartaElegida.getNombre());
        } else if (cartaElegida instanceof CartaMagica) {
            CartaMagica magica = (CartaMagica) cartaElegida;
            actual.jugarMagia(magica);
            mostrarMensajesPendientes();
            vista.mostrarCartaJugada(actual.getNombre(), cartaElegida.getNombre());
        } else if (cartaElegida instanceof CartaTrampa) {
            CartaTrampa trampa = (CartaTrampa) cartaElegida;
            actual.jugarTrampa(trampa);
            mostrarMensajesPendientes();
            vista.mostrarCartaJugada(actual.getNombre(), cartaElegida.getNombre());
        }
    }

    private void ejecutarFaseBatalla(Jugador actual, Jugador enemigo) {
        if (juego.esPrimerTurno()) {
            vista.mostrarMensaje(" Primer turno: no se puede atacar ");
            return;
        }

        if (actual.getCampo().isEmpty()) {
            vista.mostrarMensaje(" No tienes monstruos en campo para atacar ");
            return;
        }

        vista.mostrarMensaje(" Deseas atacar este turno? ");
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
        if (eleAtacante < 0 || eleAtacante > actual.getCampo().size()) {
            vista.mostrarMensaje(" Seleccion invalida de atacante. Ataque cancelado.");
            return;
        }

        Monstruo atacante = actual.getCampo().get(eleAtacante - 1);

        if (atacante.yaAtaco()) {
            vista.mostrarMensaje(" Ese monstruo ya ataco este turno ");
            return;
        }

        // Verificar que el atacante todavía está en el campo antes de continuar
        if (!actual.getCampo().contains(atacante)) {
            vista.mostrarMensaje(" El monstruo atacante ya no está en el campo (destruido por efecto).");
            return;
        }

        if (!enemigo.getCampo().isEmpty()) {
            int eleDefensor = vista.seleccionarMonstruoCampo(enemigo, "Elige el objetivo del ataque:");
            if (eleDefensor == 0) {
                vista.mostrarMensaje(" Ataque cancelado.");
                return;
            }
            if (eleDefensor < 0 || eleDefensor > enemigo.getCampo().size()) {
                vista.mostrarMensaje(" Seleccion invalida de objetivo. Ataque cancelado.");
                return;
            }
            if (eleDefensor > 1) {
                enemigo.swapCampo(0, eleDefensor - 1);
            }
        } else {
            vista.mostrarMensaje(" Ataque directo al jugador enemigo!");
        }

        if (enemigo.tieneTrampas()) {
            vista.mostrarMensaje(" El oponente tiene trampas activadas!");
            // Usar la referencia 'atacante' guardada, no acceder por índice
            enemigo.activarTrampas(actual, atacante);
            mostrarMensajesPendientes();

            // Verificar de nuevo que el atacante sobrevivió a las trampas
            if (!actual.getCampo().contains(atacante)) {
                vista.mostrarMensaje(" El monstruo fue destruido por las trampas. Ataque cancelado.");
                return;
            }
        }

        // Usar la versión con parámetro para especificar el atacante exacto
        actual.atacarJugador(enemigo, atacante);
        mostrarMensajesPendientes();
    }
}
