package org.example.Algorithms.FuzzyLogic.MembershipFunctions;

import java.util.ArrayList;
import java.util.List;

public class GaussianFunction implements Function {
    private double mean, standardDeviation;
    private String name;

    public GaussianFunction(String _name, double _mean, double _standardDeviation) {
        name = _name;
        mean = _mean;
        standardDeviation = _standardDeviation;
    }

    @Override
    public double getCentroid() {
        return mean;
    }

    @Override
    public List<Double> getMembership() {
        List<Double> membership = new ArrayList<>();
        membership.add(mean);
        membership.add(standardDeviation);
        return membership;
    }

    @Override
    public double getMaxPoint() {
        return mean;
    }

    @Override
    public double fuzzify(double x) {
        double exponent = -Math.pow(x - mean, 2) / (2 * Math.pow(standardDeviation, 2));
        return Math.exp(exponent);
    }

    @Override
    public String getName() {
        return name;
    }
}
