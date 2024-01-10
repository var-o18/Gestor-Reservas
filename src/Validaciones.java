import java.math.BigInteger;

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
                    System.out.println("El telefono introducido es incorrecto");
                    return false;
                }
            }
        } else {
            System.out.println("El telefono introducido es incorrecto");
            return false;
        }

        return true;
    }

    /**
     * @param asiento_elegido Recoge un String y comprueba si está compuesto los caracteres permitidios y si los asientos estan reservados
     * @return devuelve true o false, true cuando se introduce el asiento correcto, bien no este reservado, o este dentro del rango y false el resto.
     */
    public static boolean ComprobarAsientos(String asiento_elegido) {
        if (asiento_elegido.length() == 2) {
            asiento_elegido = asiento_elegido.toUpperCase();
            char letra = asiento_elegido.charAt(0);
            char numero = asiento_elegido.charAt(1);
            //Condicion de asientos reservados y rangos de letras y numeros
            if (asiento_elegido.equals("A1") || asiento_elegido.equals("B5") || asiento_elegido.equals("C6")) {
                System.out.println("Los asientos elegidos entan reservados, vuelva a elegir");
            } else if (letra < 'A' || letra > 'C') {
                System.out.println("El asiento elegido no es valido");
            } else if (numero < '1' || numero > '6') {
                System.out.println("El asiento elegido no es valido");
            } else {
                return true;
            }

        }
        return false;
    }

    /**
     * @param nombre Recoge un String y comprueba si solo son letras
     * @return devuelve true si solo son letras y false si se encuentra algun numero, o no son letras
     */
    public static boolean ComprobarNombre(String nombre) {
        // Una condicion minima para que se tengan que introducir caracteres oal menos 5 obligatoriamente
        if (nombre.length() >= 5){
            for (int i = 0; i < nombre.length(); i++) {
                char carac_nombre = nombre.charAt(i);
                if ((carac_nombre >= 'a' && carac_nombre <= 'z') || (carac_nombre >= 'A' && carac_nombre <= 'Z')  || carac_nombre == ' ') {
                } else {
                    System.out.println("El nombre introducido es erroneo, vuelva a introducirlo");
                    i = carac_nombre - 1;
                    return false;
                }

            }
        }else {
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
                        System.out.println("DNI INVALIDO, REPITELO");
                        return false;
                    }
                } else {
                    int resto = (Integer.parseInt(dni.substring(0, 8))) % 23;// division del dni entre 23.
                    if (dni_letra != letra.charAt(resto)) {
                        System.out.println("Tu letra no coincide con este DNI");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else {
            System.out.println("TU DNI ES DIFERENTE...");
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
        //condicion para que minimo tenga que tener 1 caracter, y que el primiero no sea ni @ ni .
        if (correo.length() != 0) {
            if (correo.charAt(0) == '@' || correo.charAt(0) == '.') {
                System.out.println("Correo invalido");
                return false;
            }
        }
        //Bucle for para comprobar que no hay caracteres fuera de lo comun dentro del correo
        for (int i = 0; i < correo.length(); i++) {
            if ((correo.charAt(i) < 'A' ||  correo.charAt(i) > 'Z') && (correo.charAt(i) != '@' && correo.charAt(i) != '.') && (correo.charAt(i) <= '0' || correo.charAt(i) >= '9')) {
                return false;
            }
        }

        // Verificar si el correo tiene una @
        if (correo.indexOf("@") == -1) {
            System.out.println("Vuelve a introducir el correo electronico");//El correo no tiene @
            return false;
        } else {
            // Verificar si el correo tiene más de una @
            int indice = correo.indexOf("@");
            int indice_punto= correo.indexOf(".");
            if (correo.indexOf("@", indice + 1) != -1) {
                System.out.println("Vuelve a introducir el correo electronico");//Contiene @ de mas
                return false;
            } else {
                // Verificar si el correo tiene un punto después de la @
                if (correo.indexOf(".", indice) == -1) {
                    System.out.println("Vuelve a introducir el correo electronico");//Falta un .
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
            System.out.println("Tu IBAN es incorrecto");//Contiene mas carcteres de lo normal o menos carcteres
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
            System.out.println("El IBAN no es valido");
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
}