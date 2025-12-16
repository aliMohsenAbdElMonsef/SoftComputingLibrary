package org.example.Algorithms.NN.LossFunctions;

public class MSE implements LossFunction{
    @Override
    public double loss(double predicted, double target) {
        double diff = predicted - target;
        return 0.5 * diff * diff;
    }

    @Override
    public double derivative(double predicted, double target) {
        return predicted - target;
    }
}
