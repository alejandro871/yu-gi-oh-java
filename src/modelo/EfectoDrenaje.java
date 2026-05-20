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
        // Versión legacy - aplicar al mismo jugador (incorrecto, mantener por compatibilidad)
        int costo = cantidad/2;
        int curacion = cantidad;

        jugador.recibirDanio(costo);
        jugador.setVida(jugador.getVida() + curacion);

        Mensajero.add(" Drenado: " + costo + " LP y recupero: " + curacion + " LP");
    }

    @Override
    public void activar(Monstruo Monstruo) {}

    @Override
    public void activar(Jugador jugador, Jugador oponente) {
        // Drena al oponente y cura al jugador
        oponente.recibirDanio(cantidad);
        jugador.setVida(jugador.getVida() + cantidad);
        Mensajero.add(" Efecto Drenaje: " + cantidad + " LP drenados del oponente");
        Mensajero.add(" " + jugador.getNombre() + " recupera " + cantidad + " LP");
    }
}
