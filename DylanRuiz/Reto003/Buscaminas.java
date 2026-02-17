import java.util.Random;
import java.util.Scanner;

public class Buscaminas {

    static final int FILAS = 6;
    static final int COLUMNAS = 8;
    static final int MINAS_TOTALES = 5;
    static final int MINAS_PARA_PERDER = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[][] tablero = new String[FILAS][COLUMNAS];
        String[][] tableroVisible = new String[FILAS][COLUMNAS];

        int minasEncontradas = 0;
        int celdasDescubiertas = 0;
        boolean salida = false;

        inicializarTableros(tablero, tableroVisible);
        colocarMinas(tablero, random);

        System.out.println("===== BUSCAMINAS =====");

        while (!salida) {

            imprimirTablero(tableroVisible);

            System.out.print("Introduce fila (0-" + (FILAS - 1) + "): ");
            int fila = sc.nextInt();

            System.out.print("Introduce columna (0-" + (COLUMNAS - 1) + "): ");
            int columna = sc.nextInt();

            // Validar rango
            if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
                System.out.println("Posición inválida.\n");
                continue;
            }

            // Evitar repetir jugadas
            if (!tableroVisible[fila][columna].equals("  ")) {
                System.out.println("Esa celda ya fue seleccionada.\n");
                continue;
            }

            if (tablero[fila][columna].equals("MM")) {
                tableroVisible[fila][columna] = "MM";
                minasEncontradas++;
                System.out.println("Has encontrado una mina!\n");
            } else {
                tableroVisible[fila][columna] = "--";
                celdasDescubiertas++;
            }

            // Condiciones de fin
            if (minasEncontradas >= MINAS_PARA_PERDER) {
                System.out.println("Lo siento, has perdido.");
                mostrarTableroCompleto(tablero);
                salida = true;
            }

            if (celdasDescubiertas == (FILAS * COLUMNAS - MINAS_TOTALES)) {
                System.out.println("¡Enhorabuena, has ganado!");
                salida = true;
            }
        }

        sc.close();
    }

    public static void inicializarTableros(String[][] tablero, String[][] tableroVisible) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = "  ";
                tableroVisible[i][j] = "  ";
            }
        }
    }

    public static void colocarMinas(String[][] tablero, Random random) {
        int minasColocadas = 0;

        while (minasColocadas < MINAS_TOTALES) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);

            if (!tablero[fila][columna].equals("MM")) {
                tablero[fila][columna] = "MM";
                minasColocadas++;
            }
        }
    }
