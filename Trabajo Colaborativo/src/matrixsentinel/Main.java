package matrixsentinel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boardSize = 10;

        while (true) {
            System.out.println(" |  MATRIX SENTINEL  | ");
            System.out.println("1. Iniciar juego");
            System.out.println("2. Configurar tablero");
            System.out.println("3. Instrucciones");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();

            if (option == 1) {
                Game game = new Game(boardSize);
                game.start();
            } else if (option == 2) {
                int size;
                do {
                    System.out.print("Ingrese el tamaño del tablero (10 a 50): ");
                    size = scanner.nextInt();
                } while (size < 10 || size > 50);
                boardSize = size;
            } else if (option == 3) {
                System.out.println("- | Encuentra al enemigo usando pistas matemáticas.");
                System.out.println("- | Las columnas pares revelan distancia vertical.");
                System.out.println("- | Las columnas impares revelan distancia horizontal.");
                System.out.println("- | Resuelve la pista antes de explorar.");
            } else if (option == 4) {
                break;
            }
        }
    }
}
