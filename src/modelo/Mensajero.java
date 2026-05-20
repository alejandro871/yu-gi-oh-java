package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mensajero {
    private static List<String> mensajes = new ArrayList<>();

    public static void add(String msg) {
        mensajes.add(msg);
    }

    public static List<String> obtener() {
        List<String> copia = new ArrayList<>(mensajes);
        mensajes.clear();
        return copia;
    }

    public static void limpiar() {
        mensajes.clear();
    }
}
