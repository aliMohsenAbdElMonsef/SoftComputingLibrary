package org.example.Algorithms.NN.Training;

public class TrainingConfig {
    public final double learningRate;
    public final int epochs;
    public final int batchSize;

    public TrainingConfig(double learningRate, int epochs, int batchSize) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.batchSize = batchSize;
    }
}
