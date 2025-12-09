package org.example.Algorithms.FuzzyLogic.Operations;

public class ORSum extends OR {
    @Override
    public double apply(double a, double b) {
        return Math.min(1.0, a + b);
    }
}
