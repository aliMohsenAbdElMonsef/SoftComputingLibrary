package org.example.Algorithms.NN.ActivationFunctions;

public interface ActivationFunction {
    double activate(double x);
    double derivative(double activatedValue);
}
