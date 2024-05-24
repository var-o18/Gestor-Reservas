import java.io.*;

import excepciones.EmailExsistenteException;
import org.mindrot.jbcrypt.BCrypt;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestor {
    FileOutputStream fos = null, fos_eventos = null, fos_salas = null;
    ObjectOutputStream oss = null, oss_eventos = null, oss_salas = null;
    FileInputStream fis = null, fis_eventos =null, fis_salas = null;
    ObjectInputStream ois = null, ois_eventos=null, ois_salas = null;

    Usuario usuarioactivo;
    Administrador administradoractivo;
    ArrayList<Evento> listado_eventos = new ArrayList<>();
    Sala listado_salas[] = new Sala[5];
    ArrayList<Usuario> listado_usuarios = new ArrayList<>();
    ArrayList<Reserva> listado_reservas = new ArrayList<>();


    public ArrayList<Evento> getListado_eventos() {
        return listado_eventos;
    }

    public Sala[] getListado_salas() {
        return listado_salas;
    }

    public ArrayList<Usuario> getListado_usuarios() {
        return listado_usuarios;
    }

    public void setListado_usuarios(ArrayList<Usuario> listado_usuarios) {
        this.listado_usuarios = listado_usuarios;
    }

    public ArrayList<Reserva> getListado_reservas() {
        return listado_reservas;
    }

    public void setListado_eventos(ArrayList<Evento> listado_eventos) {
        this.listado_eventos = listado_eventos;
    }

    public void setListado_salas(Sala[] listado_salas) {
        this.listado_salas = listado_salas;
    }


    public void setListado_reservas(ArrayList<Reserva> listado_reservas) {
        this.listado_reservas = listado_reservas;
    }

    Gestor() {
        usuarioactivo = null;
        this.listado_usuarios = new ArrayList<>();
        try {
            info_inicial();
            System.out.println("Vamos a ver los usuarios" );
            for(Usuario u: listado_usuarios) System.out.println(u.toString());
        } catch (IOException e) {
            System.out.println("Problemas de comunicacion con el fichero");
        }
    }

    public void info_inicial() throws IOException {
        /*CREAR LISTA DE ASISTENTES
                /*listado_usuarios.add(new Administrador("admin", "Rodriguez", "admin2@email.com",BCrypt.hashpw("12345678",BCrypt.gensalt()),"654411213",LocalDate.of(2005, 06, 28),2));
                listado_usuarios.add(new Administrador("admin", "Rodriguez", "admin1@email.com",BCrypt.hashpw("12345678",BCrypt.gensalt()),"654411213",LocalDate.of(2005, 06, 28),1));
                listado_usuarios.add(new Asistente("yo", "perez", "yo@email.com", BCrypt.hashpw("12345678",BCrypt.gensalt()), "6544411213", LocalDate.of(2005, 06, 28), "20523207p"));
               listado_usuarios.add(new Asistente("Mari", "Carmen", "maricarmen@email.com", BCrypt.hashpw("12345678",BCrypt.gensalt()), "654411213", LocalDate.of(2005, 06, 28), "20523207p"));
               listado_usuarios.add(new Asistente("Pepe", "perez", "pepe@email.com", BCrypt.hashpw("12345678",BCrypt.gensalt()), "654411213", LocalDate.of(2005, 06, 28), "20523207p"));
                listado_usuarios.add(new Administrador("admin", "Rodriguez", "admin0@email.com",BCrypt.hashpw("12345678",BCrypt.gensalt()),"654411213",LocalDate.of(2005, 06, 28),0));
               */
              //AgregarUsuarios();
        LeerUsuarios();
        /**
         * En este bucle for se iteran salas has 6 y genera un numeor del 1 al 6 a cada sala
         *
         * */


        for (int i = 0; i < 5; i++) {
            int identificador = 0;
            ArrayList<Butaca> mis_butacas = new ArrayList<>();
            //GENERAR BUTACAS
            for (char fila = 'A'; fila <= 'F'; fila++) {
                for (int columna = 1; columna <= 6; columna++) {
                    String pos = fila + "" + columna + "";
                    identificador++;
                    mis_butacas.add(new Butaca(columna, pos, true, true));
                }
            }
            //GENERAR SALAS
            listado_salas[i] = (new Sala("SALA" + i, 200, mis_butacas, 504.7));
        }
        //CREAR BUTACA NO DISPONIBLE


        //CREAR LISTA DE EVENTOS
        /*listado_eventos.add(new Evento("Concierto Ñengo", "Ñengo Flow", listado_salas[0],LocalDate.of(2005, 06, 28), "20:00", 30.00, "Reggaeton", 800));
        listado_eventos.add(new Evento("Rauw Alejandro", "Rauw Alejandro", listado_salas[1], LocalDate.of(2005, 06, 28), "19:00", 20.00, "Reggaeton", 100));
        listado_eventos.add(new Evento("Bad Gyal", "Bad Gyal", listado_salas[2], LocalDate.of(2005, 06, 28), "22:00", 25.00, "Reggaeton", 300));
        listado_eventos.add(new Evento("Raphael", "Mi Gran Noche", listado_salas[3], LocalDate.of(2005, 06, 28), "21:00", 60.00, "Pop-Español", 1200));
        */
        LeerEvento();
        LeerReservas();


    }

    /**
     * Esta funcion esta hecha con el objetivo de dar mas facilidad a la hora de trabajar con los menus siguientes de volver atras y demas
     *
     * @return devuelve true en caso de que la fecha sea correcta y false en caso contrario, asi tambien devolviendo una nueva introducción de fecha
     */
    public void menuPrincipal() throws IOException, EmailExsistenteException {
        String opcion_login;
        boolean salir_menu_principal = false;
        do {
            Scanner entrada_1 = new Scanner(System.in);
            System.out.println("# DELECTARE MULTIEVENTOS #");
            System.out.println();
            System.out.println("        1. Login          ");
            System.out.println("        2. Registro       ");
            System.out.println("        0. SALIR          ");
            System.out.println();
            System.out.print("Por favor, escoja una opción: ");

            opcion_login = entrada_1.nextLine();

            switch (opcion_login) {
                case "1":
                    System.out.println("Has seleccionado Login.");
                    login(usuarioactivo);
                    break;
                case "2":
                    System.out.println("Has seleccionado Registro.");
                    nuevoAsistente();
                    break;
                case "0":
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");

            }
        } while (!opcion_login.equals("1") && !opcion_login.equals("2") && !opcion_login.equals("0"));
    }

    /**
     * Funcion que es el menu de principal de login, donde aprecen dos ramas una para logearte en caso de tener cuenta en DELECTARE MULTIEVENTOS y si no se relaciona con ninguna cyuenta,
     * te manda a un gestor de seguridad que te pregunta por tu cuenta si quieres volver a logearte o registarte.
     */
    public void login(Usuario usuarioactivo) throws IOException,EmailExsistenteException {

        Scanner scanner = new Scanner(System.in);

        /**
         * Pedimo al usuario el correo, y lo validamos a traves de una validacion que se encuentra en la clase validaciones,
         * no comprobamos si pertenece o no al usuario.
         * */
        System.out.print("Introduzca su Correo:");
        String correo = scanner.nextLine();
        if (!Validaciones.ComprobarCorreo(correo)) {
            do {
                correo = scanner.nextLine();
            } while (!Validaciones.ComprobarCorreo(correo));
        }
        /**
         * Pedimo al usuario la contraseña que pertenece a el y la validamos en el sentido de caracteres no
         * si pertece o no al usuario, realiza un condicional para verificar caracteres y si es asi que tiene menos entonces repite la pregunta.
         */
        System.out.print("Introduzca su contraseña:");
        String password = scanner.nextLine();
        if (password.length() < 8) {
            do {
                System.out.print("Contraseña invalidad, introduzca otra vez la contraseña: ");
                password = scanner.nextLine();
            } while (password.length() < 8);
        }

        for (Usuario u : listado_usuarios){
                usuarioactivo = u;
                if(correo.equals(usuarioactivo.getEmail()) && BCrypt.checkpw(password,usuarioactivo.getPassword())){
                    if (usuarioactivo instanceof Asistente){
                        menuDeOpciones(usuarioactivo);
                    }else if(usuarioactivo instanceof Administrador) {
                        menuSuperadminstrador(usuarioactivo);
                    }


                }

            }





        /**
         * Bucle for para ir recorriendo tanto nombre como pasword, una vez echo pasamo a un doble if que lo que hace
         * es decir si es admin a traves de la funcion isEs_Admin, si esta a true pasa como admin si no como asistente normal.
         * */



        System.out.println("Usuario Invalido");
        System.out.println("1.Asegurese de haberse registrado en DELECTARE MULTIEVENTOS, si no lo hecho registrese.");
        System.out.println("2.Si esta registrado en DELECTARE MULTIEVENTOS vuelva a logearse.");
        System.out.println();
        System.out.println();

        String opcion_continuidad;
        do {
            System.out.println("CONTROL DE SEGURIDAD DE DELECTARE");
            System.out.println("Este control de seguridad se hace\npara gestionar la seguridad del sistema\ny para evitar colapsos");
            System.out.println();
            System.out.println("1.Volver a loguearse");
            System.out.println("2.Registrarme en DELECTARE MULTIEVENTOS");
            System.out.println();
            System.out.print("Introducir opcion:");
            opcion_continuidad = scanner.nextLine();
            switch (opcion_continuidad) {
                case "1":
                    login(usuarioactivo);
                case "2":
                    nuevoAsistente();
                default:
                    System.out.println("La opcion introducida no es correcta");

            }

        } while (!opcion_continuidad.equals("1") && !opcion_continuidad.equals("2"));

    }

    /**
     * @param usuarioactivo, este menu es el principal y ya trabajaria con el asistente ingresado, proporciona las opciones de seleccionar eventos y est asu vez mostaria los
     * cuenta con la información de eventos de ese usuario y por ultimo la opcion de log of para deslogearse(se desloguea el asistente)
     * */
    public Evento menuDeOpciones(Usuario usuarioactivo) throws IOException, EmailExsistenteException {
        String email;
        email = usuarioactivo.email;
        String opcion_menu;
        Evento evento = new Evento();
        Scanner entrada_2 = new Scanner(System.in);

        do {
            System.out.println("### DELECTARE MULTIEVENTOS ###");
            System.out.println();
            System.out.println("    1. Seleccionar evento     ");
            System.out.println("    2. Información reservas   ");
            System.out.println("    3. Log Off");
            System.out.println();
            System.out.print("Por favor, escoja una opción: ");
            opcion_menu = entrada_2.nextLine();

            switch (opcion_menu) {
                case "1":
                    boolean iteración_eventos = false;
                    boolean seleccion_evento_ok = false;
                    //Tenemos un do while que el array de lista de eventos
                    do {
                        String seleccion_evento;
                        System.out.println("### Selecciona el evento que desee ###");
                        /*
                        Este forich va recorriendo el array de lista de eventos y  hace la funcion muy snecilla que es la de mostrar el numero que es la posición del array
                        y el nombre asociado a este numero
                        * */
                        int i = 0;
                        for (Evento e : listado_eventos ) {
                            System.out.println(i + " " + e.getNombre());
                            i++;
                        }
                        while (!seleccion_evento_ok) {
                            try {
                                System.out.print("Introduzca numero de evento: ");
                                seleccion_evento = entrada_2.nextLine();
                                /*
                                 * Realizamos un comprobacion con un if para comprobar que tod son numeros y que la distancia de lo que se introduce sea 1,
                                 * una vez se comprueba esto, estonces pasamos a lo siguiennrte
                                 * que se realiza un parseo de string a int , porque estamso trabajando en int para encontar el nombre, fecha, evento ...
                                 * */

                                if (Validaciones.validarNumeros(seleccion_evento)) {
                                    seleccion_evento_ok=true;
                                    BigInteger parseo_seleccion_evento = new BigInteger(seleccion_evento);
                                    if (parseo_seleccion_evento.compareTo(BigInteger.ZERO)>= 0 && parseo_seleccion_evento.compareTo(BigInteger.valueOf(listado_eventos.size())) < 0){
                                        System.out.println(
                                                "Nombre evento: " + listado_eventos.get(parseo_seleccion_evento.intValue()).getNombre() + '\n' +
                                                        "Fecha del evento: " + listado_eventos.get(parseo_seleccion_evento.intValue()).getFecha() + '\n' +
                                                        "Hora del evento evento: " + listado_eventos.get(parseo_seleccion_evento.intValue()).getHora() + '\n' +
                                                        "Precio del evento: " + listado_eventos.get(parseo_seleccion_evento.intValue()).getPrecio() + '\n' +
                                                        "Nobre de la sala: " + listado_eventos.get(parseo_seleccion_evento.intValue()).getSala().getNombre()
                                        );
                                        // Ahora a selección evento obtiene el nombre del evento
                                        seleccion_evento = listado_eventos.get(parseo_seleccion_evento.intValue()).getNombre();
                                        //mediante la funcion que nos devuelve el evento le pasamos el nombre de este evento en concreto
                                        evento = devolver_evento(seleccion_evento, listado_eventos);
                                        //y a menu reservas le pasamos este asistente y el vento en concreto qlo devolvemos en le paso anterior
                                        menu_reservas(evento, usuarioactivo);
                                    }else {
                                        System.out.println("El numero de evento introducido no correcto, vuleva a introducirlo");
                                        iteración_eventos=false;
                                    }
                                } else {
                                    System.out.println("El numero de evento introducido no correcto, vuleva a introducirlo");
                                    iteración_eventos=false;
                                }
                            }catch(NumberFormatException e){
                                System.out.println("Numero de evento incorrecto");
                                iteración_eventos=false;
                            }
                        }



                    } while (!iteración_eventos);
                    break;
                case "2":
                    System.out.println("Aquí puedes ver la información de tus reservas.");
                    //nos manda al menu reservas mediante el email del asistente, ya que es el unico dato por el momento que tenemos del asistente, ya que lo ingresa por tenclado

                    for (Reserva reserva : listado_reservas) {
                        if (reserva.getUsuarioactivo().getEmail().equals(usuarioactivo.getEmail())) {
                            reserva.reservas_realizadas();
                        }
                    }
                    break;
                case "3":
                    System.out.println("¡Hasta luego!");
                    //Esta opcion la he puesto para el casod de que el asistente quiera desloguearse.
                    menuPrincipal();
                    System.exit(1);
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
            }
        } while (!opcion_menu.equals("3"));
        return evento;
    }
    public void menuSuperadminstrador(Usuario usuarioactivo) throws IOException, EmailExsistenteException {
        Scanner scanner = new Scanner(System.in);
        String opcion;
        scanner = new Scanner(System.in);
        String opcionadmin;

        do {
            System.out.println("  #### Bienvendio a la Administración de DELECTARE ###");
            System.out.println();
            System.out.println("             1. Gestión de Asistentes                 ");
            System.out.println("             2. Gestión de Eventos                    ");
            System.out.println("             3. Gestión de Reservas                   ");
            System.out.println("             0. Desloguearse                          ");
            System.out.println();
            System.out.print("Introduce la opcion que desea: ");
            opcionadmin = scanner.nextLine();
            switch (opcionadmin) {
                case "0":
                    menuPrincipal();
                case "1":
                    GestorAsistente();
                    break;
                case "2":
                    GestionarEvento();
                    break;
                case "3":
                    GestorReservas();
                    break;
                default:
                    System.out.println("La opcion que has introducido, no es correcta, vuelva a introducir una opción: ");


            }

        } while (opcionadmin != "0") ;
}
    public static Evento devolver_evento(String nombre, List<Evento> listado_eventos) {
        for (Evento e : listado_eventos) {
            if (e.getNombre().equals(nombre)) {
                return e;
            }
        }
        return null;
    }
    /**
     * Esta funcion genera un nuevo asistente basicamnete esta es la funcion que aprece cuando pulsamos en registrarns en delecatre multieventos,
     * ponemos los datos que queremos introducir de nuestro nuevo asistente asi como gmail, nombre ...
     * Una vez recogemos estos datos no almacenaos en listado_asistentes .
     *
     * */
    public Asistente nuevoAsistente() throws IOException, EmailExsistenteException {
        Scanner scaner_menu_nuevo_asistente = new Scanner(System.in);
        String opciones_menu_newasistente;
        do {
            Asistente asistente = new Asistente();
            System.out.println("Quieres registrarte en # DELECTARE MULTIEVENTOS #");
            System.out.println();
            System.out.println("1. SI");
            System.out.println("2. NO (Salir DELECTARE MULTIEVENTOS)");
            System.out.println("3. Volver al menu");
            System.out.println();
            System.out.print("Introduce la opcion deseada: ");
            opciones_menu_newasistente = scaner_menu_nuevo_asistente.nextLine();

            switch (opciones_menu_newasistente) {
                case "1":
                    System.out.println("Rellena el siguiente formulario:");
                    System.out.println();
                    String email = "";
                        try {
                            do {
                            boolean emailexistente = false;
                            System.out.print("Introduce Email: ");
                            email = scaner_menu_nuevo_asistente.nextLine();
                            for (Usuario usuario : listado_usuarios) {
                                if (usuario instanceof Asistente) {
                                    if (email.equals(usuario.getEmail())) {
                                        throw new  EmailExsistenteException();
                                    }
                                }
                            }
                            if (!Validaciones.ComprobarCorreo(email)) {
                                System.out.println("Email introducido contiene caracteres invalidos:");
                            }
                            } while (!Validaciones.ComprobarCorreo(email));
                        }catch (EmailExsistenteException e){
                            System.out.println(e.getMessage());
                            nuevoAsistente();
                        }

                    System.out.print("Introduce DNI: ");
                    String dni = scaner_menu_nuevo_asistente.nextLine();
                    if (!Validaciones.ComprobarDNI(dni)) {
                        do {
                            dni = scaner_menu_nuevo_asistente.nextLine();
                        } while (!Validaciones.ComprobarDNI(dni));
                    }
                    System.out.print("Introduce Nombre: ");
                    String nombre = scaner_menu_nuevo_asistente.nextLine();
                    if (!Validaciones.ComprobarNombreApellidos(nombre)) {
                        do {
                            nombre = scaner_menu_nuevo_asistente.nextLine();
                        } while (!Validaciones.ComprobarNombreApellidos(nombre));
                    }
                    System.out.print("Introduce Apellidos: ");
                    String apellidos = scaner_menu_nuevo_asistente.nextLine();
                    if (!Validaciones.ComprobarNombreApellidos(apellidos)) {
                        do {
                            apellidos = scaner_menu_nuevo_asistente.nextLine();
                        } while (!Validaciones.ComprobarNombreApellidos(apellidos));
                    }
                    System.out.print("Introduce Telefono: ");
                    String telefono = scaner_menu_nuevo_asistente.nextLine();
                    if (!Validaciones.ComprobarTelefono(telefono)) {
                        do {
                            telefono = scaner_menu_nuevo_asistente.nextLine();
                        } while (!Validaciones.ComprobarTelefono(telefono));
                    }
                    System.out.print("Por favor, ingrese su fecha de nacimiento (DD-MM-YYYY): ");
                    String fecha_nacimiento = scaner_menu_nuevo_asistente.nextLine();
                    if (!Validaciones.ValidarFecha(fecha_nacimiento)) {
                        do {
                            fecha_nacimiento = scaner_menu_nuevo_asistente.nextLine();
                        } while (!Validaciones.ValidarFecha(fecha_nacimiento));

                    }
                    //parseamos la fecha introducida por el usuario
                    LocalDate fecha_parseada = Validaciones.fechaParseada(fecha_nacimiento);
                    System.out.println();
                    System.out.println();
                    System.out.println("#~#Ahora vamos a crear tu contraseña#~#");
                    System.out.print("Introduce tu Contraseña: ");
                    String password = scaner_menu_nuevo_asistente.nextLine();

                    if (password.length() < 8) {
                        do {
                            System.out.print("Contraseña invalidad, introduzca otra vez la contraseña: ");
                            password = scaner_menu_nuevo_asistente.nextLine();
                        } while (password.length() < 8);

                    }
                    String password_encriptada  =BCrypt.hashpw(password, BCrypt.gensalt());

                    listado_usuarios.add(new Asistente(nombre, apellidos, email, password_encriptada, telefono, fecha_parseada, dni));
                    // Agregar el nuevo asistente a la lista
                    AgregarUsuarios();



                    menuPrincipal();
                case "2":
                    System.out.println("Saliendo del programa...");
                    break;
                case "3":
                    System.out.println("Volviendo a tras...");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("La opcion introducida no es valida!!");
                    break;
            }

        } while (!opciones_menu_newasistente.equals("2"));

        return new Asistente();
    }

    /**
     * @param usuarioactivo trabajamos con el objeto asistente, para relacionar la reserva con el asistente en cuestion.
     * @param evento trabajamos con objeto evento opara asociar la reserva con el evento en cuestion.
     * */
    public void menu_reservas( Evento evento, Usuario usuarioactivo) throws IOException, EmailExsistenteException {
        Scanner entrada = new Scanner(System.in);

        String opcion_menu_reservas;
        do {

            System.out.println("¿Quieres hacer la reserva de este evento?");
            System.out.println();
            System.out.println("1. SI");
            System.out.println("2. Atras(Volver al menu anterior)");
            System.out.println();
            Butaca butaca=null;
            System.out.print("Selecciona la opcion deseada:");
            opcion_menu_reservas = entrada.nextLine();
            boolean ocupar_butaca = false;
            switch (opcion_menu_reservas) {
                case "1":
                    Scanner entrada_2 = new Scanner(System.in);
                    String reserva;
                    //Butaca butaca;//no ocupa espacion en memoria, es necesario para ocupar butaca, el problema que tengo es que no ocupa la butaca concreta del evento, si no todas las butacas con la misma cordenada
                    System.out.println("### Has seleccionado realizar la reserva del evento ###");
                    System.out.println();
                    System.out.println();
                    do {
                        System.out.println("Disposición de los asientos");
                        System.out.println("A1, A2, A3 *** A4, A5, A6");
                        System.out.println("B1, B2, B3 *** B4, B5, B6");
                        System.out.println("C1, C2, C3 *** C4, C5, C6");
                        System.out.println("D1, D2, D3 *** D4, D5, D6");
                        System.out.println("E1, E2, E3 *** E4, E5, E6");
                        System.out.println("F1, F2, F3 *** F4, F5, F6");
                        System.out.println("Escoja el asiento que desee, por favor (fila/numero: ");
                        reserva = entrada_2.nextLine();
                        reserva = reserva.toUpperCase();
                        //Validamos los asientos con von la validación de comprobar asientos en validaciones, mediante reserva-evento
                        if (Validaciones.ComprobarAsientos(reserva, evento)) {
                            //Ahora lo que hacemos es coger el evento asociados con la sala y asociamso mediante butaca = null un metodo de la clase sala que asociamos la butaca con la reserva
                            butaca = evento.getSala().asociar_butaca(reserva);
                            confirmar_compra(usuarioactivo,evento, butaca);
                            //Ahora al metodo reservar le pasamos el asistente qeu ha hecho la reserva, el evento que ha reservado y la butaca selecciona
                        }else {
                           menu_reservas(evento, usuarioactivo);
                        }
                    } while (!Validaciones.ComprobarAsientos(reserva, evento));
                    break;
                case "2":
                    System.out.println("Volver al menu para información de reserva");
                    //Metodo de opciones principal, el cual esta traajando con el asistente.
                    menuDeOpciones(usuarioactivo);
                    break;
                default:
                    System.out.println("¡La opcion introducidad no es correcta!");
                    break;
            }
        } while (!opcion_menu_reservas.equals("2") && !opcion_menu_reservas.equals("1"));


    }
    /**
     * @param /// trabajamos sobre el email para encontrar la reserva, ya que es el unico dato que tenemos sobre el usuario
     * realiza un forich para recorrer el array y si coincide despues el gmail con el que hay en el parametro, si coincide se llama al objeto
     *              reservas_realizadas que este se encar de mostrar los datos de la reserva.
     *  **/


    public Evento devolver_evento(String nombre) {
        Evento evento = new Evento();
        for (Evento event : listado_eventos) {
            if (event.getNombre().equals(nombre)) {
                evento = event;
            }
        }
        return evento;
    }

    /**
     * @param usuarioactivo trabajamos con el asistente que se ha introducido.
     * @param butaca trabjamos tambien con el parametro butaca para saber que butaca se reserva
     * @param evento trabajamos con el parametro evento para saber de que evento se ha hecho la reserva.
     * */
    public void reservar(Usuario usuarioactivo, Evento evento, Butaca butaca) throws IOException {
        for (Usuario asis : listado_usuarios) {
            if (asis.nombre.equals(usuarioactivo.getNombre()) && asis.apellido.equals(usuarioactivo.getApellido()) && asis.email.equals(usuarioactivo.getEmail()
            ) && asis.telf.equals(usuarioactivo.getTelf()) && asis.password.equals(usuarioactivo.getPassword())) {
                listado_reservas.add(new Reserva(usuarioactivo, evento, butaca, evento.getFecha(), evento.getHora()));
                AgregarReserva(listado_reservas);
                // TODO: 20/02/2024 Falta solucionar problema de reservar la butaca para que no se pueda volver a reservar,
                //  ya que lo que hace es reservar esa butaca de todas las salas, se encuentra en el metodo confirmar compra.
                evento.getListaAsistentes().add(new Asistente(usuarioactivo.nombre, usuarioactivo.apellido, usuarioactivo.email, usuarioactivo.telf,
                        ((Asistente)usuarioactivo).getDni(), usuarioactivo.fechaNacimiento, usuarioactivo.password));
                System.out.println("El eveto esta reservado falta, añadir forma de pago "+usuarioactivo.nombre);
            }

        }
    }

 /**
 * @param usuarioactivo trabajamos con el asistente que se ha introducido.Lo que hacemos es que el asistente hace la reserva es decir ese asistente paga
 * y trabaja con asistente para mandarlo despues a menu principal.
 *
 * **/
    public void metodo_pagos(Usuario usuarioactivo) throws IOException, EmailExsistenteException {
        //VALIDACIONES DE PAGO
        String opcion_pago;
        String correo_paypal;
        boolean comprobar_correo_pago = false;
        Scanner pago = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        boolean comprobar_opcion = false;
        do {
            System.out.println("**SELECCIONA LA FOMRA DE PAGO**");
            System.out.println("1. PayPal");
            System.out.println("2. Bizum");
            System.out.println("3. Cuena bancaria");
            System.out.println("4. Salir...");
            System.out.print("INTRODUCE TU NUMERO->");
            opcion_pago = entrada.nextLine();
            switch (opcion_pago) {
                case "1":
                    do {
                        System.out.println("**Vas a proceder a pagar con PayPal**");
                        System.out.print("Introduce tu Correo Electronico:");
                        correo_paypal = pago.nextLine();
                        comprobar_correo_pago = Validaciones.ComprobarCorreo(correo_paypal);
                    } while (!comprobar_correo_pago);
                    System.out.println("Operacion realizada con Paypal");
                    menuDeOpciones(usuarioactivo);
                    comprobar_opcion = false;
                    break;
                case "2":
                    System.out.println("Vas a proceder a pagar con Bizum:");
                    System.out.println("Realiza un pago al siguiente numero 65441****");
                    menuDeOpciones(usuarioactivo);
                    comprobar_opcion = false;
                    break;
                case "3":
                    //Verifica formato IBAN
                    String iban;
                    boolean comprobar_cuentabancaria = false;
                    do {
                        System.out.println("Vas a proceder a pagar con Cuenta Bancaria");
                        System.out.print("Introduce un numero de cuenta bancaria:");
                        iban = pago.nextLine().toUpperCase().replace(" ", "");
                        comprobar_cuentabancaria = Validaciones.ComprobarIban(iban);
                    } while (!comprobar_cuentabancaria);
                    menuDeOpciones(usuarioactivo);
                    comprobar_opcion = false;
                    break;
                case "4":
                    System.out.println("Saliendo....");
                    comprobar_opcion = false;
                    break;
                default:
                    System.out.println("Metodo de pago no introducido");
                    comprobar_opcion = true;
            }

        }while (comprobar_opcion);

    }
    /**
     * @param usuarioactivo le paso asistente para que trabe con el y sepa que el ese asistente a confirmado la compra
     *
     * **/
    public void confirmar_compra( Usuario usuarioactivo,  Evento evento, Butaca butaca) throws IOException, EmailExsistenteException {
        String opcion_comprobar_compra;
        Scanner entrada = new Scanner(System.in);
        do {

            System.out.println("Confirmación de compra en DELECTARE MULTIEVENTOS");
            System.out.println();
            System.out.println("1. Confirmar Compra y continuar con el pago");
            System.out.println("2. Salir de la reserva");
            System.out.println();
            System.out.print("Selecciona la opcion deseada: ");
            opcion_comprobar_compra = entrada.nextLine();
            switch (opcion_comprobar_compra) {
                case "1":
                    butaca = evento.getSala().ocuparButaca(butaca);
                    reservar(usuarioactivo, evento, butaca);
                    metodo_pagos(usuarioactivo);
                    break;
                case "2":
                    System.out.println("Saliendo de la reservar del evento");
                    menuDeOpciones(usuarioactivo);
                    break;
                default:
                    System.out.println("La opcion introducida no es correctar vuelva a intentarlo: ");
            }


        } while (!opcion_comprobar_compra.equals("2"));
    }
    public  Evento GestionarEvento() throws IOException, InputMismatchException, EmailExsistenteException {
        Scanner scaner_menu_nuevo_evento = new Scanner(System.in);
        String opciones_menu_newevento;
        Sala sala;
        int numero_sala = 0;
        boolean atributocontrol = false;
        boolean preciocorrecto = false;
        boolean numeroasistentescorrecto=false;
        String fecha_evento;
        int numeroAsistentesmaximo = 0;
        boolean salacorrecta = false;
        double precio_evento = 0.0;
        do {
            System.out.println("GESTION DE EVENTOS");
            System.out.println();
            System.out.println("1. Agregar Evento");
            System.out.println("2. Modificar Evento");
            System.out.println("3. Eliminar Evento");
            System.out.println("4. Mostrar Evento");
            System.out.println("5. Listar Eventos");
            System.out.println("6. Listar un unico Evento");
            System.out.println("0. Salir al gestor de Administrador");
            System.out.println();
            System.out.print("Introduce la opcion deseada: ");
            opciones_menu_newevento = scaner_menu_nuevo_evento.nextLine();

            switch (opciones_menu_newevento) {
                case "1":
                    System.out.print("Introduce Nombre del Evento: ");
                    String nombre = scaner_menu_nuevo_evento.nextLine();
                    if (!Validaciones.ComprobarNombreApellidos(nombre)) {
                        do {
                            nombre = scaner_menu_nuevo_evento.nextLine();
                        } while (!Validaciones.ComprobarNombreApellidos(nombre));
                    }
                    System.out.print("Introduce Nombre del Invitado: ");
                    String invitado = scaner_menu_nuevo_evento.nextLine();
                    if (!Validaciones.ComprobarNombreApellidos(invitado)) {
                        do {
                            invitado = scaner_menu_nuevo_evento.nextLine();
                        } while (!Validaciones.ComprobarNombreApellidos(invitado));
                    }
                    int i = 0;
                    for (Sala s : listado_salas ) {
                        System.out.println(i + " " + s.getNombre());
                        i++;
                    }
                    while (!salacorrecta) {
                        try {
                            System.out.println("Seleccione el numero de la sala (1-5): ");
                            numero_sala =scaner_menu_nuevo_evento.nextInt();
                            scaner_menu_nuevo_evento.nextLine();
                            //COMPROBAR QUE LA SALA EXISTE
                            if (numero_sala > 0 &&  numero_sala <= listado_salas.length){
                                salacorrecta = true;
                            }

                        } catch (InputMismatchException ex) {
                            System.out.println("No has introducido ningun numero, introduce el numero de la sala: ");
                            scaner_menu_nuevo_evento.nextLine();
                        }
                    }
                    sala = listado_salas[numero_sala];


                    System.out.print("Por favor, ingrese su fecha del evento (DD-MM-YYYY): ");
                     fecha_evento = scaner_menu_nuevo_evento.nextLine();
                    if (!Validaciones.ValidarFechaEvento(fecha_evento)) {
                        do {
                            fecha_evento = scaner_menu_nuevo_evento.nextLine();
                        } while (!Validaciones.ValidarFechaEvento(fecha_evento));
                    }
                    System.out.print("Introduce Hora del Evento: ");
                    String hora_evento = scaner_menu_nuevo_evento.nextLine();
                    if (!Validaciones.ValidarHora(hora_evento)) {
                        do {
                            hora_evento = scaner_menu_nuevo_evento.nextLine();
                        } while (!Validaciones.ValidarHora(hora_evento));
                    }
                    while (!preciocorrecto){
                        try {
                            System.out.print("Introduce Precio del Evento: ");
                             precio_evento = scaner_menu_nuevo_evento.nextDouble();
                            preciocorrecto = true;
                            scaner_menu_nuevo_evento.nextLine();
                        } catch (InputMismatchException e) {
                            scaner_menu_nuevo_evento.nextLine();//volvemos a mostrar el
                        }
                    }

                    System.out.print("Introduce el Tipo del Evento: ");
                    String tipoEvento= scaner_menu_nuevo_evento.nextLine();
                    if (!Validaciones.ComprobarNombreApellidos(tipoEvento)) {
                        do {
                            tipoEvento = scaner_menu_nuevo_evento.nextLine();
                        } while (!Validaciones.ComprobarNombreApellidos(tipoEvento));
                    }

                    while (!numeroasistentescorrecto){
                        try {
                            System.out.print("Introduce Numero maximo asistentes: ");
                            numeroAsistentesmaximo = scaner_menu_nuevo_evento.nextInt();
                            numeroasistentescorrecto = true;
                        } catch (InputMismatchException e) {
                            scaner_menu_nuevo_evento.nextLine();//volvemos a mostrar el
                        }
                    }
                    LocalDate fecha_parseada = Validaciones.fechaParseada(fecha_evento);
                    listado_eventos.add(new Evento(nombre,invitado,sala,fecha_parseada,hora_evento,precio_evento,tipoEvento,numeroAsistentesmaximo));
                    AgregarEvento();
                    System.out.println("Evento añadido con exito: "+usuarioactivo.getNombre());
                    break;
                case "2":
                    System.out.println("Modificar Evento");
                    break;
                case "3":
                    System.out.println("Eliminar Evento");
                    EliminarEvento(usuarioactivo);
                    break;
                case "4":
                    System.out.println("Mostrar Evento");
                    break;
                case "5":
                    System.out.println("Listado de Eventos");
                    ListarEventos();
                    GestionarEvento();
                    break;
                case "6":
                    System.out.println("Informacion del evento seleccionado");
                    ListarUnEvento();
                    GestionarEvento();
                    break;
                default:
                    System.out.println("Vuelva a introducir la opcion: ");
            }

        } while (!opciones_menu_newevento.equals("0"));

        return new Evento();


    }

    public void AgregarUsuarios() throws IOException {
        try{
            fos=new FileOutputStream("src/data/usuariosdefinitivos.dat");
            oss=new ObjectOutputStream(fos);
            for (Usuario u: listado_usuarios){
                oss.writeObject(u);

            }

        }catch (FileNotFoundException ex){

        }finally {
            fos.close();
        }
    }
    public void LeerUsuarios() throws IOException{
        FileInputStream fis = new FileInputStream("src/Data/usuariosdefinitivos.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try{
            while (true) {
                try {
                    Usuario u = (Usuario) ois.readObject();
                    listado_usuarios.add(u);
                } catch (EOFException eofx) {
                    break;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ois.close();
            fis.close();
        }

    }
    public void AgregarEvento() throws IOException {
        try{
            fos=new FileOutputStream("src/data/listadoeventos.dat");
            oss=new ObjectOutputStream(fos);
            for (Evento e: listado_eventos){
                oss.writeObject(e);

            }

        }catch (FileNotFoundException ex){

        }finally {
            fos.close();
        }
    }

    public void LeerEvento() throws IOException {
        fis_eventos = new FileInputStream("src/data/listadoeventos.dat");
        ois_eventos = new ObjectInputStream(fis_eventos);
        try{
            while (true) {

                try {
                    Evento e = (Evento) ois_eventos.readObject();
                    listado_eventos.add(e);
                } catch (EOFException eofx) {
                    System.out.println("Fichero leído");
                    break;
                }
            }
            ois_eventos.close();
            fis_eventos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void AgregarReserva(ArrayList<Reserva> listado_reservas) throws IOException {
        FileOutputStream fos_reservas = new FileOutputStream("src/data/listadoreservasdef.dat");
        ObjectOutputStream oss_reservas = new ObjectOutputStream(fos_reservas);
        try{
            for (Reserva r : listado_reservas) {
                oss_reservas.writeObject(r);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void EliminarEvento(Usuario usuarioactivo) throws IOException, EmailExsistenteException {
        Scanner sacnner_de_eliminar_evento = new Scanner(System.in);
        boolean repetir = true;
        int eleccion;

        do {
            System.out.println("### ELIMINAR EVENTOS ###");
            int j = 1;
            for (Evento e : listado_eventos) {
                System.out.println(j + "/////" + e.getNombre() + " --- " + e.getInvitado() + " --- " + e.getSala().nombre + " --- " + e.getFecha() + "---" + e.getPrecio() + " €" + " --- " + e.getTipoEvento() + " --- " + "Num-Asist " + e.getNumeoroAsistentesmaximo() + " --- ");
                j++;
            }
            System.out.println("0. Volver al menú del administrador");
            System.out.print("Elige el número del evento a eliminar: ");

            if (sacnner_de_eliminar_evento.hasNextInt()) {
                eleccion = sacnner_de_eliminar_evento.nextInt();
                sacnner_de_eliminar_evento.nextLine();

                if (eleccion == 0) {
                    menuSuperadminstrador(usuarioactivo);
                } else if (eleccion > 0 && eleccion <= listado_eventos.size()) {
                    listado_eventos.remove(eleccion - 1);
                    System.out.println("¡Evento eliminado con éxito!");
                    AgregarEvento();
                } else {
                    System.out.println("Número inválido. Inténtalo de nuevo.");
                }
            } else {
                System.out.println("Entrada inválida. Escribe un número.");
                sacnner_de_eliminar_evento.nextLine();
            }
        } while (repetir);
    }

    public void GestorAsistente() throws EmailExsistenteException, IOException {
        String opcion_gestor_asistente;
        Scanner entrada = new Scanner(System.in);
        do {

            System.out.println("Bienvenido al Gestor de Asistente");
            System.out.println();
            System.out.println("1. Listar Asistentes");
            System.out.println("2. Ver un unico Asistente");
            System.out.println("0. Salir al menu principal");
            System.out.println();
            System.out.print("Selecciona la opcion deseada: ");
            opcion_gestor_asistente = entrada.nextLine();
            switch (opcion_gestor_asistente) {
                case "0":
                    menuSuperadminstrador(usuarioactivo);
                    break;
                case "1":
                    ListarAsistentes();
                    GestorAsistente();
                    break;
                case "2":
                    ListarUnicoAsistente();
                    GestorAsistente();
                    break;
                default:
                    System.out.println("La opcion introducida no es correctar vuelva a intentarlo: ");
            }


        } while (!opcion_gestor_asistente.equals("2"));
    }
    public void GestorReservas() throws EmailExsistenteException, IOException {
        String opcion_gestor_reserva;
        Scanner entrada = new Scanner(System.in);
        do {

            System.out.println("Bienvenido al Gestor de Reserva");
            System.out.println();
            System.out.println("1. Listar Reserva");
            System.out.println("2. Ver una unica Reserva");
            System.out.println("0. Salir al menu principal");
            System.out.println();
            System.out.print("Selecciona la opcion deseada: ");
            opcion_gestor_reserva = entrada.nextLine();
            switch (opcion_gestor_reserva) {
                case "0":
                    menuSuperadminstrador(usuarioactivo);
                    break;
                case "1":
                    ListarReservas();
                    GestorReservas();
                    break;
                case "2":
                    ListarUnaReserva();
                    GestorReservas();
                    break;
                default:
                    System.out.println("La opcion introducida no es correctar vuelva a intentarlo: ");
            }


        } while (!opcion_gestor_reserva.equals("2"));
    }

    public void ListarAsistentes(){

        int i = 1;
        for (Usuario u : listado_usuarios ) {
            if (u instanceof Asistente) {
                System.out.println(i + "|" + u.getNombre() + "|" + u.getApellido() + "|" + u.getEmail() + "|" + u.getTelf() + "|" + u.getFechaNacimiento() + "|" + u.getPassword());
                i++;
            }
        }
    }

    public void ListarUnicoAsistente(){
        boolean asistentecorrecto = false;
        Scanner entrada = new Scanner (System.in);
        int numero_usuario = 0;
        Asistente asistenteparamostrar = null;

                while (!asistentecorrecto) {
                    try {
                        ArrayList<Asistente> asistente_mostrar = new ArrayList<>();
                        int i = 1;
                        for (Usuario u  : listado_usuarios ) {
                            if (u instanceof Asistente) {
                                asistente_mostrar.add((Asistente)u);
                                System.out.println(i + " " + u.getNombre());
                                i++;
                            }
                        }

                        System.out.println("Seleccione el numero de la usuario: ");
                        numero_usuario=entrada.nextInt();
                        entrada.nextLine();
                                if (numero_usuario > 0 && numero_usuario <= asistente_mostrar.size()) {
                                    Asistente asistenteSeleccionado = asistente_mostrar.get(numero_usuario - 1);
                                    System.out.println(asistenteSeleccionado.getNombre() + "|" + asistenteSeleccionado.getApellido() + "|" + asistenteSeleccionado.getEmail() + "|" + asistenteSeleccionado.getTelf() + "|" + asistenteSeleccionado.getFechaNacimiento() + "|" + asistenteSeleccionado.getPassword());
                                    asistentecorrecto = true;
                                }


                    } catch (InputMismatchException ex) {
                        System.out.println("No has introducido ningun numero, introduce el numero de la sala: ");
                        entrada.nextLine();
                    }
                }

        }

    public void ListarEventos(){

        int i = 1;
        for (Evento e: listado_eventos ) {
                System.out.println(i + "|" + e.getNombre() + "|" + e.getTipoEvento() + "|" + e.getSala() + "|" + e.getPrecio() + "|" + e.getHora() + "|" + e.getFecha()+ "|" + e.getInvitado()+ "|" + e.getNumeoroAsistentesmaximo());
                i++;
            }
        }
    public void ListarUnaReserva(){
        boolean eventocorrecto = false;
        Scanner entrada = new Scanner (System.in);
        int numero_reserva = 0;

        while (!eventocorrecto) {
            try {
                ArrayList<Reserva> reserva_mostrar = new ArrayList<>();
                int i = 1;
                for (Reserva r  : listado_reservas ) {
                        reserva_mostrar.add((Reserva) r);
                        System.out.println(i + " " + r.getId());
                        i++;
                }

                System.out.println("Seleccione el numero de la usuario: ");
                numero_reserva=entrada.nextInt();
                entrada.nextLine();
                if (numero_reserva > 0 && numero_reserva <= reserva_mostrar.size()) {
                    Reserva reserva_seleccionada = reserva_mostrar.get(numero_reserva - 1);
                    reserva_seleccionada.reservas_realizadas();
                    eventocorrecto = true;
                }


            } catch (InputMismatchException ex) {
                System.out.println("No has introducido ningun numero, introduce el numero de la sala: ");
                entrada.nextLine();
            }
        }




    }
    public void ListarUnEvento() {
        boolean eventocorrecto = false;
        Scanner entrada = new Scanner(System.in);
        int numero_usuario = 0;

        while (!eventocorrecto) {
            try {
                ArrayList<Evento> evento_mostrar = new ArrayList<>();
                int i = 1;
                for (Evento u : listado_eventos) {
                    evento_mostrar.add((Evento) u);
                    System.out.println(i + " " + u.getNombre());
                    i++;
                }

                System.out.println("Seleccione el numero de la usuario: ");
                numero_usuario = entrada.nextInt();
                entrada.nextLine();
                if (numero_usuario > 0 && numero_usuario <= evento_mostrar.size()) {
                    Evento evento_seleccionado = evento_mostrar.get(numero_usuario - 1);
                    System.out.println(evento_seleccionado.getNombre() + "|" + evento_seleccionado.getTipoEvento() + "|" + evento_seleccionado.getSala() + "|" + evento_seleccionado.getPrecio() + "|" + evento_seleccionado.getHora() + "|" + evento_seleccionado.getFecha() + "|" + evento_seleccionado.getInvitado() + "|" + evento_seleccionado.getNumeoroAsistentesmaximo());
                    eventocorrecto = true;
                }


            } catch (InputMismatchException ex) {
                System.out.println("No has introducido ningun numero, introduce el numero de la sala: ");
                entrada.nextLine();
            }
        }


    }
    public void LeerReservas () throws IOException {
        FileInputStream fis_reservas = new FileInputStream("src/data/listadoreservasdef.dat");
        ObjectInputStream ois_reservas = new ObjectInputStream(fis_reservas);
        try {

            while (true) {
                try {
                    Reserva r = (Reserva) ois_reservas.readObject();
                    // Comprueba si la reserva pertenece al usuario logueado
                    listado_reservas.add(r);
                } catch (EOFException eofx) {
                    eofx.getMessage();
                    // Fin del archivo alcanzado
                    System.out.println("Fichero leído");
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Mostrar las reservas del usuario activo

    }
    public void ListarReservas () throws IOException {
        int i = 1;
        for (Reserva r : listado_reservas) {
            System.out.println(i + "|" + r.getId() + "|" + r.usuarioactivo.getNombre() + "|" + r.getEvento().getNombre() + "|" + r.getButaca().getCordenada() + "|" + r.getHora() + "|" + r.getFecha() + "|" + r.getEvento().getSala().getNombre());
            i++;
        }
    }
    }








