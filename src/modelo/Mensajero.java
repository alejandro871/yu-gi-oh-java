package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mensajero {
    private static final List<String> mensajes = Collections.synchronizedList(new ArrayList<>());

    public static synchronized void add(String msg) {
        mensajes.add(msg);
    }

    public static synchronized List<String> obtener() {
        List<String> copia = new ArrayList<>(mensajes);
        mensajes.clear();
        return copia;
    }

    public static synchronized void limpiar() {
        mensajes.clear();
    }
}
