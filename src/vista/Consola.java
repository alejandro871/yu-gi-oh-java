package vista;

import juego.Juego;
import jugadores.Jugador;
import cartas.Carta;
import cartas.Monstruo;
import cartas.CartaMagica;
import cartas.CartaTrampa;

import java.util.Scanner;

public class Consola implements Vista {
    
    private Scanner scanner;
    
    public Consola() {
        this.scanner = new Scanner(System.in);
    }
    
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            // Ignorar
        }
    }
    
    @Override
    public void mostrarBienvenida() {
        System.out.println("=================================");
        esperar(500);
        System.out.println("   BIENVENIDO A YU-GI-OH! JAVA   ");
        esperar(500);
        System.out.println("=================================");
        esperar(500);
        System.out.println();
    }
    
    @Override
    public void mostrarEstadoCompleto(Juego juego) {
        Jugador actual = juego.getJugadorActual();
        Jugador enemigo = juego.getJugadorEnemigo();
        
        System.out.println("=== ESTADO DEL JUEGO ===");
        esperar(300);
        System.out.println("Turno de: " + actual.getNombre());
        System.out.println();
        esperar(300);
        
        System.out.println("--- " + actual.getNombre() + " ---");
        System.out.println("LP: " + actual.getVida());
        System.out.println("Cartas en mano: " + actual.getMano().size());
        System.out.println("Monstruos en campo:");
        if (actual.getCampo().isEmpty()) {
            System.out.println("  (ninguno)");
        } else {
            for (Monstruo m : actual.getCampo()) {
                System.out.println("  - " + m.getNombre() + " [ATK: " + m.getAtk() + " DEF: " + m.getDef() + "]");
            }
        }
        esperar(300);
        
        System.out.println();
        System.out.println("--- " + enemigo.getNombre() + " ---");
        System.out.println("LP: " + enemigo.getVida());
        System.out.println("Cartas en mano: " + enemigo.getMano().size());
        System.out.println("Monstruos en campo:");
        if (enemigo.getCampo().isEmpty()) {
            System.out.println("  (ninguno)");
        } else {
            for (Monstruo m : enemigo.getCampo()) {
                System.out.println("  - " + m.getNombre() + " [ATK: " + m.getAtk() + " DEF: " + m.getDef() + "]");
            }
        }
        esperar(300);
        System.out.println();
    }
    
    @Override
    public void mostrarPantallaFinal(Juego juego, Jugador j1, Jugador j2, int turnos) {
        System.out.println("=================================");
        System.out.println("         FIN DEL JUEGO         ");
        System.out.println("=================================");
        System.out.println("Turnos jugados: " + turnos);
        
        if (j1.getVida() <= 0) {
            System.out.println("¡" + j2.getNombre() + " GANA!");
        } else if (j2.getVida() <= 0) {
            System.out.println("¡" + j1.getNombre() + " GANA!");
        } else {
            System.out.println("El juego terminó en empate.");
        }
        esperar(300);
        System.out.println();
        System.out.println("=== ESTADÍSTICAS ===");
        System.out.println(j1.getNombre() + ": " + j1.getVida() + " LP");
        System.out.println(j2.getNombre() + ": " + j2.getVida() + " LP");
    }
    
@Override
    public void mostrarManoConTipo(Jugador jugador) {
        System.out.println("Mano de " + jugador.getNombre() + ":");
        for (int i = 0; i < jugador.getMano().size(); i++) {
            Carta c = jugador.getMano().get(i);
            String info = "[" + (i + 1) + "] " + c.getNombre();
            if (c instanceof Monstruo) {
                Monstruo m = (Monstruo) c;
                info += " [ATK: " + m.getAtk() + " DEF: " + m.getDef() + " Nivel: " + m.getNivel() + "]";
            } else if (c instanceof CartaMagica) {
                info += " [MÁGICA]";
            } else if (c instanceof CartaTrampa) {
                info += " [TRAMPA]";
            }
            System.out.println(info);
            esperar(150);
        }
    }
    
    @Override
    public int leerEntero(int min, int max) {
        int opcion = 0;
        boolean valido = false;
        
        while (!valido) {
            System.out.print("Opcion (" + min + "-" + max + "): ");
            try {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                opcion = Integer.parseInt(linea);
                if (opcion >= min && opcion <= max) {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero.");
            }
        }
        return opcion;
    }
    
    @Override
    public void pausar() {
        System.out.print("Presiona ENTER para continuar...");
        scanner.nextLine();
    }
    
@Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
        esperar(200);
    }
    
    @Override
    public void mostrarOpciones(String[] opciones) {
        System.out.println("Opciones:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println("[" + (i + 1) + "] " + opciones[i]);
            esperar(150);
        }
    }
    
    @Override
    public void mostrarCartaJugada(String nombreJugador, String nombreCarta) {
        System.out.println("> " + nombreJugador + " juega: " + nombreCarta);
        esperar(400);
    }
    
    @Override
    public void mostrarAtaque(String atacante, String defensor, int dano) {
        System.out.println("! " + atacante + " ataca a " + defensor + " causando " + dano + " puntos de daño");
        esperar(400);
    }
    
    @Override
    public void mostrarResultadoAtaque(String resultado) {
        System.out.println("Resultado: " + resultado);
        esperar(400);
    }
    
    @Override
    public void mostrarTurno(int numeroTurno, String nombreJugador) {
        System.out.println();
        esperar(300);
        System.out.println("=== TURNO " + numeroTurno + " - " + nombreJugador.toUpperCase() + " ===");
    }
    
    @Override
    public void mostrarFase(String nombreFase) {
        System.out.println();
        esperar(300);
        System.out.println("--- " + nombreFase.toUpperCase() + " ---");
    }
    
    @Override
    public int seleccionarMonstruoCampo(Jugador jugador, String mensaje) {
        System.out.println(mensaje);
        if (jugador.getCampo().isEmpty()) {
            System.out.println("No hay monstruos en el campo.");
            return 0;
        }
        
        System.out.println("Monstruos disponibles:");
        for (int i = 0; i < jugador.getCampo().size(); i++) {
            Monstruo m = jugador.getCampo().get(i);
            System.out.println("[" + (i + 1) + "] " + m.getNombre() + " [ATK: " + m.getAtk() + "]");
        }
        
        return leerEntero(0, jugador.getCampo().size());
    }
    
    @Override
    public int seleccionarSacrificio(Jugador jugador, String mensaje) {
        System.out.println(mensaje);
        System.out.println("[0] Cancelar");
        
        if (jugador.getCampo().isEmpty()) {
            System.out.println("No hay monstruos para sacrificar.");
            return 0;
        }
        
        return leerEntero(0, jugador.getCampo().size());
    }
}