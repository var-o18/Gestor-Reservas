public class Sala {
    private String nombre;
    private int cpacidad_maxima;
    private String lista_butacas;
    private String buutacasDisponibles;
    private double dimensesionSala;
    private boolean accesibilidad;
    private String caracteristicasAcusticas;
    private String observcaciones;

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

    public void setLista_butacas(String lista_butacas) {
        this.lista_butacas = lista_butacas;
    }

    public String getLista_butacas() {
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
    // TODO: 08/01/2024 meter anidaciones al main 
}
