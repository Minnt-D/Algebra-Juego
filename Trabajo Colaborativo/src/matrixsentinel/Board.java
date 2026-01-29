package matrixsentinel;

public class Board {

    private String[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        grid = new String[size][size];
        reset();
    }

    private void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = "#";
            }
        }
    }

    public void markDistance(int row, int col, int value) {
        grid[row][col] = String.valueOf(value);
    }

    public void markEnemy(int row, int col, boolean revealed) {
        grid[row][col] = revealed ? "◎" : "X";
    }

    public void print() {
        System.out.print("     ");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%02d ", i);
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.printf("%02d |  ", i + 1);
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
