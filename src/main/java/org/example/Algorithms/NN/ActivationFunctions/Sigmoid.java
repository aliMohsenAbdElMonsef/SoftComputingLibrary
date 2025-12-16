package org.example.Algorithms.NN.ActivationFunctions;

public class Sigmoid implements ActivationFunction{
    @Override
    public double activate(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    @Override
    public double derivative(double a) {
        return a * (1.0 - a);
    }
}
