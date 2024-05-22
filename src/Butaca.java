import java.io.Serializable;

public class Butaca  implements Serializable {
    int id;
    String cordenada;
    private boolean accesibilidad;
    private boolean disponible;

    Butaca(){}
    Butaca(int id,String cordenada,boolean accesibilidad, boolean disponible) {
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

    public boolean getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(Boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
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
