package excepciones;

public class EmailExsistenteException extends Exception {
    String mensaje= "El email ya existe";
    public EmailExsistenteException() {
        super("El email ya existe");
    }
    public EmailExsistenteException(String mensaje) {
        super(mensaje);
    }
    @Override
    public String getMessage(){
        return mensaje;
    }


}
