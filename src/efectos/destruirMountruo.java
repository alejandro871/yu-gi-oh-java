package efectos;
import cartas.Mounstruo;
import jugadores.Jugador;
import java.util.ArrayList;


public class destruirMountruo implements Efecto{

private Jugador enemigo;


public destruirMountruo(){

        this.enemigo = null;    
}

public destruirMountruo(Jugador enemigo){

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

ArrayList<Mounstruo> campoEnemigo = enemigo.getCampo();

    if (campoEnemigo.isEmpty()) {

         System.out.println(" El oponente no tiene monstruos en campo ");

         return;
        }

     Mounstruo objetivo = campoEnemigo.get(0);

        for (Mounstruo m : campoEnemigo) {

            if (m.getAtk() < objetivo.getAtk()) {

                objetivo = m;
            }
        }

        System.out.println(" Efecto de destrucción " + 
        objetivo.getNombre() + " (ATK: " + objetivo.getAtk() + ") es destruido directamente ");
        enemigo.eliminarMonstruo(objetivo);
    }

public void activar(Mounstruo mounstruo){

}

}







