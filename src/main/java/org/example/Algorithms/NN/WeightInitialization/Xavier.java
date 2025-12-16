package org.example.Algorithms.NN.WeightInitialization;

import java.util.Random;

public class Xavier implements WeightInitializer{
    private final Random random;

    public Xavier(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public double[][] initialize(int rows, int cols) {
        double limit = Math.sqrt(6.0 / (rows + cols));
        double[][] w = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                w[i][j] = -limit + 2 * limit * random.nextDouble();
            }
        }
        return w;
    }
}
