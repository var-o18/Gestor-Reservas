package excepciones;

public class DniExistenteException extends Exception {
    String mensaje= "El DNI ya existe";
    public DniExistenteException() {
        super("El DNI ya existe");
    }
    public DniExistenteException(String mensaje) {
        super(mensaje);
    }
    @Override
    public String getMessage(){
        return mensaje;
    }


}
