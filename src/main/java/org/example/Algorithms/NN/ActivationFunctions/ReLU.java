package org.example.Algorithms.NN.ActivationFunctions;

public class ReLU implements ActivationFunction{
    @Override
    public double activate(double x) {
        return Math.max(0.0, x);
    }
    @Override
    public double derivative(double a) {
        return a > 0 ? 1.0 : 0.0;
    }
}
