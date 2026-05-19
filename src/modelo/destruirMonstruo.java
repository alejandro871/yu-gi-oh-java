package modelo;
import modelo.Monstruo;
import modelo.Jugador;
import java.util.List;


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

        Mensajero.add(" (Orden de destruccion) No se pudo determinar el objetivo ");
        return;
    }

List<Monstruo> campoEnemigo = enemigo.getCampo();

    if (campoEnemigo.isEmpty()) {

         Mensajero.add(" El oponente no tiene monstruos en campo ");

         return;
        }

     Monstruo objetivo = campoEnemigo.get(0);

        for (Monstruo m : campoEnemigo) {

            if (m.getAtk() < objetivo.getAtk()) {

                objetivo = m;
            }
        }

        Mensajero.add(" Efecto de destrucción " + 
        objetivo.getNombre() + " (ATK: " + objetivo.getAtk() + ") es destruido directamente ");
        enemigo.eliminarMonstruo(objetivo);
    }

public void activar(Monstruo Monstruo){

}

}







