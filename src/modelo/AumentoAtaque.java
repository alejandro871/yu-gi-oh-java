package modelo;
import modelo.Monstruo;
import modelo.Jugador;

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
    public void activar(Monstruo Monstruo) {

        int atkActual = Monstruo.getAtk();
        int nuevoAtk = atkActual + aumento;

        Monstruo.setAtk(nuevoAtk);

        Mensajero.add(" El ataque del monstruo aumenta de " + atkActual + " a " + nuevoAtk);
    }
}
