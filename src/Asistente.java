import java.time.LocalDate;

public class Asistente {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String dni;
    private LocalDate fecha_nacimiento;

    Asistente(){}
    Asistente(String nombre,String apellidos, String email,String telefono,String dni,  LocalDate fecha_nacimiento){
        this.nombre= nombre;
        this.apellidos= apellidos;
        this.email= email;
        this.telefono= telefono;
        this.dni= dni;
        this.fecha_nacimiento= fecha_nacimiento;
    }
    Asistente(String nombre, String apellidos, String email){
        this.nombre= nombre;
        this.apellidos= apellidos;
        this.email= email;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "Asistente{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
    public void ifoBasica(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Apellidos: "+apellidos);
        System.out.println("Email: "+email);
        System.out.println("DNI: "+dni);
    }
    // TODO: 03/01/2024 Falta meter en el Main,los llamamientos
}
