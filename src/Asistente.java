import java.io.Serializable;
import java.time.LocalDate;

public class Asistente extends Usuario  {

    private String dni;

    Asistente(){}


    public Asistente(String nombre, String apellido, String email, String password, String telf, LocalDate fechaNacimiento, String dni) {
        super(nombre, apellido, email, password, telf, fechaNacimiento);
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Asistente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telf='" + telf + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
