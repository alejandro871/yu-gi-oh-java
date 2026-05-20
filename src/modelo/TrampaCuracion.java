package modelo;

import modelo.Monstruo;
import modelo.Jugador;

public class TrampaCuracion implements Efecto {

    private int curacion;

    public TrampaCuracion(int curacion) {
        this.curacion = curacion;
    }

    @Override
    public void activar(Jugador jugador) {
        int vidaActual = jugador.getVida();
        int nuevaVida = vidaActual + curacion;
        jugador.setVida(nuevaVida);
        Mensajero.add(" ¡Trampa Curación activada! Se recuperan " + curacion + " LP");
    }

    @Override
    public void activar(Monstruo Monstruo) {
        // No aplica
    }

    @Override
    public void activar(Jugador jugador, Jugador oponente, Monstruo atacante) {
        // Curar al jugador que defendía
        int vidaActual = jugador.getVida();
        int nuevaVida = vidaActual + curacion;
        jugador.setVida(nuevaVida);
        Mensajero.add(" ¡Trampa Curación activada! Se recuperan " + curacion + " LP");
    }
}
