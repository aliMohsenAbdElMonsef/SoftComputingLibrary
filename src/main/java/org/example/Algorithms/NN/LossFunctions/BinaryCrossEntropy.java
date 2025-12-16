package org.example.Algorithms.NN.LossFunctions;

public class BinaryCrossEntropy implements LossFunction{
    private static final double EPS = 1e-12;

    @Override
    public double loss(double predicted, double target) {
        predicted = clip(predicted);
        return -(target * Math.log(predicted)
                + (1.0 - target) * Math.log(1.0 - predicted));
    }

    @Override
    public double derivative(double predicted, double target) {
        predicted = clip(predicted);
        return (predicted - target)
                / (predicted * (1.0 - predicted));
    }

    private double clip(double v) {
        return Math.min(Math.max(v, EPS), 1.0 - EPS);
    }
}
