import java.time.LocalDate;
import java.util.Date;

public class Reserva {
    private String id;
    private Asistente asistente;
    private Evento evento;
    private Butaca butaca;
    private LocalDate fecha;
    private String hora;
    private String token;

    public Reserva(Asistente asistente, Evento evento, Butaca butaca, LocalDate fecha, String hora) {
        this.id = id;
        this.asistente = asistente;
        this.evento = evento;
        this.butaca = butaca;
        this.fecha = fecha;
        this.hora = hora;
        this.id = Validaciones.RealizaTocken();
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
        System.out.println("Asistente: "+asistente.getNombre());
        System.out.println("Evento: "+evento.getNombre());
        System.out.println("Butaca: "+butaca.getCordenada());
        System.out.println("Fecha: "+evento.getFecha());
        System.out.println("Hora: "+evento.getHora());
    }
    // TODO: 15/01/2024 comprobarDisponibilidad "metodo"
    // TODO: 15/01/2024 reservarPlaza "metodo"
    // TODO: 15/01/2024 generarToken "metodo"
}
