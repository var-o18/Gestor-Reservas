import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Reserva implements Serializable {
    private String id;
    Usuario usuarioactivo;
    private Asistente asistente;
    private Evento evento;
    private Butaca butaca;
    private LocalDate fecha;
    private String hora;
    private String token;

    public Reserva(Usuario usuarioactivo, Evento evento, Butaca butaca, LocalDate fecha, String hora) {
        this.usuarioactivo=usuarioactivo;
        this.asistente = asistente;
        this.evento = evento;
        this.butaca = butaca;
        this.fecha = fecha;
        this.hora = hora;
        this.id = Validaciones.RealizaTocken();
    }

    public Usuario getUsuarioactivo() {
        return usuarioactivo;
    }

    public void setUsuarioactivo(Usuario usuarioactivo) {
        this.usuarioactivo = usuarioactivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Butaca getButaca() {
        return butaca;
    }

    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void reservas_realizadas(){
        System.out.println("Id reserva: "+id);
        System.out.println("Asistente: "+usuarioactivo.getNombre());
        System.out.println("Evento: "+evento.getNombre());
        System.out.println("Butaca: "+butaca.getCordenada());
        System.out.println("Fecha: "+evento.getFecha());
        System.out.println("Hora: "+evento.getHora());
        System.out.println("Sala: "+evento.getSala().getNombre());
    }
}
