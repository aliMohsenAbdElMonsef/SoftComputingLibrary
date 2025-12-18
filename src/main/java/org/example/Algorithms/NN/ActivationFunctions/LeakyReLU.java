package org.example.Algorithms.NN.ActivationFunctions;

public class LeakyReLU implements ActivationFunction {
    private final double alpha;

    public LeakyReLU() {
        this(0.01);
    }

    public LeakyReLU(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public double activate(double x) {
        return x > 0 ? x : alpha * x;
    }

    @Override
    public double derivative(double activated) {
        // For LeakyReLU, we need to check the original input
        // Since activated = x if x>0, or activated = alpha*x if x<0
        // We can infer: if activated > 0 (and alpha < 1), original x > 0
        // But this is problematic - we should pass the net value
        // For now, approximate: if output is positive and > |alpha*someNegative|,
        // likely x>0
        return activated > 0 ? 1.0 : alpha;
    }
}
