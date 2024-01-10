import java.util.Date;

public class Reservar {
    private String id;
    private Asistente asistente;
    private Evento evento;
    private Butaca butaca;
    private Date fecha;
    private String hora;
    private String token;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    // TODO: 08/01/2024 meter anidamientos al main
}
