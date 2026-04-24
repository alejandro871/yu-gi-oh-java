package efectos;
import cartas.Mounstruo;
import jugadores.Jugador;

public class AumentoAtaque implements Efecto {

    private int aumento;

    public AumentoAtaque(int aumento){
        this.aumento = aumento;
    }

    @Override
    public void activar(Jugador jugador) {
        // no hace nada
    }

    @Override
    public void activar(Mounstruo mounstruo) {

        int atkActual = mounstruo.getAtk();
        int nuevoAtk = atkActual + aumento;

        mounstruo.setAtk(nuevoAtk);

        System.out.println(" El ataque del monstruo aumenta de " + atkActual + " a " + nuevoAtk);
    }
}