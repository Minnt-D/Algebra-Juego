package matrixsentinel;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private Board board;
    private int size;
    private int enemyRow;
    private int enemyCol;
    private int attempts;

    public Game(int size) {
        this.size = size;
        this.board = new Board(size);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el modo:");
        System.out.println("1. Búsqueda");
        System.out.println("2. Persecución");
        int mode = scanner.nextInt();

        System.out.println("Seleccione la dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Normal");
        System.out.println("3. Difícil");
        int difficulty = scanner.nextInt();

        configureAttempts(mode, difficulty);
        generateEnemy();
        showMathHint(difficulty);

        while (attempts > 0) {
            board.print();

            int row;
            int col;

            do {
                System.out.print("Ingrese la fila: ");
                row = scanner.nextInt();
            } while (row < 1 || row > size);

            do {
                System.out.print("Ingrese la columna: ");
                col = scanner.nextInt();
            } while (col < 1 || col > size);

            int r = row - 1;
            int c = col - 1;

            if (r == enemyRow && c == enemyCol) {
                board.markEnemy(enemyRow, enemyCol, false);
                board.print();
                System.out.println("HAS ENCONTRADO AL ENEMIGO");
                endMenu(scanner);
                return;
            }

            int distance;
            if (col % 2 == 0) {
                distance = Math.abs(enemyRow - r);
            } else {
                distance = Math.abs(enemyCol - c);
            }

            board.markDistance(r, c, distance);
            attempts--;

            if (mode == 2) {
                moveEnemy();
            }

            System.out.println("Oportunidades restantes: " + attempts);
        }

        board.markEnemy(enemyRow, enemyCol, true);
        board.print();

        System.out.println("SE HAN AGOTADO LAS OPORTUNIDADES");
        System.out.println();
        System.out.println("SOLUCIÓN DE LA PISTA:");
        System.out.println("Fila: " + (enemyRow + 1));
        System.out.println("Columna: " + (enemyCol + 1));

        endMenu(scanner);
    }

    private void configureAttempts(int mode, int difficulty) {
        if (mode == 1) {
            attempts = difficulty == 1 ? 7 : difficulty == 2 ? 5 : 3;
        } else {
            attempts = difficulty == 1 ? 15 : difficulty == 2 ? 12 : 8;
        }
    }

    private void generateEnemy() {
        Random random = new Random();
        enemyRow = random.nextInt(size);
        enemyCol = random.nextInt(size);
    }

    private void moveEnemy() {
        Random random = new Random();
        int dir = random.nextInt(4);

        if (dir == 0 && enemyRow > 0) enemyRow--;
        if (dir == 1 && enemyRow < size - 1) enemyRow++;
        if (dir == 2 && enemyCol > 0) enemyCol--;
        if (dir == 3 && enemyCol < size - 1) enemyCol++;
    }

    private void showMathHint(int difficulty) {
        int i = enemyRow + 1;
        int j = enemyCol + 1;

        System.out.println("|  PISTA MATEMÁTICA  |");
        System.out.println();

        if (difficulty == 1) {
            System.out.println("│ i + 2 = " + (i + 2));
            System.out.println("│ j - 1 = " + (j - 1));
        }

        if (difficulty == 2) {
            System.out.println("│ |i - " + i + "| = 1");
            System.out.println("│ (j ÷ 2) + 1 = " + ((j / 2) + 1));
        }

        if (difficulty == 3) {
            System.out.println("│ |3i - " + (3 * i - 1) + "| = 1");
            System.out.println("│ (j ÷ 2) + 1 = " + ((j / 2) + 1));
        }

        System.out.println();
    }

    private void endMenu(Scanner scanner) {
        System.out.println();
        System.out.println("1. Volver a jugar");
        System.out.println("2. Volver al menú");

        int option = scanner.nextInt();
        if (option == 1) {
            start();
        }
    }
}
