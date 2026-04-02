package src.cartas;

public class CartaMagica extends Carta implements Efecto {

    public CartaMagica (String nombre){

        super (nombre);

    }

    @Override //para implemetar metodo del inteface
    public void activar(){

        System.out.println("Se activa carta magica: "+ grtNombre());

    }

    
}
