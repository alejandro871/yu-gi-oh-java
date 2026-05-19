package vista;

import juego.Juego;
import jugadores.Jugador;
import cartas.Carta;
import cartas.Mounstruo;

import java.util.Scanner;

public class Vista {

    private Scanner scanner;

    public Vista() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("      ¡Bienvenido al Duelo de Cartas!      ");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
    }

    public void mostrarTituloRegistro() {
        System.out.println("-----------------------");
        System.out.println("Registro de duelistas");
        System.out.println("-----------------------");
    }

    public String pedirNombreDuelista(int numero) {
        System.out.print(" Ingresa el nombre del Duelista " + numero + ": ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "Duelista " + numero;
        }
        return nombre;
    }

    public void mostrarVs(String n1, String n2) {
        System.out.println(" ¡" + n1 + " VS " + n2 + "! ");
        System.out.println(" Que comience el duelo ");
    }

    public void pausar() {
        System.out.print(" Presiona ENTER para continuar... ");
        scanner.nextLine();
    }

    public void pausarTurno() {
        System.out.print(" Presiona ENTER para continuar al siguiente turno... ");
        scanner.nextLine();
    }

    public void mostrarTurno(int turnoNum, String nombre) {
        System.out.println("------------------");
        System.out.println(" TURNO: " + turnoNum + " - " + nombre.toUpperCase());
        System.out.println("------------------");
    }

    public void mostrarFaseRobo() {
        System.out.println("------ Fase Robo ------");
    }

    public void mostrarFasePrincipal() {
        System.out.println("-----Fase principal-----");
    }

    public void mostrarFaseBatalla() {
        System.out.println("-----Fase batalla-----");
    }

    public void mostrarEstadoCompleto(Juego juego) {
        Jugador actual = juego.getJugadorActual();
        Jugador enemigo = juego.getJugadorEnemigo();

        System.out.println("-----------------------------");
        System.out.println("      ESTADO DEL CAMPO       ");
        System.out.println("-----------------------------");

        System.out.printf(" %-20s LP: %5d  Mazo: %2d%n",
            actual.getNombre(), actual.getVida(), actual.getCartasMazo());

        System.out.println(" Campo: ");
        if (actual.getCampo().isEmpty()) {
            System.out.println("   (sin monstruos)  ");
        } else {
            for (Mounstruo m : actual.getCampo()) {
                System.out.println("  - " + m.getNombre()
                        + "  ATK: " + m.getAtk() + "  DEF: " + m.getDef());
            }
        }

        System.out.println("---------------------------");

        System.out.printf(" %-20s LP: %5d  Mazo: %2d%n",
                enemigo.getNombre(), enemigo.getVida(), enemigo.getCartasMazo());

        System.out.println(" Campo: ");
        if (enemigo.getCampo().isEmpty()) {
            System.out.println("  (sin monstruos)  ");
        } else {
            for (Mounstruo m : enemigo.getCampo()) {
                System.out.println(" - " + m.getNombre()
                        + "  ATK:" + m.getAtk() + "  DEF:" + m.getDef());
            }
        }

        System.out.println("-------------------------------");
    }

    public void mostrarManoConTipo(Jugador jugador) {
        System.out.println(" Mano de " + jugador.getNombre() + ":");
        for (int i = 0; i < jugador.getMano().size(); i++) {
            Carta c = jugador.getMano().get(i);
            String tipo;
            String detalle;

            if (c instanceof Mounstruo) {
                Mounstruo m = (Mounstruo) c;
                tipo = " [Monstruo] ";
                detalle = " ATK: " + m.getAtk() + " DEF: " + m.getDef()
                        + " Niv: " + m.getNivel();
            } else {
                tipo = " [Mágica] ";
                detalle = " -- " + c.getDescripcion().trim();
            }

            System.out.println("  [" + (i + 1) + "] " + tipo + " " + c.getNombre() + detalle);
        }
    }

    public int mostrarMenuFasePrincipal() {
        System.out.println(" ¿Qué deseas hacer? ");
        System.out.println("  [1] Jugar una carta ");
        System.out.println("  [2] Pasar (no jugar carta este turno) ");
        System.out.print(" Tu elección: ");
        return leerEntero(1, 2);
    }

    public void mostrarManoVacia() {
        System.out.println(" (Mano vacía — no puedes jugar cartas) ");
    }

    public void mostrarPasarTurno(String nombre) {
        System.out.println(" " + nombre + " decide no jugar carta ");
    }

    public int mostrarMenuSeleccionCarta(Jugador actual) {
        System.out.println("¿Qué carta quieres jugar? ");
        for (int i = 0; i < actual.getMano().size(); i++) {
            Carta c = actual.getMano().get(i);
            String tipo = (c instanceof Mounstruo) ? " [Monstruo] " : " [Mágica]  ";
            System.out.println("  [" + (i + 1) + "] " + tipo + " " + c.getNombre());
        }
        System.out.println("  [0] Cancelar");
        System.out.print(" Tu elección: ");
        return leerEntero(0, actual.getMano().size());
    }

    public void mostrarAccionCancelada() {
        System.out.println(" Acción cancelada ");
    }

    public int mostrarMenuBatalla() {
        System.out.println(" ¿Deseas atacar este turno? ");
        System.out.println("  [1] Si, atacar ");
        System.out.println("  [2] No atacar (pasar)");
        System.out.print(" Tu elección: ");
        return leerEntero(1, 2);
    }

    public void mostrarNoAtacar(String nombre) {
        System.out.println(" " + nombre + " decide no atacar ");
    }

    public void mostrarPrimerTurnoSinAtaque() {
        System.out.println(" Primer turno: no se puede atacar ");
    }

    public void mostrarSinMonstruosAtaque() {
        System.out.println(" No tienes monstruos en campo para atacar ");
    }

    public int mostrarMenuAtacante(Jugador actual) {
        System.out.println(" Elige el monstruo atacante: ");
        for (int i = 0; i < actual.getCampo().size(); i++) {
            Mounstruo m = actual.getCampo().get(i);
            String yaAtaco = m.yaAtaco() ? " [Ya ataco]" : "";
            System.out.println("  [" + (i + 1) + "] " + m.getNombre()
                    + " ATK:" + m.getAtk() + " DEF:" + m.getDef() + yaAtaco);
        }
        System.out.println("  [0] Cancelar ataque ");
        System.out.print(" Tu eleccion: ");
        return leerEntero(0, actual.getCampo().size());
    }

    public void mostrarAtaqueCancelado() {
        System.out.println(" Ataque cancelado.");
    }

    public void mostrarYaAtaco() {
        System.out.println(" Ese monstruo ya ataco este turno ");
    }

    public int mostrarMenuDefensor(Jugador enemigo) {
        System.out.println(" Elige el objetivo del ataque: ");
        for (int i = 0; i < enemigo.getCampo().size(); i++) {
            Mounstruo m = enemigo.getCampo().get(i);
            System.out.println("  [" + (i + 1) + "] " + m.getNombre()
                    + " ATK:" + m.getAtk() + " DEF:" + m.getDef());
        }
        System.out.println("  [0] Cancelar");
        System.out.print(" Tu eleccion: ");
        return leerEntero(0, enemigo.getCampo().size());
    }

    public void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos) {
        String ganador = (j1.getVida() > 0) ? j1.getNombre() : j2.getNombre();
        String perdedor = (j1.getVida() > 0) ? j2.getNombre() : j1.getNombre();

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
        System.out.printf("  %-20s LP finales: %5d%n",
                j1.getNombre(), j1.getVida());
        System.out.printf("  %-20s LP finales: %5d%n",
                j2.getNombre(), j2.getVida());
        System.out.println("  Duracion: " + turnos + " turnos ");
        System.out.println("╚═════════════════════════════════════╝");
    }

    public void cerrar() {
        scanner.close();
    }

    private int leerEntero(int min, int max) {
        while (true) {
            try {
                String linea = scanner.nextLine().trim();
                int valor = Integer.parseInt(linea);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.print(" Opción invalida. Elige entre "
                        + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print(" Entrada invalida. Ingresa un numero: ");
            }
        }
    }
}
