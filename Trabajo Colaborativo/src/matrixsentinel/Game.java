package matrixsentinel;

import java.util.Scanner;

public class Game {

    private Board board;
    private SensorMatrix sensorMatrix;
    private boolean gameOver;
    private Scanner scanner;
    private int rows;
    private int cols;

    public Game() {
        scanner = new Scanner(System.in);
        rows = 5;
        cols = 5;
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== MATRIX SENTINEL ===");
            System.out.println("1. Iniciar juego");
            System.out.println("2. Configurar tablero");
            System.out.println("3. Instrucciones");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opcion: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    startGameLoop();
                    break;
                case "2":
                    configureBoard();
                    break;
                case "3":
                    showInstructions();
                    break;
                case "4":
                    scanner.close();
                    return;
            }
        }
    }

    private void startGameLoop() {
        reset();
        play();

        System.out.print("Deseas volver a jugar? (s/n): ");
        String option = scanner.nextLine().toLowerCase();

        if (option.equals("s")) {
            startGameLoop();
        }
    }

    private void reset() {
        board = new Board(rows, cols);
        sensorMatrix = new SensorMatrix();
        gameOver = false;
    }

    private void play() {
        while (!gameOver) {
            board.print();

            int row = readValidRow();
            int col = readValidColumn();

            Enemy enemy = board.getEnemy(row, col);

            if (enemy != null) {
                if (sensorMatrix.detect(enemy.getVector())) {
                    board.reveal(row, col, board.countNearbyThreats(row, col));
                } else {
                    board.revealLoss(row, col);
                    board.print();
                    System.out.println("Has perdido");
                    gameOver = true;
                }
            } else {
                board.reveal(row, col, board.countNearbyThreats(row, col));
            }
        }
    }

    private int readValidRow() {
        while (true) {
            System.out.print("Ingresa la fila: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() != 1) {
                continue;
            }

            int row = input.charAt(0) - 'A';

            if (row >= 0 && row < rows) {
                return row;
            }
        }
    }

    private int readValidColumn() {
        while (true) {
            System.out.print("Ingresa la columna: ");
            String input = scanner.nextLine();

            try {
                int col = Integer.parseInt(input) - 1;
                if (col >= 0 && col < cols) {
                    return col;
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    private void configureBoard() {
        rows = readBoardSize("Numero de filas");
        cols = readBoardSize("Numero de columnas");
    }

    private int readBoardSize(String label) {
        while (true) {
            System.out.print(label + " (3 - 25): ");
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= 3 && value <= 25) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    private void showInstructions() {
        System.out.println("INSTRUCCIONES");
        System.out.println("El tablero contiene amenazas ocultas");
        System.out.println("Cada amenaza es un vector");
        System.out.println("Los sensores forman una matriz");
        System.out.println("Si el vector pertenece al espacio columna, la amenaza es detectable");
        System.out.println("Si activas una amenaza no detectable, pierdes");
        System.out.println("Los numeros indican amenazas detectables cercanas");
        System.out.println("Presiona ENTER para volver al menu");
        scanner.nextLine();
    }
}
