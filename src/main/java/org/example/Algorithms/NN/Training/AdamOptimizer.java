package org.example.Algorithms.NN.Training;

import java.util.HashMap;
import java.util.Map;

public class AdamOptimizer implements Optimizer {

    private final double learningRate;
    private final double beta1;
    private final double beta2;
    private final double epsilon;

    private Map<Integer, double[][]> m;
    private Map<Integer, double[][]> v;
    private int t;

    public AdamOptimizer(double learningRate) {
        this(learningRate, 0.9, 0.999, 1e-8);
    }

    public AdamOptimizer(double learningRate, double beta1, double beta2, double epsilon) {
        this.learningRate = learningRate;
        this.beta1 = beta1;
        this.beta2 = beta2;
        this.epsilon = epsilon;
        this.m = new HashMap<>();
        this.v = new HashMap<>();
        this.t = 0;
    }

    @Override
    public void update(double[][] weights, double[][] gradients, int layerIndex) {
        t++;

        if (!m.containsKey(layerIndex)) {
            int rows = weights.length;
            int cols = weights[0].length;
            m.put(layerIndex, new double[rows][cols]);
            v.put(layerIndex, new double[rows][cols]);
        }

        double[][] mLayer = m.get(layerIndex);
        double[][] vLayer = v.get(layerIndex);

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                mLayer[i][j] = beta1 * mLayer[i][j] + (1 - beta1) * gradients[i][j];

                vLayer[i][j] = beta2 * vLayer[i][j] + (1 - beta2) * gradients[i][j] * gradients[i][j];

                double mHat = mLayer[i][j] / (1 - Math.pow(beta1, t));

                double vHat = vLayer[i][j] / (1 - Math.pow(beta2, t));

                weights[i][j] -= learningRate * mHat / (Math.sqrt(vHat) + epsilon);
            }
        }
    }

    @Override
    public void reset() {
        m.clear();
        v.clear();
        t = 0;
    }
}
