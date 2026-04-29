package datos;

public class Usuario {
    private String nombre;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int vecesJugador1;
    private int vecesJugador2;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
        this.vecesJugador1 = 0;
        this.vecesJugador2 = 0;
    }

    public Usuario(String nombre, int ganadas, int perdidas, int j1, int j2) {
        this.nombre = nombre;
        this.partidasGanadas = ganadas;
        this.partidasPerdidas = perdidas;
        this.vecesJugador1 = j1;
        this.vecesJugador2 = j2;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public int getTotalPartidas() {
        return partidasGanadas + partidasPerdidas;
    }

    public int getVecesJugador1() {
        return vecesJugador1;
    }

    public int getVecesJugador2() {
        return vecesJugador2;
    }

    public void incrementarGanadas() {
        partidasGanadas++;
    }

    public void incrementarPerdidas() {
        partidasPerdidas++;
    }

    public void incrementarVecesJugador1() {
        vecesJugador1++;
    }

    public void incrementarVecesJugador2() {
        vecesJugador2++;
    }

    public double getPorcentajeVictorias() {
        int total = getTotalPartidas();
        if (total == 0) return 0;
        return (partidasGanadas * 100.0) / total;
    }
}