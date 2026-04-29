package vista;

import juego.Juego;
import jugadores.Jugador;
import java.util.Scanner;

public class VistaConsola implements Vista {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarBienvenida() {
        System.out.println("=====================================");
        System.out.println("     BIENVENIDO A YU-GI-OH JAVA     ");
        System.out.println("=====================================");
        System.out.println();
        System.out.println(" Instrucciones:");
        System.out.println(" - Cada jugador comienza con 8000 puntos de vida.");
        System.out.println(" - El objetivo es reducir la vida del oponente a 0.");
        System.out.println(" - Usa tus cartas para invocar monstruos, activar magia y trampas.");
        System.out.println(" - ¡Que gane el mejor duelista!");
        System.out.println();
    }

    @Override
    public void mostrarEstadoCompleto(Juego juego) {
        Jugador j1 = juego.getJugadorActual();
        Jugador j2 = juego.getJugadorEnemigo();

        System.out.println("-----------------------------");
        System.out.println("      ESTADO DEL CAMPO       ");
        System.out.println("-----------------------------");

        System.out.printf(" %-20s LP: %5d  Mazo: %2d%n",
            j1.getNombre(), j1.getVida(), j1.getCartasMazo());

        System.out.println(" Campo: ");
        if (j1.getCampo().isEmpty()) {
            System.out.println("  (vacio)");
        } else {
            for (int i = 0; i < j1.getCampo().size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + j1.getCampo().get(i).getNombre()
                    + " ATK:" + j1.getCampo().get(i).getAtk() + " DEF:" + j1.getCampo().get(i).getDef());
            }
        }

        System.out.println(" Trampas en campo: " + j1.getTrampasEnCampo().size());

        System.out.println();

        System.out.printf(" %-20s LP: %5d  Mazo: %2d%n",
            j2.getNombre(), j2.getVida(), j2.getCartasMazo());

        System.out.println(" Campo: ");
        if (j2.getCampo().isEmpty()) {
            System.out.println("  (vacio)");
        } else {
            for (int i = 0; i < j2.getCampo().size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + j2.getCampo().get(i).getNombre()
                    + " ATK:" + j2.getCampo().get(i).getAtk() + " DEF:" + j2.getCampo().get(i).getDef());
            }
        }

        System.out.println(" Trampas en campo: " + j2.getTrampasEnCampo().size());

        System.out.println();
    }

    @Override
    public void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos) {
        System.out.println("=====================================");
        System.out.println("         FIN DEL DUELO              ");
        System.out.println("=====================================");

        if (j1.getVida() <= 0) {
            System.out.println(" ¡" + j2.getNombre() + " GANA! ");
        } else if (j2.getVida() <= 0) {
            System.out.println(" ¡" + j1.getNombre() + " GANA! ");
        } else {
            System.out.println(" Duelo terminado por otra razón. ");
        }

        System.out.println(" Turnos jugados: " + turnos);
        System.out.println(" Vida final " + j1.getNombre() + ": " + j1.getVida());
        System.out.println(" Vida final " + j2.getNombre() + ": " + j2.getVida());
    }

    @Override
    public void mostrarManoConTipo(Jugador jugador) {
        System.out.println(" Mano de " + jugador.getNombre() + " (" + jugador.getMano().size() + " cartas):");
        for (int i = 0; i < jugador.getMano().size(); i++) {
            String tipo = "[Mágica]";
            if (jugador.getMano().get(i) instanceof cartas.Monstruo) {
                tipo = "[Monstruo]";
            } else if (jugador.getMano().get(i) instanceof cartas.CartaTrampa) {
                tipo = "[Trampa]";
            }
            System.out.println("  [" + (i + 1) + "] " + tipo + " " + jugador.getMano().get(i).getNombre());
        }
    }

    @Override
    public int leerEntero(int min, int max) {
        int valor;
        do {
            System.out.print(" Tu elección: ");
            while (!scanner.hasNextInt()) {
                System.out.println(" Por favor, ingresa un número válido.");
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor < min || valor > max) {
                System.out.println(" Opción fuera de rango. Intenta de nuevo.");
            }
        } while (valor < min || valor > max);
        return valor;
    }

    @Override
    public void pausar() {
        System.out.print(" Presiona ENTER para continuar...");
        scanner.nextLine();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void mostrarTurno(int numeroTurno, String nombreJugador) {
        System.out.println("------------------");
        System.out.println(" TURNO: " + numeroTurno + " - " + nombreJugador.toUpperCase());
        System.out.println("------------------");
    }

    @Override
    public void mostrarFase(String nombreFase) {
        System.out.println("-----" + nombreFase + "-----");
    }

    @Override
    public void mostrarOpciones(String[] opciones) {
        for (int i = 0; i < opciones.length; i++) {
            System.out.println("  [" + (i + 1) + "] " + opciones[i]);
        }
    }

    @Override
    public void mostrarCartaJugada(String nombreJugador, String nombreCarta) {
        System.out.println(nombreJugador + " juega: " + nombreCarta);
    }

    @Override
    public void mostrarAtaque(String atacante, String defensor, int dano) {
        System.out.println(atacante + " ataca a " + defensor + " causando " + dano + " de daño.");
    }

    @Override
    public void mostrarResultadoAtaque(String resultado) {
        System.out.println(resultado);
    }

    @Override
    public int seleccionarMonstruoCampo(Jugador jugador, String mensaje) {
        System.out.println(mensaje);
        for (int i = 0; i < jugador.getCampo().size(); i++) {
            cartas.Monstruo m = jugador.getCampo().get(i);
            String yaAtaco = m.yaAtaco() ? " [Ya ataco]" : "";
            System.out.println("  [" + (i + 1) + "] " + m.getNombre()
                + " ATK:" + m.getAtk() + " DEF:" + m.getDef() + yaAtaco);
        }
        System.out.println("  [0] Cancelar");
        return leerEntero(0, jugador.getCampo().size());
    }

    @Override
    public String leerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    @Override
    public int seleccionarTrampaEnCampo(Jugador jugador, String mensaje) {
        if (jugador.getTrampasEnCampo().isEmpty()) {
            return -1; // No hay trampas
        }
        System.out.println(mensaje);
        for (int i = 0; i < jugador.getTrampasEnCampo().size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + jugador.getTrampasEnCampo().get(i).getNombre());
        }
        System.out.println("  [0] No activar trampa");
        int eleccion = leerEntero(0, jugador.getTrampasEnCampo().size());
        return eleccion == 0 ? -1 : eleccion - 1; // Devolver índice 0-based o -1
    }
}