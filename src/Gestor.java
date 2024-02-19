import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestor {
    ArrayList<Evento> listado_eventos = new ArrayList<>();
    Sala listado_salas[] = new Sala[5];
    ArrayList<Asistente> listado_asistentes = new ArrayList<>();
    ArrayList<Reserva> listado_reservas = new ArrayList<>();


    Gestor() {
        info_inicial();
    }

    public ArrayList<Evento> getListado_eventos() {
        return listado_eventos;
    }

    public Sala[] getListado_salas() {
        return listado_salas;
    }

    public ArrayList<Asistente> getListado_asistentes() {
        return listado_asistentes;
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

    public void setListado_asistentes(ArrayList<Asistente> listado_asistentes) {
        this.listado_asistentes = listado_asistentes;
    }

    public void setListado_reservas(ArrayList<Reserva> listado_reservas) {
        this.listado_reservas = listado_reservas;
    }

    private void info_inicial() {
        //CREAR LISTA DE ASISTENTES
        listado_asistentes.add(new Asistente("admin", "Rodriguez", "admin@email.com", "12345678", true));
        listado_asistentes.add(new Asistente("yo", "perez", "yo@email.com", "6544411213", "20523207p", LocalDate.of(2005, 06, 28), "12345678"));
        listado_asistentes.add(new Asistente("Mari", "Carmen", "maricarmen@email.com", "654654654", "12345678z", LocalDate.of(2005, 06, 28), "12345678"));
        listado_asistentes.add(new Asistente("Pepe", "perez", "pepe@email.com", "6544411213", "12345678z", LocalDate.of(2005, 06, 28), "12345678"));

        /**
         * En este bucle for se iteran salas has 6 y genera un numeor del 1 al 6 a cada sala
         *
         * */
        int identificador = 0;
        ArrayList<Butaca> mis_butacas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //GENERAR BUTACAS
            for (char fila = 'A'; fila <= 'F'; fila++) {
                for (int columna = 1; columna <= 6; columna++) {
                    String pos = fila + "" + columna + "";
                    identificador++;
                    mis_butacas.add(new Butaca( columna, pos, true, true));
                }
            }
            //GENERAR SALAS
            listado_salas[i] = new Sala("SALA" + i, 200, mis_butacas, 504.7);
            listado_salas[i] = new Sala("SALA" + i, 400, mis_butacas, 650.9);
            listado_salas[i] = new Sala("SALA" + i, 400, mis_butacas, 650.9);
            listado_salas[i] = new Sala("SALA" + i, 400, mis_butacas, 650.9);
            listado_salas[i] = new Sala("SALA" + i, 400, mis_butacas, 650.9);
        }
        //CREAR BUTACA NO DISPONIBLE


        //CREAR LISTA DE EVENTOS
        listado_eventos.add(new Evento("Concierto Ñengo", "Ñengo Flow", listado_salas[1], "2024-10-02", "20:00", 30.00, "Reggaeton", 800));
        listado_eventos.add(new Evento("Rauw Alejandro", "Rauw Alejandro", listado_salas[2], "2024-04-28", "19:00", 20.00, "Reggaeton", 100));
        listado_eventos.add(new Evento("Bad Gyal", "Bad Gyal", listado_salas[3], "2024-10-12", "22:00", 25.00, "Reggaeton", 300));
        listado_eventos.add(new Evento("Raphael", "Mi Gran Noche", listado_salas[0], "2024-05-12", "21:00", 60.00, "Pop-Español", 1200));
    }

    /**
     * Esta funcion esta hecha con el objetivo de dar mas facilidad a la hora de trabajar con los menus siguientes de volver atras y demas
     * @return devuelve true en caso de que la fecha sea correcta y false en caso contrario, asi tambien devolviendo una nueva introducción de fecha
     */
    public void menuPrincipal(Asistente asistente) {
        String opcion_login;
        boolean salir_menu_principal=false;
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
                    login(asistente);
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
     *
     * */
    public void login( Asistente asistente) {

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

        /**
         * Bucle for para ir recorriendo tanto nombre como pasword, una vez echo pasamo a un doble if que lo que hace
         * es decir si es admin a traves de la funcion isEs_Admin, si esta a true pasa como admin si no como asistente normal.
         * */
        for (Asistente a : listado_asistentes) {
            if (a.getEmail().equals(correo) && a.getPassword().equals(password)) {
                /**
                 * Validacion Es_admin
                 * **/
                asistente = a;
                if (a.isEs_admin()) {
                    System.out.println("Bienvenido Administrador de DELECTARE MULTIEVENTOS");
                    menuPrincipal(asistente);// TODO: 19/02/2024 futuro menu admin
                } else {
                    System.out.println("Bienvenido " + correo);
                    //porque menu de opciones tiene dentro asistente ? porque trabaja ya con el asistente es decir ya esta logueado.
                    menuDeOpciones(asistente);
                }

            }

        }
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
                    login(asistente);
                case "2":
                    nuevoAsistente();
                default:
                    System.out.println("La opcion introducida no es correcta");

            }

        } while (!opcion_continuidad.equals("1") && !opcion_continuidad.equals("2"));


    }

    /**
     * @param asistente, este menu es el principal y ya trabajaria con el asistente ingresado, proporciona las opciones de seleccionar eventos y est asu vez mostaria los
     * cuenta con la información de eventos de ese usuario y por ultimo la opcion de log of para deslogearse(se desloguea el asistente)
     * */
    public void menuDeOpciones(Asistente asistente) {
        String email;
        email = asistente.getEmail();
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
                    //Tenemos un do while que el array de lista de eventos
                    do {
                        String seleccion_evento;
                        System.out.println("### Selecciona el evento que desee ###");
                        /*
                        Este forich va recorriendo el array de lista de eventos y  hace la funcion muy snecilla que es la de mostrar el numero que es la posición del array
                        y el nombre asociado a este numero
                        * */
                        int i = 0;
                        for (Evento e : listado_eventos) {
                            System.out.println(i + " " + e.getNombre());
                            i++;
                        }
                        System.out.print("Introduzca numero de evento: ");
                        seleccion_evento = entrada_2.nextLine();
                        /*
                        * Realizamos un comprobacion con un if para comprobar que tod son numeros y que la distancia de lo que se introduce sea 1,
                        * una vez se comprueba esto, estonces pasamos a lo siguiennrte
                        * que se realiza un parseo de string a int , porque estamso trabajando en int para encontar el nombre, fecha, evento ...
                        * */
                        if (Validaciones.validarNumeros(seleccion_evento)) {
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
                                evento = devolver_evento(seleccion_evento);
                                //y a menu reservas le pasamos este asistente y el vento en concreto qlo devolvemos en le paso anterior
                                menu_reservas(asistente, evento);
                            }else {
                                System.out.println("El numero de evento introducido no correcto, vuleva a introducirlo");
                                iteración_eventos=false;
                            }
                        } else {
                            System.out.println("El numero de evento introducido no correcto, vuleva a introducirlo");
                            iteración_eventos=false;
                        }
                    } while (!iteración_eventos);
                    break;
                case "2":
                    System.out.println("Aquí puedes ver la información de tus reservas.");
                    //nos manda al menu reservas mediante el email del asistente, ya que es el unico dato por el momento que tenemos del asistente, ya que lo ingresa por tenclado
                    mostrar_reservas(email);
                    break;
                case "3":
                    System.out.println("¡Hasta luego!");
                    //Esta opcion la he puesto para el casod de que el asistente quiera desloguearse.
                    menuPrincipal(asistente);
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
            }
        } while (!opcion_menu.equals("3"));
    }

    /**
     * Esta funcion genera un nuevo asistente basicamnete esta es la funcion que aprece cuando pulsamos en registrarns en delecatre multieventos,
     * ponemos los datos que queremos introducir de nuestro nuevo asistente asi como gmail, nombre ...
     * Una vez recogemos estos datos no almacenaos en listado_asistentes .
     *
     * */
    public Asistente nuevoAsistente() {
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
                    System.out.print("Introduce Email: ");
                    do {
                        email = scaner_menu_nuevo_asistente.nextLine();
                    } while (!Validaciones.ComprobarCorreo(email));

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
                    System.out.print("Introduce DNI: ");
                    String dni = scaner_menu_nuevo_asistente.nextLine();
                    if (!Validaciones.ComprobarDNI(dni)) {
                        do {
                            dni = scaner_menu_nuevo_asistente.nextLine();
                        } while (!Validaciones.ComprobarDNI(dni));
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
                    listado_asistentes.add(new Asistente(nombre, apellidos, email, telefono, dni, fecha_parseada, password));

                    menuPrincipal(asistente);
                case "2":
                    System.out.println("Saliendo del programa...");
                    break;
                case "3":
                    System.out.println("Volviendo a tras...");
                    menuPrincipal(asistente);
                    break;
                default:
                    System.out.println("La opcion introducida no es valida!!");
                    break;
            }

        } while (!opciones_menu_newasistente.equals("2"));

        return new Asistente();
    }
    /**
     * @param asistente trabajamos con el objeto asistente, para relacionar la reserva con el asistente en cuestion.
     * @param evento trabajamos con objeto evento opara asociar la reserva con el evento en cuestion.
     * */
    public void menu_reservas(Asistente asistente, Evento evento) {
        Asistente a = new Asistente();
        Evento e = new Evento();
        Scanner entrada = new Scanner(System.in);

        String opcion_menu_reservas;
        do {

            System.out.println("¿Quieres hacer la reserva de este evento?");
            System.out.println();
            System.out.println("1. SI");
            System.out.println("2. Atras(Volver al menu anterior)");
            System.out.println();
            System.out.print("Selecciona la opcion deseada:");
            opcion_menu_reservas = entrada.nextLine();
            boolean ocupar_butaca = false;
            switch (opcion_menu_reservas) {
                case "1":
                    Scanner entrada_2 = new Scanner(System.in);
                    String reserva;
                    Butaca butaca = null;
                    System.out.println("### Has seleccionado realizar la reserva del evento ###");
                    System.out.println();
                    System.out.println();
                    do {
                        System.out.println("Disposición de los asientos");
                        System.out.println("A1, A2, A3 *** A4, A5, A6");
                        System.out.println("B1, B2, B3 *** B4, B5, B6");
                        System.out.println("C1, C2, C3 *** C4, C5, C6");
                        System.out.println("E1, E2, E3 *** E4, E5, E6");
                        System.out.println("F1, F2, F3 *** F4, F5, F6");
                        System.out.println("Escoja el asiento que desee, por favor (fila/numero: ");
                        reserva = entrada_2.nextLine();
                        reserva = reserva.toUpperCase();
                        //Validamos los asientos con von la validación de comprobar asientos en validaciones, mediante reserva-evento
                        if (Validaciones.ComprobarAsientos(reserva, evento)) {
                            //Ahora lo que hacemos es coger el evento asociados con la sala y asociamso mediante un metodo de la clase sala que asociamos la butaca con la reserva
                            butaca = evento.getSala().asociar_butaca(reserva);
                            //Ahora al metodo reservar le pasamos el asistente qeu ha hecho la reserva, el evento que ha reservado y la butaca selecciona.
                            reservar(asistente, evento, butaca);
                           
                        }
                    } while (!Validaciones.ComprobarAsientos(reserva, evento));

                    confirmar_compra(asistente,butaca);


                    break;
                case "2":
                    System.out.println("Volver al menu para información de reserva");
                    //Metodo de opciones principal, el cual esta traajando con el asistente.
                    menuDeOpciones(asistente);
                    break;
                default:
                    System.out.println("¡La opcion introducidad no es correcta!");
                    break;
            }
        } while (!opcion_menu_reservas.equals("2") && !opcion_menu_reservas.equals("1"));


    }
    /**
     * @param email trabajamos sobre el email para encontrar la reserva, ya que es el unico dato que tenemos sobre el usuario
     * realiza un forich para recorrer el array y si coincide despues el gmail con el que hay en el parametro, si coincide se llama al objeto
     *              reservas_realizadas que este se encar de mostrar los datos de la reserva.
     *  **/
    public void mostrar_reservas(String email) {
        for (Reserva reser : listado_reservas) {
            if (reser.getAsistente().getEmail().equals(email)) {
                reser.reservas_realizadas();
            }
        }
    }


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
     * @param asistente trabajamos con el asistente que se ha introducido.
     * @param butaca trabjamos tambien con el parametro butaca para saber que butaca se reserva
     * @param evento trabajamos con el parametro evento para saber de que evento se ha hecho la reserva.
     * */
    public void reservar(Asistente asistente, Evento evento, Butaca butaca) {
        for (Asistente asis : listado_asistentes) {
            if (asis.getNombre().equals(asistente.getNombre()) && asis.getApellidos().equals(asistente.getApellidos()) && asis.getEmail().equals(asistente.getEmail()) && asis.getTelefono().equals(asistente.getTelefono()) && asis.getDni().equals(asistente.getDni()) && asis.getFecha_nacimiento().equals(asistente.getFecha_nacimiento()) && asis.getPassword().equals(asistente.getPassword())) {
                listado_reservas.add(new Reserva(asistente, evento, butaca, evento.getFecha(), evento.getHora()));
                evento.getListaAsistentes().add(new Asistente(asistente.getNombre(), asistente.getApellidos(), asistente.getEmail(), asistente.getTelefono(), asistente.getDni(), asistente.getFecha_nacimiento(), asistente.getPassword()));
                System.out.println("El eveto esta reservado falta, añadir forma de pago "+asistente.getNombre());
            }


        }
    }

    public void metodo_pagos(Asistente asistente) {
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
                    menuDeOpciones(asistente);
                    comprobar_opcion = false;
                    break;
                case "2":
                    System.out.println("Vas a proceder a pagar con Bizum:");
                    System.out.println("Realiza un pago al siguiente numero 65441****");
                    menuDeOpciones(asistente);
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
                    menuDeOpciones(asistente);
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
    public void confirmar_compra( Asistente asistente, Butaca butaca) {
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
                    butaca.setDisponible(false);
                    metodo_pagos(asistente);
                    break;
                case "2":
                    System.out.println("Saliendo de la reservar del evento");
                    menuDeOpciones(asistente);
                    break;
                default:
                    System.out.println("La opcion introducida no es correctar vuelva a intentarlo: ");
            }


        } while (!opcion_comprobar_compra.equals("2"));
    }

    // TODO: 19/02/2024
    //
    //
    //  evento.getSala().butaca_ocupada(butaca);
}






