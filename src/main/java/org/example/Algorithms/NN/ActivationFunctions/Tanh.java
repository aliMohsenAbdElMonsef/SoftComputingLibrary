package org.example.Algorithms.NN.ActivationFunctions;

public class Tanh implements ActivationFunction{
    @Override
    public double activate(double x) {
        return Math.tanh(x);
    }

    @Override
    public double derivative(double a) {
        return 1.0 - (a * a);
    }
}
