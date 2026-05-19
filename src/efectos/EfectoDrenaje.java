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

        int costo = cantidad/2;
        int curacion = cantidad;

        jugador.recibirDanio(costo);
        jugador.setVida(jugador.getVida() + curacion);

        System.out.println(" Drenado: " + costo + " LP y recupero: " + curacion + " LP");
    }

    @Override
    public void activar(Mounstruo mounstruo) {}
}