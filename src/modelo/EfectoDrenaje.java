package modelo;

import modelo.Monstruo;
import modelo.Jugador;

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

        Mensajero.add(" Drenado: " + costo + " LP y recupero: " + curacion + " LP");
    }

    @Override
    public void activar(Monstruo Monstruo) {}
}
