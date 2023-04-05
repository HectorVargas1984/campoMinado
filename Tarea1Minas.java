
/**
 * Repaso
 * 
 * Tarea 1 - Construir Juego “Campo Minado”
 * Este juego consiste en elegir coordenadas en un tablero de 5*5
 * Dentro del campo de juego existen 10 minas
 * Si eres capaz de ingresar 6 coordenadas (Ej: 0,0) sin activar una
 * mina, Ganaste!
 * NOTA: Para que el juego no sea predecible las minas deben ser
 * creadas aleatoriamente
 * BONUS: Agregar dificultad al juego (el jugador debe seleccionar
 * entre: Facil, Medio y Dificil)
 * (La dificultad aumenta la cantidad de minas en el juego)
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class Tarea1Minas {

    public static void main(String[] args) {

        /*
         * Declaracion de variales
         */
        Scanner scanner = new Scanner(System.in);

        int dificultad = scanner.nextInt();

        int numMinas = 0;

        int intentos = 0;

        /*
         * inicio de juego
         */
        System.out.println("¡Bienvenido al juego de Campo Minado!");

        System.out.print("Ingrese la dificultad (1 = Fácil, 2 = Medio, 3 = Difícil): -> ");

        switch (dificultad) {
            case 1:
                numMinas = 5;
                break;
            case 2:
                numMinas = 10;
                break;
            case 3:
                numMinas = 15;
                break;
            default:
                System.out.println("Dificultad no válida. Seleccionando nivel fácil por defecto...");
                numMinas = 5;
                break;
        }

        /*
         * construccion de tableros
         */
        Integer[][] tablero = crearTablero(numMinas);
        String[][] tableroMuestra = crearTableroM();

        /*
         * creacion de bucle while que controla el juego
         */
        while (intentos < 6) {

            /*
             * inputs de ingreso de coordenadas
             */
            System.out.print("Ingrese coordenadas fila: -> ");
            int fila = scanner.nextInt();
            System.out.print("Ingrese coordenada de columna: -> ");
            int columna = scanner.nextInt();

            System.out.println("las coordenadas son : " + "( fila: " + fila + ", columna: " + columna + ")");

            /*
             * logica para no ingresas coordenadas fuera del parametro
             */
            if (fila < 0 || fila > 4 || columna < 0 || columna > 4) {
                System.out.println("Coordenadas no válidas. Intente de nuevo.");
                continue;
            }

            /*
             * logica de entrar minas o lo contrario
             */
            if (tablero[fila][columna] == 1) {
                System.out.println("¡Mina encontrada! Pufff! GAME OVER.");
                tableroMuestra[fila][columna] = "B";

                /*
                 * al perder se nuestra tablero donde se encontraba la mina
                 */
                mostrarTablero(tableroMuestra);

                break;
            } else {
                intentos++;
                var i = 6 - intentos;
                System.out.println("Coordenadas ingresada válidas. aun no pisas una mina 'genial!!!' quedan: " + i
                        + " Intentos para sobrevivir");

            }
        }

        /*
         * al ganar se muestra tableno donde se encontraban todas la minas
         */
        if (intentos == 6) {
            mostrarTablero(tablero);
            System.out.println("");
            System.out.println("¡Felicidades! Has ganado el juego.");

        }

        scanner.close();
    }

    /**
     * Crea tablero pára el juego de minas
     * 
     * @param numMinas solicta el parametro de las minas que se dan segun dificultad
     * @return un tablero creado
     */
    public static Integer[][] crearTablero(int numMinas) {

        Random rand = new Random();

        Integer[][] tablero = new Integer[5][5];

        int minasGeneradas = 0;

        while (minasGeneradas < numMinas) {

            int fila = rand.nextInt(5);
            int columna = rand.nextInt(5);

            if (tablero[fila][columna] == null) {
                tablero[fila][columna] = 1;
                minasGeneradas++;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tablero[i][j] == null) {
                    tablero[i][j] = 0;
                }
            }

        }

        return tablero;
    }

    /**
     * Crea table para mostrar en caso de peder
     * 
     * @return retorna un table de tipo String de mostrara la posicion de la bomba
     */
    public static String[][] crearTableroM() {

        String[][] tablero = new String[5][5];

        for (int i = 0; i < 5; i++) {
            System.out.println("");
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = "*";
            }
        }
        return tablero;
    }

    /**
     * Montrador de tableros generico
     * 
     * @param <T>           clase generica
     * @param tableroMustra recibe ua tablero de tipo generico para mostrar
     */
    public static <T> void mostrarTablero(T[][] tableroMustra) {

        for (int i = 0; i < 5; i++) {
            System.out.println("");
            for (int j = 0; j < 5; j++) {
                System.out.print("[" + tableroMustra[i][j] + "]");
            }
        }

    }
}
