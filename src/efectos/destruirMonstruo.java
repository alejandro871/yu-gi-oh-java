package efectos;
import cartas.Monstruo;
import jugadores.Jugador;
import java.util.ArrayList;


public class destruirMonstruo implements Efecto{

private Jugador enemigo;


public destruirMonstruo(){

        this.enemigo = null;    
}

public destruirMonstruo(Jugador enemigo){

    this.enemigo = enemigo;
}

public void setEnemigo(Jugador enemigo){

    this.enemigo = enemigo;
}

@Override

public void activar (Jugador jugador){

    if(enemigo == null){

        System.out.println(" (Orden de destruccion) No se pudo determinar el objetivo ");
        return;
    }

ArrayList<Monstruo> campoEnemigo = enemigo.getCampo();

    if (campoEnemigo.isEmpty()) {

         System.out.println(" El oponente no tiene monstruos en campo ");

         return;
        }

     Monstruo objetivo = campoEnemigo.get(0);

        for (Monstruo m : campoEnemigo) {

            if (m.getAtk() < objetivo.getAtk()) {

                objetivo = m;
            }
        }

        System.out.println(" Efecto de destrucción " + 
        objetivo.getNombre() + " (ATK: " + objetivo.getAtk() + ") es destruido directamente ");
        enemigo.eliminarMonstruo(objetivo);
    }

public void activar(Monstruo Monstruo){

}

}







