package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mensajes {

    private static List<String> cola = new ArrayList<>();

    public static void agregar(String msg) {
        cola.add(msg);
    }

    public static List<String> obtenerTodos() {
        List<String> copia = new ArrayList<>(cola);
        cola.clear();
        return copia;
    }

    public static void limpiar() {
        cola.clear();
    }
}
