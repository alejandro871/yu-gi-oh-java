package efectos;
import jugadores.Jugador;
import cartas.Mounstruo;
import java.util.ArrayList;;

public class efectoTemporalAtk implements Efecto {

    private int bonusAtk; //aqui guardamos la referencia al mountruo  afectado para que se revierta al final del turno
    private Mounstruo mounstruoAfectado;
    private boolean activo;

    public efectoTemporalAtk(int bonusAtk){

        this.bonusAtk = bonusAtk;
        this.activo = false;

    }

    @Override
    public void activar(Jugador jugador){//Para bonus al primer mountruo en campo del jugador
    
    ArrayList<cartas.Mounstruo> campo = jugador.getCampo();
    
    if (campo.isEmpty()){

        System.out.println(" No hay moustruos en el campo para aplicar el efecto ");
        return;
    }

    mounstruoAfectado = campo.get(0);
    int atkActual = mounstruoAfectado.getAtk();
    mounstruoAfectado.setAtk(atkActual + bonusAtk);
    activo = true;

    System.out.println(" (Temporal) Ataque de: " + mounstruoAfectado.getNombre() + " Aumenta de:" + atkActual + " a: " + mounstruoAfectado.getAtk());

}

    @Override
    public void activar(Mounstruo mounstruo){

        int atkActual = mounstruo.getAtk();
        mounstruo.setAtk(atkActual + bonusAtk);
        mounstruoAfectado = mounstruo;
        activo = true;

        System.out.println(" (Temporal) ataque de: " + mounstruo.getNombre() + " sube: " + bonusAtk + " puntos por este turno ");

    }

    public void revertir(){

        if (activo && mounstruoAfectado != null) {

            int atkActual = mounstruoAfectado.getAtk();

            mounstruoAfectado.setAtk(atkActual - bonusAtk);

            System.out.println(" (Temporal) ataque de: " + mounstruoAfectado.getNombre() + " vuelve a: " + mounstruoAfectado.getAtk() + " (fin de efecto temporal) ");
            activo = false;
            mounstruoAfectado = null;
        }
    }

    public boolean estaActivo(){

        return activo;
    }

        
}

