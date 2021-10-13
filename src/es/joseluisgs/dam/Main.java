package es.joseluisgs.dam;

import java.util.Scanner;

/**
 * Esta es la clase Main y es la principal de nuestro programa. Es el punto de entrada
 * ¿Por qué? Porque es la única que tiene un método estático que se llama main y es público.
 * Ese es el criterio que sigue JAVA aunque lo podemos cambiar. Pero ya lo iremos viendo.
 * JAVA es un lenguaje orientado a objetos, y los objetos se definen por Clases, por lo que tiene que haber objetos
 * y tu programa está compuesto de uno o muchos de ellos.
 * Por lo tanto Main es una clase que creará el primer objeto al lanzarse nuestro programa.
 * Pero como no somos nosotros los que creamos este objeto debe ser creado por la máquina virtual.
 * Esto y mucho más lo veremos con calma, pero al ponerle main y específicamente static, podemos usar métodos
 * sin crear un objeto al ser métodos de clase (lo veremos 🧐)
 * En definitiva, la máquina virtual busca un método estático llamado main y que sea público (visible desde fuera)
 * para iniciar el programa
 * Por eso mientras que no usemos objetos, cualquier método lleva static para poder usarlo desde la Clase (métodos de clase).
 * Repito, lo veremos muchas veces y con calma.
 */
public class Main {

    /**
     * Método principal de ejecución, debería ser lo más "simple" posible.
     *
     * @param args
     */
    public static void main(String[] args) {

        // Creamos un procedimiento, con un parámetro
        presentacion("1DAM");

        // Creamos el menú
        menuOpciones();

    }

    /// --- Mis procedimientos y funciones en mi mismo fichero

    private static void menuOpciones() {
        // Hacemos un do para mostrar las opciones hasta que pulse la opción correcta
        int opcion;
        // Scanner nos sirve para analizar la entrada desde el teclado. Al usar new si estamos creando el objeto (lo veremos)
        Scanner in = new Scanner(System.in);

        System.out.println("Seleccione la opción");
        System.out.println("1.- Cálculo de estadístcas de clase");
        System.out.println("2.- Cálculo año bisiesto");
        System.out.println("3.- Cálculo de Factorial");
        System.out.println("4.- Primos Gemelos");
        System.out.println("0.- Salir");

        // Creamos un bucle do while y lo tenemos girando aquí hasta que meta estos valores
        // Pero sabemos que scanner va a "petar" si no le metemos algo que pueda hacer el casting
        // Ya lo solucionaremos
        do {
            System.out.println("Elija opción: ");
            opcion = in.nextInt();
        } while (opcion != 0 && opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4);

        switch (opcion) {
            case 1:
                estadisticaClase();
                break;
            case 2:
                añoBisiesto();
                break;
            case 3:
                factorial();
                break;
            case 4:
                primosGemelos();
                break;
            case 0:
                despedida();
                break;
            // No hay default, porque por el do-while no puede llegar aquí con otro valor
        }

        // Y si queremos volver al menú cada vez que terminemos una opción?

    }

    /**
     * Calcula los primos gemelos hasta un número dado
     */
    private static void primosGemelos() {
        int numero;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Introduzca el número máximo para obtener los primos gemelos: ");
            numero = in.nextInt();
        } while (numero <= 0);

        // Solo nos interesan los impares desde el 3
        for (int i = 3; i < numero; i += 2) {
            if (esPrimo(i) && esPrimo(i + 2))
                System.out.print("(" + i + ", " + (i + 2) + ") ");
        }
    }

    /**
     * Calcula si un número es primo o no
     *
     * @param num
     * @return true si num es primo, false si no lo es
     */
    private static boolean esPrimo(int num) {
        boolean isPrimo = num % 2 != 0;

        for (int i = 3; i < Math.sqrt(num) && isPrimo; i += 2) {
            if (num % i == 0)
                isPrimo = false;
        }

        return isPrimo;
    }

    /**
     * Calcula el factorial de un número
     */
    private static void factorial() {
        int numero;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Introduzca el número: ");
            numero = in.nextInt();
        } while (numero <= 0);

        int resultado = factorialRecursivo(numero);
        System.out.println(numero + "! = " + resultado);
    }

    /**
     * Calcula el facroial de manera recursiva
     *
     * @param num a calcular
     * @return factorial del número
     */
    private static int factorialRecursivo(int num) {
        if (num == 0)
            return 1;
        else
            return num * factorialRecursivo(num - 1);
    }

    /**
     * Calcula si un año es bisiesto o no
     */
    private static void añoBisiesto() {
        int año;
        boolean isBisiesto = false;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Introduzca el año: ");
            año = in.nextInt();
        } while (año <= 0);

        if (año % 4 == 0 && año % 100 != 0 || año % 400 == 0) {
            System.out.println("El año: " + año + " SÍ es bisiesto");
        } else {
            System.out.println("El año: " + año + " NO es bisiesto");
        }
    }


    /**
     * Calcula la media de una clase
     */
    private static void estadisticaClase() {
        int numeroAlumnos;
        int aprobados = 0, suspensos = 0; // definición e instanciación de varias a la vez
        int suficentes = 0, bienes = 0, notables = 0, sobresalientes = 0;
        float totalNotas = 0;
        float media;
        Scanner in = new Scanner(System.in);

        // Salismos del bucle sólo si nos introduce un número positivo
        do {
            System.out.println("Introduzca el número de alumnos/as: ");
            numeroAlumnos = in.nextInt();
        } while (numeroAlumnos <= 0);

        // Como sabemos el número de alumnos es un bucle definido
        for (int i = 1; i <= numeroAlumnos; i++) {
            float nota = 0;

            // nota solo existe dentro de este for, por eso la definimos aquí.
            do {
                System.out.println("Nota del alumno " + i + " [0-10]: ");
                nota = in.nextFloat();
            } while (nota < 0 || nota > 10);

            // Sumamos el total de notas
            totalNotas += nota;

            if (nota < 5) {
                suspensos++;
            } else if (nota >= 5 && nota < 6) {
                aprobados++;
                suficentes++;
            } else if (nota >= 6 && nota < 7) {
                aprobados++;
                bienes++;
            } else if (nota >= 7 && nota < 9) {
                aprobados++;
                notables++;
            } else if (nota >= 9) {
                aprobados++;
                sobresalientes++;
            }
        }
        // De esta manera redondeo a dos decimales. Tengo que hacer un casting porque de Double quiero float
        // Si no debería trabajar con double siempre.
        media = (float) (Math.round((totalNotas / numeroAlumnos) * 100.0) / 100.0);

        // Sacamos las estadísticas
        System.out.println("Número de alumnos/as: " + numeroAlumnos);
        System.out.println("Número de aprobados/as: " + aprobados);
        System.out.println("Número de suspensos: " + suspensos);
        System.out.println("Nota media: " + media);
        System.out.println("Nº Suficientes: " + suficentes);
        System.out.println("Nº Bienes: " + bienes);
        System.out.println("Nº Notables: " + notables);
        System.out.println("Nº Sobresalientes: " + sobresalientes);


    }

    /**
     * Mensaje de Despedida
     */
    private static void despedida() {
        System.out.println("¡Adiós \uD83D\uDC4B");
    }

    /**
     * Mensaje de presentación
     *
     * @param curso Curso destinado a programar
     */
    private static void presentacion(String curso) {
        // System.out.println() --> Nos sirve para imprimir por pantalla
        System.out.println("Hola JAVA en 1º DAM");
    }
}
