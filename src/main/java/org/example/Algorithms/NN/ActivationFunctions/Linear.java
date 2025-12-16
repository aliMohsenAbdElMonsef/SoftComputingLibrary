package org.example.Algorithms.NN.ActivationFunctions;

public class Linear implements ActivationFunction{
    @Override
    public double activate(double x) {
        return x;
    }
    @Override
    public double derivative(double a) {
        return 1.0;
    }
}
