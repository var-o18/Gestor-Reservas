import java.util.Date;

public class Evento {
    private String nombre;
    private String invitado;
    private Sala sala;
    private Date fecha;
    private String hora;
    private double precio;
    private String tipoEvento;
    private int numeoroAsistentesmaximo;
    private String listaAsistentes;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
        return numeoroAsistentesmaximo;
    }

    public void setNumeoroAsistentesmaximo(int numeoroAsistentesmaximo) {
        this.numeoroAsistentesmaximo = numeoroAsistentesmaximo;
    }

    public String getListaAsistentes() {
        return listaAsistentes;
    }

    public void setListaAsistentes(String listaAsistentes) {
        this.listaAsistentes = listaAsistentes;
    }
    // TODO: 08/01/2024 meter anidamientos al main
}

