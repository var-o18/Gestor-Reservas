import java.util.ArrayList;

public class Sala {
    public String nombre;
    public int cpacidad_maxima;

    public ArrayList<Butaca> lista_butacas=new ArrayList<>();
    private String buutacasDisponibles;
    public double dimensesionSala;
    public boolean accesibilidad;
    private String caracteristicasAcusticas;
    private String observcaciones;

    public Sala(String nombre, int cpacidad_maxima, ArrayList<Butaca> lista_butacas, double dimensesionSala) {
        this.nombre = nombre;
        this.cpacidad_maxima = cpacidad_maxima;
        this.lista_butacas = lista_butacas;
        this.dimensesionSala = dimensesionSala;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCpacidad_maxima(int cpacidad_maxima) {
        this.cpacidad_maxima = cpacidad_maxima;
    }

    public int getCpacidad_maxima() {
        return cpacidad_maxima;
    }

    public void setLista_butacas(ArrayList<Butaca> lista_butacas) {
        this.lista_butacas = lista_butacas;
    }

    public ArrayList<Butaca> getLista_butacas() {
        return lista_butacas;
    }

    public void setBuutacasDisponibles(String buutacasDisponibles) {
        this.buutacasDisponibles = buutacasDisponibles;
    }

    public String getBuutacasDisponibles() {
        return buutacasDisponibles;
    }

    public void setDimensesionSala(double dimensesionSala) {
        this.dimensesionSala = dimensesionSala;
    }

    public double getDimensesionSala() {
        return dimensesionSala;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean getAccesibilidad() {
        return accesibilidad;
    }

    public void setCaracteristicasAcusticas(String caracteristicasAcusticas) {
        this.caracteristicasAcusticas = caracteristicasAcusticas;
    }

    public String getCaracteristicasAcusticas() {
        return caracteristicasAcusticas;
    }

    public void setObservcaciones(String observcaciones) {
        this.observcaciones = observcaciones;
    }

    public String getObservcaciones() {
        return observcaciones;
    }
    public boolean comprobarCapacidad(int numeroAsistentes){
        return numeroAsistentes <= cpacidad_maxima;
    }



    public Butaca asociar_butaca(String posicion){
        Butaca butaca_asociada=new Butaca();
        for(Butaca b : lista_butacas){
            if (b.getCordenada().equals(posicion)){
               butaca_asociada = b;
            }
        }
        return butaca_asociada;
    }

}
