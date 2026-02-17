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