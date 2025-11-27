package org.example.FuzzyLogic.Operations;

public class ORMax extends OR {
    @Override
    public double apply(double a, double b) {
        return Math.max(a, b);
    }
}
