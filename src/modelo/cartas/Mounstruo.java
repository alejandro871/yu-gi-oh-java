package modelo.cartas;

public class Mounstruo extends Carta {

    private int atkBase;
    private int atk;
    private int def;
    private int nivel;
    private boolean posicion;// True es ataque y Falso es defensa
    private boolean yaAtaco;

    public Mounstruo (String nombre, int atk, int def, int nivel, String descripcion ){

        super(nombre, descripcion); //es para decirle a la clase padre que le guarde algo

        this.atkBase = atk; //ELvalor original que no cambia
        this.atk = atk; //valor actual que cambia con los efectos
        this.def = def;
        this.nivel = nivel; //se guardan los valores recibidos dentro del obj
        this.posicion = true;
        this.yaAtaco = false;

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

    public void setDef(int def){

        this.def = def;
    }



    public int getNivel(){

        return nivel;

    }//para poder saber sus valores que vienen de la clase pruvada



    public void resetAtk(){

        this.atk = atkBase;
    }


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

    public boolean yaAtaco(){

    return yaAtaco;

    }

    public void marcarAtaque(){

    this.yaAtaco = true; 

    }

    public void reiniciarAtaque(){

    this.yaAtaco = false;
    }



}
