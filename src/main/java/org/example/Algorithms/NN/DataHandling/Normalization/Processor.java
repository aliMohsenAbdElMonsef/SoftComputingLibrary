package org.example.Algorithms.NN.DataHandling.Normalization;

public interface Processor {
    void fit(double[][] xTrain);
    void transform(double[][] x);
}
