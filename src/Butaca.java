public class Butaca {
    int id;
    String cordenada;
    private Boolean accesibilidad;
    private Boolean disponible = true;

    Butaca(){}
    Butaca(int id,String cordenada,Boolean accesibilidad, Boolean disponible) {
        this.id = id;
        this.accesibilidad = accesibilidad;
        this.disponible = disponible;
        this.cordenada=cordenada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCordenada() {
        return cordenada;
    }

    public void setCordenada(String cordenada) {
        this.cordenada = cordenada;
    }

    public Boolean getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(Boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Butaca{" +
                "id='" + id + '\'' +
                ", accesibilidad=" + accesibilidad +
                ", disponible=" + disponible +
                '}';
    }

    /**
     * Metodo que permite comprobar la disponibilidad de las butacas
    */
    public boolean ocuparButaca(){
        if (disponible){
            disponible=false;
            System.out.println("La butaca "+id+" ha sido ocupada");
        }else {
            System.out.println("La butaca "+id+" ya esta ocupada");
        }
        return false;
    }
    public boolean desocuparButaca(){
        if (!disponible){
            disponible=true;
            System.out.println("La butaca "+id+" ha sido desocupada");
        }else {
            System.out.println("La butaca "+id+" ya esta disponible");
        }
        return false;
    }
}
