package src.cartas;

public abstract class Carta {

    private String nombre;

    public Carta ( String nombre ){

        this.nombre = nombre;
    }

    public String grtNombre() {
        return nombre;
    }


    
}
