package efectos;

import cartas.Mounstruo;
import jugadores.Jugador;

public class EfectoDrenaje implements Efecto {

    private int cantidad;

    public EfectoDrenaje(int cantidad){
        this.cantidad = cantidad;
    }

    @Override
    public void activar(Jugador jugador) {

        jugador.recibirDanio(cantidad);
        jugador.setVida(jugador.getVida() + cantidad);

        System.out.println(" Drenado ");
    }

    @Override
    public void activar(Mounstruo mounstruo) {}
}