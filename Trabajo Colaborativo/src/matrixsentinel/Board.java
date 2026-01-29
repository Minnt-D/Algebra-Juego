package matrixsentinel;

import java.util.Random;

public class Board {

    private int rows;
    private int cols;
    private Cell[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        init();
    }

    private void init() {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
                if (random.nextDouble() < 0.2) {
                    grid[i][j].enemy = new Enemy(
                            new double[]{
                                    random.nextInt(4),
                                    random.nextInt(4),
                                    random.nextInt(4)
                            }
                    );
                }
            }
        }
    }

    public boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public Enemy getEnemy(int r, int c) {
        return grid[r][c].enemy;
    }

    public void reveal(int r, int c, int value) {
        grid[r][c].revealed = true;
        grid[r][c].value = value;
    }

    public void revealLoss(int r, int c) {
        grid[r][c].revealed = true;
        grid[r][c].value = -1;
    }

    public int countNearbyThreats(int r, int c) {
        int count = 0;

        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (isValid(i, j) && grid[i][j].enemy != null) {
                    count++;
                }
            }
        }

        return count;
    }

    public void print() {
        System.out.print("    ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((char) ('A' + i) + "   ");
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].revealed) {
                    System.out.print("|   ");
                } else if (grid[i][j].value == -1) {
                    System.out.print("| X ");
                } else {
                    System.out.print("| " + grid[i][j].value + " ");
                }
            }
            System.out.println("|");
        }
    }

    private static class Cell {
        boolean revealed = false;
        int value = 0;
        Enemy enemy = null;
    }
}
