package efectos;
import jugadores.Jugador;
import cartas.Monstruo;
import java.util.ArrayList;;

public class efectoTemporalAtk implements Efecto {

    private int bonusAtk; //aqui guardamos la referencia al mountruo  afectado para que se revierta al final del turno
    private Monstruo MonstruoAfectado;
    private boolean activo;

    public efectoTemporalAtk(int bonusAtk){

        this.bonusAtk = bonusAtk;
        this.activo = false;

    }

    @Override
    public void activar(Jugador jugador){//Para bonus al primer mountruo en campo del jugador
    
    ArrayList<cartas.Monstruo> campo = jugador.getCampo();
    
    if (campo.isEmpty()){

        System.out.println(" No hay moustruos en el campo para aplicar el efecto ");
        return;
    }

    MonstruoAfectado = campo.get(0);
    int atkActual = MonstruoAfectado.getAtk();
    MonstruoAfectado.setAtk(atkActual + bonusAtk);
    activo = true;

    System.out.println(" (Temporal) Ataque de: " + MonstruoAfectado.getNombre() + " Aumenta de:" + atkActual + " a: " + MonstruoAfectado.getAtk());

}

    @Override
    public void activar(Monstruo Monstruo){

        int atkActual = Monstruo.getAtk();
        Monstruo.setAtk(atkActual + bonusAtk);
        MonstruoAfectado = Monstruo;
        activo = true;

        System.out.println(" (Temporal) ataque de: " + Monstruo.getNombre() + " sube: " + bonusAtk + " puntos por este turno ");

    }

    public void revertir(){

        if (activo && MonstruoAfectado != null) {

            int atkActual = MonstruoAfectado.getAtk();

            MonstruoAfectado.setAtk(atkActual - bonusAtk);

            System.out.println(" (Temporal) ataque de: " + MonstruoAfectado.getNombre() + " vuelve a: " + MonstruoAfectado.getAtk() + " (fin de efecto temporal) ");
            activo = false;
            MonstruoAfectado = null;
        }
    }

    public boolean estaActivo(){

        return activo;
    }

        
}

