package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

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
        System.out.println(" ¡Trampa Curación activada! Se recuperan " + curacion + " LP");
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        // No aplica
    }
}
