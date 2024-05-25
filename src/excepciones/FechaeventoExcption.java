package excepciones;

public class FechaeventoExcption extends Exception{
    String mensaje= "La fecha no es correcta";
    public FechaeventoExcption() {
        super("La fecha no es correcta");
    }
    public FechaeventoExcption(String mensaje) {
        super(mensaje);
    }
    @Override
    public String getMessage(){
        return mensaje;
    }
}
