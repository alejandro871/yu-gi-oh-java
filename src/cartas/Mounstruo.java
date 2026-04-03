package cartas;

public class Mounstruo extends Carta {

    private int atk;
    private int def;
    private int nivel;
    private boolean posicion;// True es ataque y Falso es defensa

    public Mounstruo (String nombre, int atk, int def, int nivel, String descripcion ){

        super(nombre, descripcion); //es para decirle a la clase padre que le guarde algo

        this.atk = atk;
        this.def = def;
        this.nivel = nivel; //se guardan los valores recibidos dentro del obj
        this.posicion = true;
    }


    public boolean getPosicion(){

        return posicion;
    }


    public int getAtk(){

        return atk;

    }

    public void setAtk (int atk){

        this.atk = atk;
    }
    
    public int getDef(){

        return def;

    }

    public int getNivel(){

        return nivel;

    }//para poder saber sus valores que vienen de la clase pruvada






    public int atacar( Mounstruo enemigo){



        if (enemigo.getPosicion()){

            int enfrentamiento = atk - enemigo.getAtk();
            return enfrentamiento;
        }else{

            int enfrentamiento = atk - enemigo.getDef();
            if (enfrentamiento >= 0){
                return 0;// por mas que el ataque sea siperior no roba puntos de vida
            }else{
                return enfrentamiento;
            }

        }
    

    }

    public void cambiarPosicion(){

        posicion = !posicion;

    }


}
