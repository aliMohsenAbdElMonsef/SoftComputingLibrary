package org.example.Algorithms.FuzzyLogic.Operations;

public class ANDMin extends AND {
    @Override
    public double apply(double a, double b) {
        return Math.min(a, b);
    }
}
