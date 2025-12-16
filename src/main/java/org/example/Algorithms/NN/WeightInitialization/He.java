package org.example.Algorithms.NN.WeightInitialization;

import java.util.Random;

public class He implements WeightInitializer{
    private final Random random;

    public He(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public double[][] initialize(int rows, int cols) {
        double std = Math.sqrt(2.0 / cols);
        double[][] w = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                w[i][j] = random.nextGaussian() * std;
            }
        }
        return w;
    }
}
