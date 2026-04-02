package src.cartas;

public class Mounstruo extends Carta {

    private int atk;
    private int def;
    private int nivel;

    public Mounstruo (String nombre, int atk, int def, int nivel ){

        super(nombre); //es para decirle a la clase padre que le guarde algo

        this.atk = atk;
        this.def = def;
        this.nivel = nivel; //se guardan los valores recibidos dentro del obj

    }


    public int getAtk(){

        return atk;

    } //para poder saber sus valores que vienen de la clase pruvada
    
    public int getDef(){

        return def;

    }

    public int getNivel(){

        return nivel;

    }

    public void atacar( Mountruo enemigo){

        if (this.atk > getAtk(enemigo));

        int diferencia = this.atk - getAtk();

        System.out.println( this.grtNombre() + " Destrulle a " + enemigo.grtNombre());
        System.out.println("Inflingiste: " + diferencia + " de daño");

    } else if ( this.atk < enemigo.getAtk()) {

        int diferencia = enemigo.getAtk() - this.atk;

        System.out.println( this.grtNombre());

        

    }


}
