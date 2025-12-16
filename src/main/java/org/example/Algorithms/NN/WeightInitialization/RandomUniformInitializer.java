package org.example.Algorithms.NN.WeightInitialization;

import java.util.Random;

public class RandomUniformInitializer implements WeightInitializer{
    private final double min;
    private final double max;
    private final Random random;

    public RandomUniformInitializer(double min, double max, long seed) {
        this.min = min;
        this.max = max;
        this.random = new Random(seed);
    }

    @Override
    public double[][] initialize(int rows, int cols) {
        double[][] w = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                w[i][j] = min + (max - min) * random.nextDouble();
            }
        }
        return w;
    }
}
