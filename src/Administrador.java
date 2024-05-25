import java.io.Serializable;
import java.time.LocalDate;

public class Administrador extends Usuario {

    private  int permisos;

     Administrador(){}
    public Administrador(String nombre, String apellido, String email, String password, String telf, LocalDate fechaNacimiento, int permisos) {
        super(nombre, apellido, email, password, telf, fechaNacimiento);
        this.permisos = permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }

    public int getPermisos() {
        return permisos;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telf='" + telf + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", permisos=" + permisos +

                '}';
    }
}

