package src.cartas;

public class CartaMagica extends Carta implements Efecto {

    public CartaMagica (String nombre, String descripcion){

        super (nombre, descripcion);

    }

    @Override //para implemetar metodo del inteface
    public void activar(){

        System.out.println("Se activa carta magica: "+ grtNombre());

    }

    
}
