package org.example.Algorithms.NN.Training;

public interface Optimizer {
    void update(double[][] weights, double[][] gradients, int layerIndex);

    void reset();
}
