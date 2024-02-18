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
        listado_asistentes.add(new Asistente("Pepe", "Rodriguez", "pepe@email.com", "12345678", false));
        listado_asistentes.add(new Asistente("Pepa", "Rodriguez", "pepa@email.com", "12345678", false));
        listado_asistentes.add(new Asistente("Admin", "Rodriguez", "admin@email.com", "12345678", true));
        listado_asistentes.add(new Asistente("yo", "perez", "yo@email.com", "6544411213", "20523207p", LocalDate.of(2005, 06, 28), "12345678"));
        listado_asistentes.add(new Asistente("Mari", "Carmen", "maricarmen@email.com", "654654654", "12345678z", LocalDate.of(2005, 06, 28), "12345678"));

        int identificador = 0;
        ArrayList<Butaca> mis_butacas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ;
            //GENERAR BUTACAS
            for (char fila = 'A'; fila <= 'F'; fila++) {
                for (int columna = 1; columna <= 6; columna++) {
                    String pos = fila + "" + columna + "";
                    identificador++;
                    mis_butacas.add(new Butaca((i + 1) * 100 + columna, pos, false, true));
                }
            }
            //GENERAR SALAS
            listado_salas[i] = new Sala("SALA" + i, 200, mis_butacas, 504.7);
            listado_salas[i] = new Sala("SALA" + i, 400, mis_butacas, 650.9);
        }
        //CREAR LISTA RESERVAS

        //CREAR LISTA DE EVENTOS
        listado_eventos.add(new Evento("Concierto Ñengo", "Ñengo Flow", listado_salas[1], "2024-10-02", "20:00", 30.00, "Reggaeton", 800));
        listado_eventos.add(new Evento("Rauw Alejandro", "Rauw Alejandro", listado_salas[2], "2024-04-28", "19:00", 20.00, "Reggaeton", 100));
        listado_eventos.add(new Evento("Bad Gyal", "Bad Gyal", listado_salas[3], "2024-10-12", "22:00", 25.00, "Reggaeton", 300));
        listado_eventos.add(new Evento("Raphael", "Mi Gran Noche", listado_salas[0], "2024-05-12", "21:00", 60.00, "Pop-Español", 1200));
    }

    public void menuPrincipal() {
        String opcion_login;
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
                    login();
                    break;
                case "2":
                    System.out.println("Has seleccionado Registro.");
                    NuevoAsistente();
                    break;
                case "0":
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");

            }
        } while (!opcion_login.equals("1") && !opcion_login.equals("2") && !opcion_login.equals("0"));
    }

    public void login() {
        Asistente asistente = new Asistente();
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
            } while (password.length() < 4);
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
                } else {
                    System.out.println("Bienvenido " + correo);
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
            System.out.println("2.Salir al login");
            System.out.println();
            System.out.print("Introducir opcion:");
            opcion_continuidad = scanner.nextLine();
            switch (opcion_continuidad) {
                case "1":
                    login();
                case "2":
                    menuPrincipal();
                default:
                    System.out.println("La opcion introducida no es correcta");

            }

        } while (!opcion_continuidad.equals("1") && !opcion_continuidad.equals("2"));


    }

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
                    do {
                        String seleccion_evento;
                        System.out.println("### Selecciona el evento que desee ###");
                        int i = 0;
                        for (Evento e : listado_eventos) {
                            System.out.println(i + " " + e.getNombre());
                            i++;
                        }
                        System.out.print("Introduzca numero de evento: ");
                        seleccion_evento = entrada_2.nextLine();
                        if (Validaciones.validarNumeros(seleccion_evento)) {
                            int parseo_seleccion_evento = Integer.parseInt(seleccion_evento);
                            System.out.println(
                                    "Nombre evento: " + listado_eventos.get(parseo_seleccion_evento).getNombre() + '\n' +
                                            "Fecha del evento: " + listado_eventos.get(parseo_seleccion_evento).getFecha() + '\n' +
                                            "Hora del evento evento: " + listado_eventos.get(parseo_seleccion_evento).getHora() + '\n' +
                                            "Precio del evento: " + listado_eventos.get(parseo_seleccion_evento).getPrecio() + '\n' +
                                            "Nobre de la sala: " + listado_eventos.get(parseo_seleccion_evento).getSala().getNombre()
                            );
                            seleccion_evento = listado_eventos.get(parseo_seleccion_evento).getNombre();
                            evento = devolver_evento(seleccion_evento);
                            menu_reservas(asistente, evento);
                        } else {
                            System.out.println("El numero de evento introducido no correcto, vuleva a introducirlo");
                            iteración_eventos = true;
                        }
                    } while (!iteración_eventos);
                    break;
                case "2":
                    System.out.println("Aquí puedes ver la información de tus reservas.");
                    mostrar_reservas(email);
                    break;
                case "3":
                    System.out.println("¡Hasta luego!");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
            }
        } while (!opcion_menu.equals("3"));
    }

    public Asistente NuevoAsistente() {
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
                    LocalDate fecha_parseada = Validaciones.fechaParseada(fecha_nacimiento);
                    System.out.println();
                    System.out.println();
                    System.out.println("#~#Ahora vamos a crear tu contraseña#~#");
                    System.out.print("Introduce tu Contraseña: ");
                    String password = scaner_menu_nuevo_asistente.nextLine();
                    if (password.length() < 4) {
                        do {
                            System.out.print("Contraseña invalidad, introduzca otra vez la contraseña: ");
                            password = scaner_menu_nuevo_asistente.nextLine();
                        } while (password.length() < 4);
                    }
                    listado_asistentes.add(new Asistente(nombre, apellidos, email, telefono, dni, fecha_parseada, password));
                    menuDeOpciones(asistente);
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
            switch (opcion_menu_reservas) {
                case "1":
                    Scanner entrada_2 = new Scanner(System.in);
                    String reserva;
                    Butaca butaca;
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
                        if (Validaciones.ComprobarAsientos(reserva, evento)) {
                            butaca = evento.getSala().asociar_butaca(reserva);
                            reservar(asistente, evento, butaca);
                        }
                    } while (!Validaciones.ComprobarAsientos(reserva, evento));
                        confirmar_compra(asistente);
                    break;
                case "2":
                    System.out.println("Volver al menu par información de reserva");
                    menuDeOpciones(asistente);
                    break;
                default:
                    System.out.println("¡La opcion introducidad no es correcta!");
                    break;
            }
        } while (!opcion_menu_reservas.equals("2") && !opcion_menu_reservas.equals("1"));


    }

    public void mostrar_reservas(String email) {
        for (Reserva reser : listado_reservas) {
            if (reser.getAsistente().getEmail().equals(email)) {
                reser.reservas_realizadas();
            }
        }
    }

    public Asistente devolver_asistente(String email) {
        Asistente asistente = new Asistente();
        for (Asistente asis : listado_asistentes) {
            if (asistente.getEmail().equals(email)) {
                asistente = asis;
            }
        }
        return asistente;
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

    public void reservar(Asistente asistente, Evento evento, Butaca butaca) {
        for (Asistente asis : listado_asistentes) {
            if (asis.getNombre().equals(asistente.getNombre()) && asis.getApellidos().equals(asistente.getApellidos()) && asis.getEmail().equals(asistente.getEmail()) && asis.getTelefono().equals(asistente.getTelefono()) && asis.getDni().equals(asistente.getDni()) && asis.getFecha_nacimiento().equals(asistente.getFecha_nacimiento()) && asis.getPassword().equals(asistente.getPassword())) {
                listado_reservas.add(new Reserva(asistente, evento, butaca, evento.getFecha(), evento.getHora()));
                evento.getListaAsistentes().add(new Asistente(asistente.getNombre(), asistente.getApellidos(), asistente.getEmail(), asistente.getTelefono(), asistente.getDni(), asistente.getFecha_nacimiento(), asistente.getPassword()));
                System.out.println("Se ha realizado tu reserva " + asistente.getNombre());
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
    public void confirmar_compra( Asistente asistente) {
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
}






