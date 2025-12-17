package org.example.Algorithms.NN.Training;

public class SGDOptimizer implements Optimizer {

    private final double learningRate;

    public SGDOptimizer(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public void update(double[][] weights, double[][] gradients, int layerIndex) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] -= learningRate * gradients[i][j];
            }
        }
    }

    @Override
    public void reset() {
        // SGD has no state to reset
    }
}
