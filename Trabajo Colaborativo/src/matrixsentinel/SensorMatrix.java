package matrixsentinel;

public class SensorMatrix {

    private double[][] matrix;

    public SensorMatrix() {
        matrix = new double[][]{
                {1, 0},
                {0, 1},
                {1, 1}
        };
    }

    public boolean detect(double[] v) {
        double a = v[0];
        double b = v[1];
        double c = v[2];
        return (a + b == c);
    }
}
