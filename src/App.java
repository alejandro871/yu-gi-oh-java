

/**
 * Punto de entrada legacy - usar vista.App para ejecutar
 * Este archivo se mantiene por compatibilidad con scripts antiguos.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Este archivo ya no es el punto de entrada.");
        System.out.println("Ejecuta: vista.App");
        System.out.println("O usa los archivos .bat de la raíz del proyecto.");
    }
}

            mostrarEstadoCompleto(juego);

        System.out.println("-----Fase principal-----");
            ejecutarFasePrincipal(juego, actual);

        System.out.println("-----Fase batalla-----");
            ejecutarFaseBatalla(juego, actual, enemigo);

        juego.faseFinal();

        mostrarEstadoCompleto(juego);
            turnosJugados++;

            if (juego.hayGanador()) break;

        System.out.print(" Presiona ENTER para continuar al siguiente turno...");
            scanner.nextLine();

    }

    mostrarPantallaFinal(juego, j1, j2, turnosJugados);

    scanner.close();
}

private static void ejecutarFasePrincipal(Juego juego, Jugador actual) {

        if (actual.getMano().isEmpty()) {
            System.out.println(" (Mano vacía — no puedes jugar cartas) ");
            return;
        }

        System.out.println(" Mano de " + actual.getNombre() + ":");
        mostrarManoConTipo(actual);

        System.out.println(" ¿Qué deseas hacer? ");
        System.out.println("  [1] Jugar una carta ");
        System.out.println("  [2] Pasar (no jugar carta este turno) ");
        System.out.print(" Tu elección: ");

        int opcion = leerEntero(1, 2);

        if (opcion == 2) {
            System.out.println(" " + actual.getNombre() + " decide no jugar carta ");
            return;
        }

        System.out.println("¿Qué carta quieres jugar? ");//se muestran las cartas enumeradas para elegir

        for (int i = 0; i < actual.getMano().size(); i++) {

            Carta c = actual.getMano().get(i);

            String tipo = (c instanceof Monstruo) ? " [Monstruo] " : " [Mágica]  ";

            System.out.println("  [" + (i + 1) + "] " + tipo + " " + c.getNombre());
        }
        System.out.println("  [0] Cancelar");
        System.out.print(" Tu elección: ");

        int indice = leerEntero(0, actual.getMano().size());

        if (indice == 0) {
            System.out.println(" Acción cancelada ");
            return;
        }

        Carta cartaElegida = actual.getMano().get(indice - 1);

        if (cartaElegida instanceof Monstruo) {
            actual.jugarMonstruo((Monstruo) cartaElegida);

        } else if (cartaElegida instanceof CartaMagica) {

            CartaMagica magica = (CartaMagica) cartaElegida;

            actual.jugarMagia(magica);
        }
    }

private static void ejecutarFaseBatalla(Juego juego, Jugador actual, Jugador enemigo) {

        if (juego.esPrimerTurno()) {

            System.out.println(" Primer turno: no se puede atacar ");

            return;
        }

        if (actual.getCampo().isEmpty()) {
            System.out.println(" No tienes monstruos en campo para atacar ");
            return;
        }

        System.out.println(" ¿Deseas atacar este turno? ");
        System.out.println("  [1] Si, atacar ");
        System.out.println("  [2] No atacar (pasar)");
        System.out.print(" Tu elección: ");

        int opcion = leerEntero(1, 2);

        if (opcion == 2) {
            System.out.println(" " + actual.getNombre() + " decide no atacar ");
            return;
        }

        System.out.println(" Elige el monstruo atacante: ");
        for (int i = 0; i < actual.getCampo().size(); i++) {
            Monstruo m = actual.getCampo().get(i);
            String yaAtaco = m.yaAtaco() ? " [Ya ataco]" : "";
            System.out.println("  [" + (i + 1) + "] " + m.getNombre()
                    + " ATK:" + m.getAtk() + " DEF:" + m.getDef() + yaAtaco);
        }
        System.out.println("  [0] Cancelar ataque ");
        System.out.print(" Tu eleccion: ");

        int eleAtacante = leerEntero(0, actual.getCampo().size());
        if (eleAtacante == 0) {
            System.out.println(" Ataque cancelado.");
            return;
        }

        Monstruo atacante = actual.getCampo().get(eleAtacante - 1);

        if (atacante.yaAtaco()) {
            System.out.println(" Ese monstruo ya ataco este turno ");
            return;
        }

        if (!enemigo.getCampo().isEmpty()) { //aqui elegimos si el objetivo tiene mountruos
            System.out.println(" Elige el objetivo del ataque: ");

            for (int i = 0; i < enemigo.getCampo().size(); i++) {

                Monstruo m = enemigo.getCampo().get(i);

                System.out.println("  [" + (i + 1) + "] " + m.getNombre()

                            + " ATK:" + m.getAtk() + " DEF:" + m.getDef());
            }
            System.out.println("  [0] Cancelar");
            System.out.print(" Tu eleccion: ");

            int eleDefensor = leerEntero(0, enemigo.getCampo().size());
            if (eleDefensor == 0) {
                System.out.println(" Ataque cancelado.");
                return;
            }

            java.util.Collections.swap(enemigo.getCampo(), 0, eleDefensor - 1);
        }

        actual.atacarJugador(enemigo);
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

            for (Monstruo m : j1.getCampo()) {
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

            for (Monstruo m : j2.getCampo()) {

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
        System.out.printf ("  %-20s LP finales: %5d%n",
                j1.getNombre(), j1.getVida());

        System.out.printf ("  %-20s LP finales: %5d%n",
                j2.getNombre(), j2.getVida());

        System.out.println("  Duracion: " + turnos + " turnos ");
        System.out.println("╚═════════════════════════════════════╝");
    }

private static int leerEntero(int min, int max) {

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

private static void pausar() {

        System.out.print(" Presiona ENTER para continuar... ");

        scanner.nextLine();
    }
}

