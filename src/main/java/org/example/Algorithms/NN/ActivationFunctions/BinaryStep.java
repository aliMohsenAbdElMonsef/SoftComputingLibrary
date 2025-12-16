package org.example.Algorithms.NN.ActivationFunctions;

public class BinaryStep implements ActivationFunction{
    @Override
    public double activate(double x) {
        return x >= 0 ? 1.0 : 0.0;
    }
    @Override
    public double derivative(double a) {
        return 0.0;
    }
}
