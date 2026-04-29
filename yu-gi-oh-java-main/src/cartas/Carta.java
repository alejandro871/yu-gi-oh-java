package cartas;

import jugadores.Jugador;

public abstract class Carta {

    private String nombre;
    private String descripcion;

    public Carta ( String nombre,String descripcion ){

        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public String getDescripcion(){
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void jugar(Jugador jugadorActual);
    
}
