package org.example.Algorithms.FuzzyLogic.MembershipFunctions;

import java.util.ArrayList;
import java.util.List;

public class TriangularFunction implements Function {
    private double a, b, c;
    private String name;

    public TriangularFunction(String _name, double _a, double _b, double _c) {
        name = _name;
        a = _a;
        b = _b;
        c = _c;
        if(!(a<=b && b<=c))
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getCentroid() {
        return (a + b + c) / 3;
    }

    @Override
    public List<Double> getMembership() {
        List<Double> membership = new ArrayList<>();
        membership.add(a);
        membership.add(b);
        membership.add(c);
        return membership;
    }

    @Override
    public double getMaxPoint() {
        return b;
    }

    @Override
    public double fuzzify(double x) {
        if (x <= a) {
            return 0.0;
        } else if (x == b) {
            return 1.0;
        } else if (x >= c) {
            return 0.0;
        } else if (x > a && x < b) {
            return (x - a) / (b - a);
        } else {
            return (c - x) / (c - b);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
