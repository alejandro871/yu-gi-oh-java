package modelo;
import modelo.Monstruo;
import modelo.Jugador;


public class EfectoCuracion implements Efecto{

    private int cantidadCuracion;

    public EfectoCuracion (int cantidadCuracion){

        this.cantidadCuracion = cantidadCuracion;

    }

    @Override
    public void activar(Jugador jugador) {
    
        jugador.setVida(jugador.getVida()+ cantidadCuracion);
        Mensajero.add(" El jugador recupera " + cantidadCuracion + " LP. Vida actual: " + jugador.getVida());
    }

    @Override
    public void activar(Monstruo Monstruo) {
       return;
    }

    
}

