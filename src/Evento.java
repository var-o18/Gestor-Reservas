import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Evento {
    private String nombre;
    private String invitado;
    private Sala sala;
    private LocalDate fecha;
    private String hora;
    private double precio;
    private String tipoEvento;
    private int numeroAsistentesmaximo;
    private ArrayList<Asistente> listaAsistentes = new ArrayList<>();


    public Evento(){}
    public Evento(String nombre, String invitado, Sala sala, String fecha, String hora, double precio, String tipoEvento, int numeroAsistentesmaximo) {
        this.nombre = nombre;
        this.invitado = invitado;
        this.sala = sala;
        this.fecha = LocalDate.parse(fecha);
        this.hora = hora;
        this.precio = precio;
        this.tipoEvento = tipoEvento;
        this.numeroAsistentesmaximo = numeroAsistentesmaximo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInvitado() {
        return invitado;
    }

    public void setInvitado(String invitado) {
        this.invitado = invitado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ArrayList<Asistente> getListaAsistentes() {
        return listaAsistentes;
    }

    public void setListaAsistentes(ArrayList<Asistente> listaAsistentes) {
        this.listaAsistentes = listaAsistentes;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeoroAsistentesmaximo() {
        return numeroAsistentesmaximo;
    }

    public void setNumeoroAsistentesmaximo(int numeoroAsistentesmaximo) {
        this.numeroAsistentesmaximo = numeoroAsistentesmaximo;
    }
}

