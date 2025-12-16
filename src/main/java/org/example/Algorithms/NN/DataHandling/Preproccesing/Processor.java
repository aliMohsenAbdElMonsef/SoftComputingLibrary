package org.example.Algorithms.NN.DataHandling.Preproccesing;

public interface Processor {
    void fit(double[][] xTrain);
    void transform(double[][] x);
}
