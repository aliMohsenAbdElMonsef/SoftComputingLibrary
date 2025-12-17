package org.example.Algorithms.NN.Training;

public class TrainingConfig {
    public final double learningRate;
    public final int epochs;
    public final int batchSize;
    public final double l2Lambda;

    public TrainingConfig(double learningRate, int epochs, int batchSize) {
        this(learningRate, epochs, batchSize, 0.01);
    }

    public TrainingConfig(double learningRate, int epochs, int batchSize, double l2Lambda) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.batchSize = batchSize;
        this.l2Lambda = l2Lambda;
    }
}
