package org.example.Algorithms.NN.LossFunctions;

public interface LossFunction {
    double loss(double predicted, double target);
    double derivative(double predicted, double target);
}
