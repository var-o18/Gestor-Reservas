public class Butaca {
    private String id;
    private Boolean accesibilidad;
    private Boolean disponible;

    Butaca(){}
    Butaca(String id,Boolean accesibilidad, Boolean disponible) {
        this.id = id;
        this.accesibilidad = accesibilidad;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    // TODO: 08/01/2024 meter anidamientos al main
}
