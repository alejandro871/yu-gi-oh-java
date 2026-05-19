package modelo.efectos;
import modelo.cartas.Mounstruo;
import modelo.jugadores.Jugador;
import modelo.Mensajes;

public class AumentoAtaque implements Efecto {

    private int aumento;

    public AumentoAtaque(int aumento){
        this.aumento = aumento;
    }

    @Override
    public void activar(Jugador jugador) {
    }

    @Override
    public void activar(Mounstruo mounstruo) {
        int atkActual = mounstruo.getAtk();
        int nuevoAtk = atkActual + aumento;
        mounstruo.setAtk(nuevoAtk);
        Mensajes.agregar(" El ataque del monstruo aumenta de " + atkActual + " a " + nuevoAtk);
    }
}
