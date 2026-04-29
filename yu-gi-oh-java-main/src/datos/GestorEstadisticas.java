package datos;

import java.io.*;
import java.util.*;

public class GestorEstadisticas {
    private static final String ARCHIVO = "estadisticas.txt";
    private Map<String, Usuario> usuarios;

    public GestorEstadisticas() {
        usuarios = new HashMap<>();
        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 5) {
                    String nombre = partes[0].trim();
                    int ganadas = Integer.parseInt(partes[1].trim());
                    int perdidas = Integer.parseInt(partes[2].trim());
                    int j1 = Integer.parseInt(partes[3].trim());
                    int j2 = Integer.parseInt(partes[4].trim());
                    usuarios.put(nombre, new Usuario(nombre, ganadas, perdidas, j1, j2));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar estadísticas: " + e.getMessage());
        }
    }

    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : usuarios.values()) {
                pw.println(u.getNombre() + "|" + u.getPartidasGanadas() + "|" 
                         + u.getPartidasPerdidas() + "|" + u.getVecesJugador1() 
                         + "|" + u.getVecesJugador2());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar estadísticas: " + e.getMessage());
        }
    }

    public Usuario getUsuario(String nombre) {
        return usuarios.get(nombre);
    }

    public Usuario getOrCrearUsuario(String nombre) {
        if (!usuarios.containsKey(nombre)) {
            usuarios.put(nombre, new Usuario(nombre));
        }
        return usuarios.get(nombre);
    }

    public void registrarPartida(String nombreJugador, boolean gano, boolean fueJugador1) {
        Usuario u = getOrCrearUsuario(nombreJugador);
        if (gano) {
            u.incrementarGanadas();
        } else {
            u.incrementarPerdidas();
        }
        if (fueJugador1) {
            u.incrementarVecesJugador1();
        } else {
            u.incrementarVecesJugador2();
        }
        guardarEnArchivo();
    }

    public List<Usuario> getTop5Jugadores() {
        List<Usuario> lista = new ArrayList<>(usuarios.values());
        lista.sort((a, b) -> Integer.compare(b.getPartidasGanadas(), a.getPartidasGanadas()));
        return lista.size() > 5 ? lista.subList(0, 5) : lista;
    }

    public List<Usuario> getTodosLosUsuarios() {
        List<Usuario> lista = new ArrayList<>(usuarios.values());
        lista.sort((a, b) -> Integer.compare(b.getPartidasGanadas(), a.getPartidasGanadas()));
        return lista;
    }

    public boolean existeUsuario(String nombre) {
        return usuarios.containsKey(nombre);
    }
}