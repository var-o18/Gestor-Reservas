public class HoraInvalidaException extends Exception{

    String mensaje = "Hora introducida incorrecta";
   public HoraInvalidaException(String mensaje){
       super(mensaje);
   }
   @Override
    public String getMessage(){
       return mensaje;
   }
}
