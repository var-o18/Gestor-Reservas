import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor_eventos = new Gestor();
        Asistente asistente = new Asistente();
        gestor_eventos.menuPrincipal(asistente);
    }
}
