import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

public class Validaciones {
    /**
     * @param numero_telefono Recoge un String y comprueba si está compuesto por números
     * @return devuelve true o false es decir un dato boolean, true si esta compuesto por numeros, si no false
     */
    public static boolean ComprobarTelefono(String numero_telefono) {

        //Remplazos
        numero_telefono = numero_telefono.replace(" ", "");
        numero_telefono = numero_telefono.replace("-", "");
        numero_telefono = numero_telefono.replace(".", "");
        numero_telefono = numero_telefono.toUpperCase();
        //obligacion de introducir 9 carcteres
        if (numero_telefono.length() == 9) {
            for (int i = 0; i < 9; i++) {
                //Telefono solo numeros
                if (numero_telefono.charAt(i) < '0' || numero_telefono.charAt(i) > '9') {
                    System.out.print("Telefono incoreecto, vuelve a introducirlo: ");
                    return false;
                }
            }
        } else {
            System.out.print("Telefono incoreecto, vuelve a introducirlo: ");
            return false;
        }

        return true;

    }
    public static boolean validarNumeros(String texto) {
        /**
         * Verificamos si la cadena se encuentra a null es decir vacia o la cadena esta a null
         */
        if (texto == null || texto.isEmpty()) {
            return false;

        }

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (caracter < '0' || caracter > '9') {
                return false;
            }
        }

        // Si llegamos aquí, todos los caracteres son números
        return true;
    }

    /**
     * @param asiento_elegido Recoge un String y comprueba si está compuesto los caracteres permitidios
     * @param evento se recoge el evento con el que se esta trabajando y aqui se iguala el evento con la butaca mediante la funcion de asociar_butaca
     * @return devuelve true o false, en funcion de validaciones es decir si esta disponible o no y si trabaja con los carcateres permitidos.
     */

    public static boolean ComprobarAsientos(String asiento_elegido, Evento evento) {
        Butaca butaca;
        boolean comprobarasiento=false;
        if (asiento_elegido.length() == 2) {
            asiento_elegido = asiento_elegido.toUpperCase();
            char letra = asiento_elegido.charAt(0);
            char numero = asiento_elegido.charAt(1);
            //Condicion de asientos reservados y rangos de letras y numeros
            if ( ( letra >= 'A' && letra<= 'F') && (numero>='1' && numero<='6')) {
                butaca = evento.getSala().asociar_butaca(asiento_elegido);
                if (butaca.getDisponible()){
                    comprobarasiento=true;
                }else{
                    System.out.println("El asiento no esta dispobible");
                }
            }

        }else {
            System.out.println("Asiento introducido no es correcto, vuelva a introducirlo: ");
        }
       return comprobarasiento;
    }

    /**
     * @param nombre Recoge un String y comprueba si solo son letras
     * @return devuelve true si solo son letras y false si se encuentra algun numero, o no son letras
     */
    public static boolean ComprobarNombreApellidos(String nombre) {
        // Una condicion minima para que se tengan que introducir caracteres oal menos 5 obligatoriamente
        nombre.replace(" ","");
        if (nombre.length() >= 5){
            for (int i = 0; i < nombre.length(); i++) {
                char carac_nombre = nombre.charAt(i);
                if ((carac_nombre >= 'a' && carac_nombre <= 'z') || (carac_nombre >= 'A' && carac_nombre <= 'Z')  || carac_nombre == ' ') {
                } else {
                    System.out.print("Contiene caracteres no validos, vuelve a introducirlo: ");
                    i = carac_nombre - 1;
                    return false;
                }

            }
        }else {
            System.out.print("Contiene caracteres no validos, vuelve a introducirlo: ");
            return false;
        }
        return true;
    }

    /**
     * @param dni Recoge un String y comprueba que tiene letras y numero y si la letra pertenece al dni
     * @return devuelve true o false es decir un dato boolean, true si esta compuesto por numeros y por una letra al final, si no false
     */
    public static boolean ComprobarDNI(String dni) {
        dni=dni.replace(" ","");
        if (dni.length() == 9) {
            //REMPLAZAMIENTOS DE CARACTERES
            dni = dni.replace(" ", "");
            dni = dni.replace("-", "");
            dni = dni.replace(".", "");
            dni = dni.toUpperCase();
            char dni_letra = dni.charAt(8);
            String letra = "TRWAGMYFPDXBNJZSQVHLCKE";
            //Bucle para comprobar que el dni no tiene caracteres fuera de lo comun como numeros negativos o letras fuera del rango de la a, y la z
            for (int i = 0; i <= dni.length() - 1; i++) {
                if (i <= 7) {
                    if (((dni.charAt(i) > '9' && dni.charAt(i) < '0') || (dni.charAt(i) >= 'A' && dni.charAt(i) <= 'Z'))) {
                        System.out.print("DNI invalido, vuelva a introducirlo: ");
                        return false;
                    }
                } else {
                    int resto = (Integer.parseInt(dni.substring(0, 8))) % 23;// division del dni entre 23.
                    if (dni_letra != letra.charAt(resto)) {
                        System.out.print("DNI invalido, vuelva a introducirlo:  ");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else {
            System.out.print("DNI invalido, vuelva a introducirlo: ");
            return false;
        }
        return true;
    }

    /**
     * @param correo Recoge un String y comprueba si está compuesto por números
     * @return devuelve true o false es decir un dato boolean, true si el correo tiene delnate del @ caracteres y que tiene que ir un arroba y despues un ., si no false
     */
    public static boolean ComprobarCorreo(String correo) {
        correo = correo.toUpperCase();
        correo = correo.replace(" ","");
        //condicion para que minimo tenga que tener 1 caracter, y que el primiero no sea ni @ ni .
        if (correo.length() != 0) {
            if (correo.charAt(0) == '@' || correo.charAt(0) == '.') {
                System.out.print("Vuelve a introducir el correo electronico: ");
                return false;
            }
        }
        //Bucle for para comprobar que no hay caracteres fuera de lo comun dentro del correo
        for (int i = 0; i < correo.length(); i++) {
            if ((correo.charAt(i) < 'A' ||  correo.charAt(i) > 'Z') && (correo.charAt(i) != '@' && correo.charAt(i) != '.') && (correo.charAt(i) <= '0' || correo.charAt(i) >= '9')) {
                System.out.print("Vuelve a introducir el correo electronico: ");
                return false;
            }
        }

        // Verificar si el correo tiene una @
        if (correo.indexOf("@") == -1) {
            System.out.print("Vuelve a introducir el correo electronico: ");//El correo no tiene @
            return false;
        } else {
            // Verificar si el correo tiene más de una @
            int indice = correo.indexOf("@");
            int indice_punto= correo.indexOf(".");
            if (correo.indexOf("@", indice + 1) != -1) {
                System.out.print("Vuelve a introducir el correo electronico: ");//Contiene @ de mas
                return false;
            } else {
                // Verificar si el correo tiene un punto después de la @
                if (correo.indexOf(".", indice) == -1) {
                    System.out.print("Vuelve a introducir el correo electronico: ");//Falta un .
                    return false;
                }else {
                    //correo.substring((correo.indexOf(".", indice))
                }

            }
        }

        return true;
    }

    /**
     * @param iban Recoge un String y comprueba si está compuesto por números
     * @return devuelve true o false es decir un dato boolean, true si esta compuesto por numeros y  dos letras al principio,
     * despues realiza una división del biginteger entre 97 y si el resto es diferente de 1 falso si no true, si no false,
     *
     */
    public static boolean ComprobarIban(String iban) {
        //Comprobar que Carcteres estan dentro del rango y contiene letras menores que A Y mayores de Z
        if (iban.length() != 24) {
            System.out.print("El IBAN no es valido, vuelve a introducirlo: ");//Contiene mas carcteres de lo normal o menos carcteres
            return false;
        } else {

            //Comprobamos que las dos primeras posiciones son letras
            for (int j = 0; j < 2; j++) {
                if (iban.charAt(j) < 'A' || iban.charAt(j) > 'Z') {
                    return false;
                }
            }
            for (int i = 2; i < iban.length(); i++) {
                if (iban.charAt(i) < '0' || iban.charAt(i) > '9') {
                    return false;
                }
            }
        }

        //Cambiamos de posicion las dos primeras letras al final
        iban = iban.substring(4) + iban.substring(0, 4);

        //Recorre cadena, y ccuando se encuentre una letra la sustitutye por el nunmeor al que pertenece
        for (int i = 0; i < iban.length(); i++) {
            if (iban.charAt(i) >= 'A' && iban.charAt(i) <= 'Z') {
                String cambio_de_letras = String.valueOf(iban.charAt(i) - 55);//resta de numero con la tabla ascci
                //Suma de letras con los demas numeros
                iban = iban.substring(0, i) + cambio_de_letras + iban.substring(i + 1, iban.length());

            }

        }
        //Pasamos de String a BigInteger
        BigInteger num_iban = new BigInteger(iban);
        BigInteger resto = num_iban.mod(BigInteger.valueOf(97));
        String resultado = resto.toString();

        //Condiciconal, si resto de la division es diferente de 1 es falso, si no es verdadero.
        if (!resultado.equals("1")) {
            System.out.println("El IBAN no es valido, vuelve a introducirlo: ");
            return false;
        } else {
            System.out.println("IBAN validado correctamente");
            return true;
        }
    }

    /**
     *@return imprime un nnumeor dentro aleatorio entre los carcteres, numeros y letras.Genera un tocken.
     */
    public static String RealizaTocken() {
        char num;
        char carcter;
        char letra;
        String token = "";
        int numeros_maximos = 7;
        int caracteres_maximos = 3;
        int letras_maximas = 7;
        String numeros_permitidos = "0123456789";
        String caracteres_permitidos = "!@#$%^&*";
        String letras_permitidas = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //Bucle for para sacar las letras al azar
        for (int i = 0; i < letras_maximas; i++) {
            letra = letras_permitidas.charAt((int) (Math.random() * letras_permitidas.length()));
            token += letra;
            letras_permitidas = letras_permitidas.replace(String.valueOf(letra), "");
        }
        //Bucle for para sacar las numeros al azar
        for (int i = 0; i < numeros_maximos; i++) {
            num = numeros_permitidos.charAt((int) (Math.random() * numeros_permitidos.length()));
            token += num;
            numeros_permitidos = numeros_permitidos.replace(String.valueOf(num), "");
        }
        //Bucle for para sacar las caracteres al azar
        for (int i = 0; i < caracteres_maximos; i++) {
            carcter = caracteres_permitidos.charAt((int) (Math.random() * caracteres_permitidos.length()));
            token += carcter;
            caracteres_permitidos = caracteres_permitidos.replace(String.valueOf(carcter), "");
        }

        return token;
    }
    /**
     * @param fecha_nacimiento se comprueban diversos carcteres obligatorios para la fecha
     * @return devuelve true en caso de que la fecha sea correcta y false en caso contrario, asi tambien devolviendo una nueva introducción de fecha
     */
     public static boolean ValidarFecha(String fecha_nacimiento){

         if (fecha_nacimiento.length() != 10) {
             System.out.print("Fecha no válida. Introduce la fecha en el formato indicado (DD-MM-YYYY): ");
             return false;
         } else {
             String fechanum = fecha_nacimiento.replace("-", "");
             if (!Validaciones.validarNumeros(fechanum)) {
                 System.out.print("Fecha no válida. Introduce la fecha en el formato indicado (DD-MM-YYYY):");
                 return false;
             } else {
                 String[] dia_mes_anyo = fecha_nacimiento.split("-");

                 //Pasar día, mes y año a valor numérico
                 int dia = Integer.parseInt(dia_mes_anyo[0]);
                 int mes = Integer.parseInt(dia_mes_anyo[1]);
                 int anyo = Integer.parseInt(dia_mes_anyo[2]);

                 //Comprobar año
                 if (anyo > 1920 && anyo < Integer.parseInt(String.valueOf(Year.now()))){

                     //Comprobar mes
                     if (mes >= 1 && mes <= 12) {

                         //Comprobar que el día corresponde con el mes
                         if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                             if (dia >= 1 && dia <=31) {
                                 return true;
                             } else {
                                 System.out.print("Introduce de nuevo la fecha en el formato indicado (DD-MM-YYYY):");
                                 return false;
                             }
                         }

                         if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                             if (dia >= 1 && dia <=30) {
                                 return true;
                             } else {
                                 System.out.print("Introduce de nuevo la fecha en el formato indicado (DD-MM-YYYY):");
                                 return false;
                             }
                         }

                         if (mes == 2) {
                             /**
                              * Se compruueba si el año es bisiesto pero antes coprueba que el mes sea dos es decir febrero,
                              * y una vez easo comprueba si es bisiesto. Gracias al año entre 400 pero que el resto de 0.
                             * */
                             if (anyo%400 == 0) {
                                 if (dia >= 1 && dia <=29) {
                                     return true;
                                 } else {
                                     System.out.print("Introduce de nuevo la fecha en el formato indicado (DD-MM-YYYY):");
                                     return false;
                                 }
                             } else {
                                 /**
                                  * Ahora lo que hace es comprobar que el año no sea bisiesto, ya que uno son 28 dias en caso de no bisiest y y 29 en
                                  * caso de bisiesto
                                  * */
                                 if (dia >= 1 && dia <=28) {
                                     return true;
                                 } else {
                                     System.out.print("Día no válido. Introduce de nuevo la fecha en el formato indicado (DD-MM-YYYY):");
                                     return false;
                                 }
                             }
                         }

                     } else {
                         System.out.print("El mes debe estar comprendido entre 1 y 12. Introduce de nuevo la fecha en el formato indicado (DD-MM-YYYY):");
                         return false;
                     }
                 } else {
                     System.out.print("El año debe estar comprendido entre 1900 y " + Year.now() + ". Introduce de nuevo la fecha en el formato indicado (DD-MM-YYYY):");
                     return false;
                 }
             }
         }
         return true;
}

    /**
     * @param fecha se parsea la fecha al formato de validacion es decir para que el usuario pueda introducirlo de otra manera en vez de año mes dia,
     *              introducimos de manera mas comoda dia mes y año
     * @return devuelve la fecha parseada, para trabjar con ella.
     */
    static LocalDate fechaParseada(String fecha) {
        String[] dia_mes_anyo = fecha.split("-");
        int dia = Integer.parseInt(dia_mes_anyo[0]);
        int mes = Integer.parseInt(dia_mes_anyo[1]);
        int anyo = Integer.parseInt(dia_mes_anyo[2]);

        LocalDate fecha_parseada = LocalDate.of(anyo,mes,dia);
        return fecha_parseada;
    }
}